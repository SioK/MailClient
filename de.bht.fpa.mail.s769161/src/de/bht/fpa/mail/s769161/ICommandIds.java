package de.bht.fpa.mail.s769161;

/**
 * Interface defining the application's command IDs. Key bindings can be defined
 * for specific commands. To associate an action with a command, use
 * IAction.setActionDefinitionId(commandId).
 * 
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface ICommandIds {

  String CMD_OPEN = "de.bht.fpa.mail.s769161.open";
  String CMD_OPEN_MESSAGE = "de.bht.fpa.mail.s769161.openMessage";
  String CMD_OPEN_ROOT_DIRECTORY = "de.bht.fpa.mail.s769161.setBaseDirectory";

}
