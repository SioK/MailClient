package de.bht.fpa.mail.s769161.imapnavigation.model;

import java.util.ArrayList;
import java.util.List;


import de.bht.fpa.mail.common.model.Account;
import de.bht.fpa.mail.common.model.Folder;
import de.bht.fpa.mail.s769161.interfaces.ITreeObject;
import de.bht.fpa.mail.s769161.interfaces.ITreeParent;

public class ImapAccount implements ITreeParent {

	private Account account;
	private ITreeParent imapParentItem;

	private List<ITreeObject> children;

	public ImapAccount(Account account) {
		this.account = account;
	}

	public ImapAccount(ITreeParent imapParentItem, Account account) {
		this.imapParentItem = imapParentItem;
		this.account = account;
	}

	@Override
	public Object getObject() {
		return this;

	}

	@Override
	public String getName() {
		return account.getName();
	}

	@Override
	public ITreeParent getParent() {

		return imapParentItem;
	}

	@Override
	public boolean hasChildren() {
		if (getChildren().size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ITreeObject> getChildren() {
		children = new ArrayList<ITreeObject>();
		for (Folder folder : account.getFolders()) {
			children.add(new ImapFolder(this, folder));
		}

		return children;

	}

}