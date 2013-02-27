package de.bht.fpa.mail.s769161.imapnavigation;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.bht.fpa.mail.s769161.imapnavigation.model.AccountNode;
import de.bht.fpa.mail.s769161.imapnavigation.model.ImapAccount;
import de.bht.fpa.mail.s769161.imapnavigation.model.ImapFolder;
import de.bht.fpa.mail.s769161.interfaces.ITreeObject;

public class IMAPNavigationViewLabelProvider extends LabelProvider {

	@Override
	public String getText(Object obj) {
		return ((ITreeObject) obj).getName();
	}

	@Override
	public Image getImage(Object obj) {
		String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
		if (obj instanceof ImapAccount) {
			imageKey = ISharedImages.IMG_OBJ_FOLDER;
		}
		if (obj instanceof AccountNode) {
			imageKey = ISharedImages.IMG_OBJ_FOLDER;
		}
		if (obj instanceof ImapFolder) {
			imageKey = ISharedImages.IMG_OBJ_FOLDER;
		}
		return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	}
}
