package de.bht.fpa.mail.s769161;


import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import de.bht.fpa.mail.s769161.fsnavigation.manager.FileSystemManager;
import de.bht.fpa.mail.s769161.preferences.FileSystemSettings;


public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor implements IConstants {

  public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    super(configurer);
  }

  @Override
  public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    return new ApplicationActionBarAdvisor(configurer);
  }

  @Override
  public void preWindowOpen() {
    IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
    configurer.setInitialSize(new Point(WINDOW_WIDTH, WINDOW_HEIGHT));
    configurer.setTitle("Mail Client");
    configurer.setShowCoolBar(true);
    configurer.setShowStatusLine(true);

    // initial preferences

    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();

    // set BaseDirectory
    String rootPath = preferenceStore.getString(FileSystemSettings.BASEDIRECTORY);
    FileSystemManager basePath = FileSystemManager.getInstance();
    if (rootPath != "" && rootPath != null) {
      basePath.setBaseDirectoryPath(rootPath);
    }

 
  }

}
