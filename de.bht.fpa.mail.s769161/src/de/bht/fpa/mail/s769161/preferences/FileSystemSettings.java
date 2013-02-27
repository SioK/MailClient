package de.bht.fpa.mail.s769161.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.bht.fpa.mail.s769161.Activator;

public class FileSystemSettings extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public static String BASEDIRECTORY = "baseDir";
 
  
  public FileSystemSettings() {
    super(GRID);
  }

  @Override
  public void init(IWorkbench workbench) {
    setPreferenceStore(Activator.getDefault().getPreferenceStore());

  }

  @Override
  protected void createFieldEditors() {
    addField(new DirectoryFieldEditor(BASEDIRECTORY, "BaseDirectory:", getFieldEditorParent()));
    
  }

}
