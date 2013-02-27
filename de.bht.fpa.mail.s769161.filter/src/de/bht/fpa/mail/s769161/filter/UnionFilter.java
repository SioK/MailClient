package de.bht.fpa.mail.s769161.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class UnionFilter extends ViewerFilter {

	private ViewerFilter f1, f2;
	
	public UnionFilter(ViewerFilter f1, ViewerFilter f2) {
		this.f1 = f1;
		this.f2 = f2;
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return f1.select(viewer, parentElement, element) || f2.select(viewer, parentElement, element);
	}

}
