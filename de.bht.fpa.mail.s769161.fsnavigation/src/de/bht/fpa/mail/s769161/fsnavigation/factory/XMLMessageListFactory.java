package de.bht.fpa.mail.s769161.fsnavigation.factory;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.bht.fpa.mail.common.model.Message;
import de.bht.fpa.mail.s769161.fsnavigation.model.DirectoryItem;
import de.bht.fpa.mail.s769161.interfaces.ITreeObject;
import de.bht.fpa.mail.s769161.model.AbstractMessageListFactory;

public class XMLMessageListFactory extends AbstractMessageListFactory {

	public XMLMessageListFactory(ITreeObject treeObject) {
		super(treeObject);

	}

	@Override
	public void produceMessageList() {
		JAXBContext context;
		if (treeObject instanceof DirectoryItem) {
			for (ITreeObject subFile : ((DirectoryItem) treeObject).getFiles()) {
				if (subFile.getName().endsWith(".xml")) {
					Message message;
					try {
						context = JAXBContext.newInstance(Message.class);
						Unmarshaller u = context.createUnmarshaller();
						message = (Message) u.unmarshal((File) subFile.getObject());
						messages.add(message);
					} catch (JAXBException e) {
						System.out.println("invalid xml format");
					}
				}
			}

		}
	}
}
