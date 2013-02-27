package de.bht.fpa.mail.s769161.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.IWorkbenchWindow;

import de.bht.fpa.mail.s769161.ICommandIds;
import de.bht.fpa.mail.s769161.fsnavigation.manager.FileSystemManager;

public class OpenRootDirectoryAction extends Action {

  private final IWorkbenchWindow window;
  private DirectoryDialog directoryDialog;

  private String path;

  public OpenRootDirectoryAction(IWorkbenchWindow window, String label) {

    this.window = window;
    setText(label);
    // The id is used to refer to the action in a menu or toolbar
    setId(ICommandIds.CMD_OPEN_ROOT_DIRECTORY);
    // Associate the action with a pre-defined command, to allow key bindings.
    setActionDefinitionId(ICommandIds.CMD_OPEN_ROOT_DIRECTORY);
    setImageDescriptor(de.bht.fpa.mail.s769161.Activator.getImageDescriptor("/icons/sample2.gif"));
  }

  @Override
  public void run() {

    directoryDialog = new DirectoryDialog(window.getShell(), SWT.OPEN);
    directoryDialog.getParent();

    path = directoryDialog.open();

    FileSystemManager basePath = FileSystemManager.getInstance();
    basePath.setBaseDirectoryPath(path);

  }
}
