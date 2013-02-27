package de.bht.fpa.mail.s769161.model;


import java.util.ArrayList;
import java.util.List;

import de.bht.fpa.mail.common.model.Message;
import de.bht.fpa.mail.s769161.interfaces.ITreeObject;

public abstract class AbstractMessageListFactory {
	
	public ITreeObject treeObject;
	public List<Message> messages = new ArrayList<Message>();

	public AbstractMessageListFactory(ITreeObject treeObject) {
		this.treeObject = treeObject;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public abstract void produceMessageList();

}
