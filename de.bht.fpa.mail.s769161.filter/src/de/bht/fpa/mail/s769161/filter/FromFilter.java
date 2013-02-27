package de.bht.fpa.mail.s769161.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import de.bht.fpa.mail.common.model.Message;

public class FromFilter extends ViewerFilter {

	private String filterString;

	public FromFilter(String filterText) {
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

		if (message.getSender().getEmail() != null) {
			return message.getSender().getEmail().contains(filterString);
		}
		return false;
	}

}
