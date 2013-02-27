package de.bht.fpa.mail.s769161.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import de.bht.fpa.mail.common.model.Message;

public class SubjectFilter extends ViewerFilter {

	private String filterString;

	public SubjectFilter(String filterText) {
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
		if (message.getSubject() != null) {
			return message.getSubject().contains(filterString);
		}
		return false;
	}

}
