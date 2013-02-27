package de.bht.fpa.mail.s769161.interfaces;

import java.util.List;

import de.bht.fpa.mail.common.model.Message;



public interface IMessageList {
	
	public List<Message> getMessages();
	public void addMessage(Message message);
	public void removeMessage(int index);


}
