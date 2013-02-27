package de.bht.fpa.mail.s769161.imapnavigation.action;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import de.bht.fpa.mail.s769161.imapnavigation.handler.IMAPHandler;

public class SynchronizeImapFolderAction extends Action {

	public SynchronizeImapFolderAction(IWorkbenchWindow window, String label) {

		setText(label);
		// The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.CMD_SYNC_IMAP_FOLDERS);
		// Associate the action with a pre-defined command, to allow key
		// bindings.
		setActionDefinitionId(ICommandIds.CMD_SYNC_IMAP_FOLDERS);
		setImageDescriptor(de.bht.fpa.mail.s769161.imapnavigation.Activator
				.getImageDescriptor("/icons/sample3.gif"));
	}

	@Override
	public void run() {

		Job syncJob = new Job("SynchronizeJob") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {

			  IMAPHandler imapHandler = new IMAPHandler();
			  
			  imapHandler.synchronizeMailBox();
			  //System.out.println("test");
			  
				return Status.OK_STATUS;
			}

		};

		syncJob.schedule();
		if (syncJob.getResult() == Status.OK_STATUS) {

			syncJob.cancel();
		}
	  

	}
}
