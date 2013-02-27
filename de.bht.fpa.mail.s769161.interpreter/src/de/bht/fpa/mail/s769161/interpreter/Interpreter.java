package de.bht.fpa.mail.s769161.interpreter;

import org.eclipse.jface.viewers.ViewerFilter;

import de.bht.fpa.mail.s769161.filter.FromFilter;
import de.bht.fpa.mail.s769161.filter.IntersectionFilter;
import de.bht.fpa.mail.s769161.filter.ReadFilter;
import de.bht.fpa.mail.s769161.filter.SubjectFilter;
import de.bht.fpa.mail.s769161.filter.ToFilter;
import de.bht.fpa.mail.s769161.filter.UnionFilter;

public class Interpreter implements IInterpreter {
	
	private String filterString;

	@Override
	public synchronized ViewerFilter getFilter(String filterString) {
		this.filterString = filterString;
		return filter();
	}
	
	private ViewerFilter filter() {
		removeLeadingWhitespaces();
		
		if (filterString.startsWith("union"))
			return union();
		else if (filterString.startsWith("intersection"))
			return intersection();
		else
			return e();		
	}

	private ViewerFilter e() {
		if (next("from"))
			return from();
		else if (next("to"))
			return to();
		else if (next("subject"))
			return subject();
		else if (next("read"))
			return isread();
			
		return null;
	}

	private ViewerFilter union() {
		if (!read("union"))
			return null;

		if (!read("\\("))
			return null;
		
		ViewerFilter f1 = filter();
		if (f1 == null)
			return null;
		
		if (!read(","))
			return null;
		
		ViewerFilter f2 = filter();
		if (f2 == null)
			return null;
		
		if (!read("\\)"))
			return null;
		
		return new UnionFilter(f1, f2);
	}

	private ViewerFilter intersection() {
		if (!read("intersection"))
			return null;

		if (!read("\\("))
			return null;
		
		ViewerFilter f1 = filter();
		if (f1 == null)
			return null;
		
		if (!read(","))
			return null;
		
		ViewerFilter f2 = filter();
		if (f2 == null)
			return null;
		
		if (!read("\\)"))
			return null;
		
		return new IntersectionFilter(f1, f2);
	}

	private ViewerFilter isread() {
		if (!read("read"))
			return null;
		
		if (!read("\\(\""))
			return null;
		
		boolean isRead;
		
		if (read("true")) 
			isRead = true;
		else if (read("false"))
			isRead = false;
		else
			return null;
		
		if (!read("\"\\)"))
			return null;
		
		return new ReadFilter(isRead);
	}

	private ViewerFilter subject() {
		if (!read("subject\\s*\\(\\s*\""))
			return null;
		
		int textLength = filterString.indexOf("\"");
		if (textLength < 0)
			return null;
		
		String filterText = filterString.substring(0, textLength);
		
		read(filterText);
		
		if (!read("\"\\s*\\)"))
			return null;
		
		return new SubjectFilter(filterText);
	}

	private ViewerFilter to() {
		if (!read("to\\s*\\(\\s*\""))
			return null;
		
		int textLength = filterString.indexOf("\"");
		if (textLength < 0)
			return null;
		
		String filterText = filterString.substring(0, textLength);
		
		read(filterText);
		
		if (!read("\"\\s*\\)"))
			return null;
		
		return new ToFilter(filterText);
	}

	private ViewerFilter from() {
		if (!read("from\\s*\\(\\s*\""))
			return null;
		
		int textLength = filterString.indexOf("\"");
		if (textLength < 0)
			return null;
		
		String filterText = filterString.substring(0, textLength);
		
		read(filterText);
		
		if (!read("\"\\s*\\)"))
			return null;
		
		return new FromFilter(filterText);
	}

	private boolean read(String s) {
		boolean canRead = next(s);
		
		if (canRead) {
			filterString = filterString.replaceFirst(s, "");
			removeLeadingWhitespaces();
		}
		
		return canRead;
	}
	
	private boolean next(String s) {
		return filterString.matches(s+".*");
	}
	
	private void removeLeadingWhitespaces() {
 		if (filterString.startsWith(" "))
 			filterString = filterString.replaceFirst("\\s*", "");
	}

}
