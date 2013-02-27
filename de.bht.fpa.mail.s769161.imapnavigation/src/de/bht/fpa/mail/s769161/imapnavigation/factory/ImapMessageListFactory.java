package de.bht.fpa.mail.s769161.imapnavigation.factory;

import de.bht.fpa.mail.common.model.Folder;
import de.bht.fpa.mail.s769161.imapnavigation.model.ImapFolder;
import de.bht.fpa.mail.s769161.interfaces.ITreeObject;
import de.bht.fpa.mail.s769161.model.AbstractMessageListFactory;

public class ImapMessageListFactory extends AbstractMessageListFactory{
	
	
	public ImapMessageListFactory(ITreeObject treeObject) {
		super(treeObject);
	}

	@Override
	public void produceMessageList() {
		if(treeObject instanceof ImapFolder) {
			Folder folder = (Folder) treeObject.getObject();
			messages = folder.getMessages();
		}
		
	}

}
