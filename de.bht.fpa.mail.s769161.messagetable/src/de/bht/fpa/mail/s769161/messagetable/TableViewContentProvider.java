package de.bht.fpa.mail.s769161.messagetable;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.bht.fpa.mail.s769161.fsnavigation.factory.XMLMessageListFactory;
import de.bht.fpa.mail.s769161.fsnavigation.model.DirectoryItem;
import de.bht.fpa.mail.s769161.imapnavigation.factory.ImapMessageListFactory;
import de.bht.fpa.mail.s769161.imapnavigation.model.ImapFolder;
import de.bht.fpa.mail.s769161.interfaces.ITreeObject;


public class TableViewContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof DirectoryItem) {
			XMLMessageListFactory factory = new XMLMessageListFactory((ITreeObject) inputElement);
			factory.produceMessageList();
			return factory.getMessages().toArray();
		}

		if (inputElement instanceof ImapFolder) {
			ImapMessageListFactory factory = new ImapMessageListFactory((ITreeObject) inputElement);
			factory.produceMessageList();
			return factory.getMessages().toArray();
		}
		return null;
	}

}
