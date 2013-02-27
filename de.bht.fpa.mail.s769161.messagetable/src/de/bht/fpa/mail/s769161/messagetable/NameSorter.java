package de.bht.fpa.mail.s769161.messagetable;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;

import de.bht.fpa.mail.common.model.Message;

public class NameSorter extends ViewerSorter {

	private int index;
	private static final int DOWN = 1;
	private int direction = DOWN;

	private static final int CASE1 = 0;
	private static final int CASE2 = 1;
	private static final int CASE3 = 2;
	private static final int CASE4 = 3;
	private static final int CASE5 = 4;

	public NameSorter() {
		this.index = 0;
		direction = DOWN;
	}

	public int getDirection() {
		if (direction == 1) {
			return SWT.DOWN;
		} else {
			return SWT.UP;
		}
	}

	public void setColumn(int column) {
		if (column == this.index) {
			direction = 1 - direction;
		} else {
			this.index = column;
			direction = DOWN;
		}
	}

	@Override
	public int compare(Viewer viewer, Object object1, Object object2) {

		Message messageObject1 = (Message) object1;
		Message messageObject2 = (Message) object2;
		int rc = 0;
		switch (index) {
		case CASE1:
			if (messageObject1.getImportance() != null
					&& messageObject2.getImportance() != null) {
				rc = messageObject1.getImportance().compareTo(
						messageObject2.getImportance());
			}
			break;
		case CASE2:
			if (messageObject1.isRead() != null
					&& messageObject2.isRead() != null) {
				rc = messageObject1.isRead().compareTo(messageObject2.isRead());
			}
			break;
		case CASE3:
			if (messageObject1.getSender().getEmail() != null
					&& messageObject2.getSender().getEmail() != null) {
				rc = messageObject1.getSender().getEmail()
						.compareTo(messageObject2.getSender().getEmail());
			}
			break;
		case CASE4:
			if (messageObject1.getSubject() != null
					&& messageObject2.getSubject() != null) {
				rc = messageObject1.getSubject().compareTo(
						messageObject2.getSubject());
			}
			break;
		case CASE5:
			if (messageObject1.getReceived() != null
					&& messageObject2.getReceived() != null) {
				rc = messageObject1.getReceived().toString()
						.compareTo(messageObject2.getReceived().toString());
			}
			break;
		default:
			rc = 0;
		}

		if (direction == DOWN) {
			rc = -rc;
		} 
		return rc;
	}
}
