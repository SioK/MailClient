package de.bht.fpa.mail.s769161.imapnavigation.model;

import java.util.ArrayList;
import java.util.List;

import de.bht.fpa.mail.s769161.interfaces.ITreeObject;
import de.bht.fpa.mail.s769161.interfaces.ITreeParent;

public class AccountNode implements ITreeParent{
	
	String name;
	ITreeParent imapParentItem;
	List<ITreeObject> accounts = new ArrayList<ITreeObject>();
	
	public AccountNode(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void addAccount(ImapAccount account) {
		accounts.add(account);
	}
	
	@Override
	public boolean hasChildren() {
		if (getChildren().size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Object getObject() {
		return this;
	}

	@Override
	public ITreeParent getParent() {
		return null;
	}

	@Override
	public List<ITreeObject> getChildren() {
		
		return accounts;
	}

}