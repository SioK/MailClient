package de.bht.fpa.mail.s769161.imapnavigation.handler;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.eclipse.jface.preference.IPreferenceStore;

import de.bht.fpa.mail.common.MailHelper;
import de.bht.fpa.mail.common.PersistenceManager;
import de.bht.fpa.mail.common.model.Account;
import de.bht.fpa.mail.s769161.imapnavigation.Activator;
import de.bht.fpa.mail.s769161.imapnavigation.manager.ImapManager;
import de.bht.fpa.mail.s769161.imapnavigation.model.AccountNode;
import de.bht.fpa.mail.s769161.imapnavigation.model.ImapAccount;
import de.bht.fpa.mail.s769161.imapnavigation.preferences.IMAPSettings;


public class IMAPHandler {

	private Account createAccount(EntityManager entityManager,
			String name, String host, String username, String password) {

		if (isValid(name, host, username, password)) {
			Account account = MailHelper.getAccount(entityManager, name);
			if (account == null) {
				account = new Account();
				account.setName(name);
				account.setHost(host);
				account.setUsername(username);
				account.setPassword(password);

				entityManager.getTransaction().begin();
				entityManager.persist(account);
				entityManager.getTransaction().commit();

				System.out.println("Created new account");

			} else {
				System.out.println("Use existing account");

			}
			return account;
		}
		
		return null;
	}

	private EntityManager getEntityManager() {

		EntityManagerFactory entityManagerFactory = PersistenceManager
				.getInstance().createEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		return entityManager;

	}

	private boolean isValid(String accountname, String host,
			String username, String password) {
		if (accountname == "" || host == "" || username == "" || password == "") {
			return false;
		}
		return true;
	}
	
	public void synchronizeMailBox() {
		
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		
	    String name = preferenceStore.getString(IMAPSettings.ACCOUNT_ID);
        String host = preferenceStore.getString(IMAPSettings.HOST);
        String username = preferenceStore.getString(IMAPSettings.USERNAME);
        String password = preferenceStore.getString(IMAPSettings.PASSWORD);
        
        EntityManager entityManager = getEntityManager();
        Account account = createAccount(entityManager, name, host, username, password);
        
    	try {
			MailHelper.syncAllFolders(account, entityManager);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    	
    	entityManager.close();
    	
    	AccountNode node = new AccountNode("root");
    	
    	node.addAccount(new ImapAccount(account));
    	
    	ImapManager.getInstance().setAccountNode(node);
		
		
	}

}
