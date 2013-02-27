package de.bht.fpa.mail.s769161.fsnavigation;


import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.bht.fpa.mail.s769161.fsnavigation.model.DirectoryItem;
import de.bht.fpa.mail.s769161.fsnavigation.model.FileItem;

public class NavigationViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub

  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof DirectoryItem) {
      return ((DirectoryItem) parentElement).getChildren().toArray();
    }

    return new Object[0];
  }

  @Override
  public Object getParent(Object element) {

    if (element instanceof FileItem) {
      return ((FileItem) element).getParent();
    }
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {

    if (element instanceof DirectoryItem) {
      return ((DirectoryItem) element).hasChildren();
    }
    return false;
  }

  @Override
  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);
  }

}
