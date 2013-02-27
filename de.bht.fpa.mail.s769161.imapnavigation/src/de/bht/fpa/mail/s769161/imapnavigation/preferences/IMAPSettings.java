package de.bht.fpa.mail.s769161.imapnavigation.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.bht.fpa.mail.s769161.imapnavigation.Activator;


public class IMAPSettings extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public static String ACCOUNT_ID = "accoundID";
  public static String HOST = "host";
  public static String USERNAME = "username";
  public static String PASSWORD = "password";
 
  
  public IMAPSettings() {
    super(GRID);
  }

  @Override
  public void init(IWorkbench workbench) {
    setPreferenceStore(Activator.getDefault().getPreferenceStore());

  }

  @Override
  protected void createFieldEditors() {
    addField(new StringFieldEditor(ACCOUNT_ID, "Account Name:", getFieldEditorParent()));
    addField(new StringFieldEditor(HOST, "Host:", getFieldEditorParent()));
    addField(new StringFieldEditor(USERNAME, "Username:", getFieldEditorParent()));
    addField(new StringFieldEditor(PASSWORD, "Password:", getFieldEditorParent()));
    
  }

}
