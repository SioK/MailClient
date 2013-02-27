package de.bht.fpa.mail.s769161.preferences;

import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import de.bht.fpa.mail.s769161.Activator;
import de.bht.fpa.mail.s769161.imapnavigation.preferences.IMAPSettings;

public class AbstractPreferenceInitializer extends org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer {

  public AbstractPreferenceInitializer() {
  }

  @Override
  public void initializeDefaultPreferences() {
    IEclipsePreferences node = DefaultScope.INSTANCE.getNode(Activator.PLUGIN_ID);
    node.put(IMAPSettings.ACCOUNT_ID, "");
    node.put(IMAPSettings.HOST, "");
    node.put(IMAPSettings.USERNAME, "");
    node.put(IMAPSettings.PASSWORD, "");
    node.put(FileSystemSettings.BASEDIRECTORY, "");
    
  }

}
