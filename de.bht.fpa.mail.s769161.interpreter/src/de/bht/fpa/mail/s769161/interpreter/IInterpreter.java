package de.bht.fpa.mail.s769161.interpreter;

import org.eclipse.jface.viewers.ViewerFilter;

public interface IInterpreter {
	
	public ViewerFilter getFilter(String filterString);

}
