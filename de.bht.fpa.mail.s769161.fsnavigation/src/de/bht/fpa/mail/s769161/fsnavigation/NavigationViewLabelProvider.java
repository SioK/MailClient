package de.bht.fpa.mail.s769161.fsnavigation;



import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


import de.bht.fpa.mail.s769161.fsnavigation.model.DirectoryItem;
import de.bht.fpa.mail.s769161.interfaces.ITreeObject;

public class NavigationViewLabelProvider extends LabelProvider {

  @Override
  public String getText(Object obj) {
    return ((ITreeObject) obj).getName();
  }

  @Override
  public Image getImage(Object obj) {
    String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
    if (obj instanceof DirectoryItem) {
      imageKey = ISharedImages.IMG_OBJ_FOLDER;
    }
    return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
  }
}
