package de.bht.fpa.mail.s769161.imapnavigation.model;


import de.bht.fpa.mail.common.model.Folder;
import de.bht.fpa.mail.s769161.interfaces.ITreeObject;
import de.bht.fpa.mail.s769161.interfaces.ITreeParent;

public class ImapFolder implements ITreeObject{

	private Folder folder;
	private ITreeParent imapParentItem;

	public ImapFolder(ITreeParent imapParentItem, Folder folder) {

		this.imapParentItem = imapParentItem;
		this.folder = folder;

	}

	@Override
	public Object getObject() {
		return folder;
	}

	@Override
	public String getName() {
		return folder.getFullName();
	}

	@Override
	public ITreeParent getParent() {

		return imapParentItem;
	}
}
