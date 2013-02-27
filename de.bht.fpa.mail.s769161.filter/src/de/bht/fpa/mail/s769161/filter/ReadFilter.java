package de.bht.fpa.mail.s769161.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import de.bht.fpa.mail.common.model.Message;

public class ReadFilter extends ViewerFilter {

	private boolean read;

	public ReadFilter(boolean isRead) {
		setRead(isRead);
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	@Override
	public boolean select(Viewer viewer, Object dirItem, Object element) {
		Message message = (Message) element;
		
		return message.isRead() == read;

	}

}
