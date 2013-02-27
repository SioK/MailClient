package de.bht.fpa.mail.s769161.imapnavigation.manager;

import java.util.Observable;
import java.util.Observer;

import de.bht.fpa.mail.s769161.imapnavigation.model.AccountNode;


public class ImapManager {

	private static ImapManager instance = null;

	private AccountNode accountNode = new AccountNode("root");

	private ImapManager() {
	}

	public static synchronized ImapManager getInstance() {
		if (instance == null) {
			instance = new ImapManager();
		}

		return instance;
	}

	static class MyObservable extends Observable {
		@Override
		public synchronized void setChanged() {
			super.setChanged();
		}
	}

	private MyObservable accountNodeObservable = new MyObservable();
	

	public void removeAccountNodeObserver(Observer observer) {
		accountNodeObservable.addObserver(observer);

	}

	public void addAccountNodeObserver(Observer observer) {
		accountNodeObservable.addObserver(observer);
	}

	public AccountNode getAccountNode() {
		return this.accountNode;
	}

	public void setAccountNode(AccountNode accountNode) {

		if (accountNode != null) {
			this.accountNode = accountNode;
			accountNodeObservable.setChanged();
			accountNodeObservable.notifyObservers(accountNode);
		}

	}
}
