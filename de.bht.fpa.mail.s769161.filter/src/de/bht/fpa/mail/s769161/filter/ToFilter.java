package de.bht.fpa.mail.s769161.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import de.bht.fpa.mail.common.model.Message;
import de.bht.fpa.mail.common.model.Recipient;

public class ToFilter extends ViewerFilter {

	private String filterString;

	public ToFilter(String filterText) {
		setSearch(filterText);
	}

	public void setSearch(String search) {
		this.filterString = search;
	}

	@Override
	public boolean select(Viewer viewer, Object dirItem, Object element) {
		if (filterString == null) {
			return true;
		}

		Message message = (Message) element;

		if (message.getRecipient() != null) {
			for (Recipient recipient : message.getRecipient()) {
				if (recipient.getEmail().contains(filterString))
					return true;
			}
		}

		return false;
	}

}
