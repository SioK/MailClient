package de.bht.fpa.mail.s769161.message.view;

import java.io.IOException;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.common.SelectionHelper;
import de.bht.fpa.mail.common.model.Attachment;
import de.bht.fpa.mail.common.model.Message;
import de.bht.fpa.mail.common.model.Recipient;
import de.bht.fpa.mail.s769161.message.contants.IMessageConstants;
import de.bht.fpa.mail.s769161.message.handler.AttachmentHandler;

public class View extends ViewPart implements IMessageConstants {

	public static final String ID = MAIL_VIEW_ID;

	private Label subject;
	private Link from;
	private Label cC;
	private Combo attachmentList;
	private Combo toList;
	private Button saveBtn;
	private DirectoryDialog directoryDialog;
	private Browser messageBrowser;

	private AttachmentHandler attachHandler;

	@Override
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();

		// top banner
		top.setLayout(layout);
		Composite banner = new Composite(top, SWT.NONE);
		GridData grid = new GridData(GridData.FILL,
				GridData.VERTICAL_ALIGN_BEGINNING, false, false);
		GridData columnSize = new GridData();
		columnSize.widthHint = BANNER_COLUMNSIZE;
		GridData dropdownSize = new GridData();
		dropdownSize.widthHint = DROPDOWN_LAYOUT_WIDTH;
		banner.setLayoutData(grid);
		layout = new GridLayout();
		layout.marginHeight = BANNER_LAYOUT_HEIGHT;
		layout.marginWidth = BANNER_LAYOUT_WIDTH;
		layout.numColumns = BANNER_LAYOUT_NUMCOLUMNS;
		banner.setLayout(layout);

		// setup bold font
		Font boldFont = JFaceResources.getFontRegistry().getBold(
				JFaceResources.DEFAULT_FONT);

		// add selection listener
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(listener);

		// sender
		Label l = new Label(banner, SWT.WRAP);
		l.setText(MAIL_VIEW_LABEL_FROM);
		l.setFont(boldFont);
		from = new Link(banner, SWT.WRAP);
		from.setLayoutData(columnSize);
		from.setText(MAIL_VIEW_TEXT_DEFAULT);
		from.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog
						.openInformation(getSite().getShell(),
								"Not Implemented",
								"Imagine the address book or a new message being created now.");
			}
		});

		// subject
		l = new Label(banner, SWT.WRAP);
		l.setText(MAIL_VIEW_LABEL_SUBJECT);
		l.setFont(boldFont);
		subject = new Label(banner, SWT.WRAP);
		subject.setLayoutData(columnSize);
		subject.setText(MAIL_VIEW_TEXT_DEFAULT);
		subject.setLayoutData(columnSize);

		// recipient
		l = new Label(banner, SWT.WRAP);
		l.setText(MAIL_VIEW_LABEL_TO);
		l.setFont(boldFont);
		toList = new Combo(banner, SWT.DROP_DOWN | SWT.BORDER);
		toList.setLayoutData(dropdownSize);

		// cC
		l = new Label(banner, SWT.WRAP);
		l.setText(MAIL_VIEW_LABEL_CC);
		l.setFont(boldFont);
		cC = new Label(banner, SWT.WRAP);
		cC.setLayoutData(columnSize);
		cC.setText(MAIL_VIEW_TEXT_DEFAULT);
		cC.setLayoutData(columnSize);

		// attachment
		l = new Label(banner, SWT.WRAP);
		l.setText(MAIL_VIEW_LABEL_ATTACHMENTS);
		l.setFont(boldFont);
		attachmentList = new Combo(banner, SWT.DROP_DOWN | SWT.BORDER);
		attachmentList.setLayoutData(dropdownSize);

		saveBtn = new Button(banner, SWT.NONE);
		saveBtn.setText("save Attachment");
		saveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (attachmentList.getItemCount() > 0) {
					directoryDialog = new DirectoryDialog(getSite().getShell(),
							SWT.SAVE);
					directoryDialog.getParent();
					String savePath = directoryDialog.open();
					if (savePath != null) {
						int attachmentIndex = attachmentList
								.getSelectionIndex();
						try {
							attachHandler.saveAttachment(savePath,
									attachmentIndex);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						IStatusLineManager manager = getViewSite()
								.getActionBars().getStatusLineManager();
						manager.setMessage(STATUS_LINE_ATTACHMENT_SAVED_PREFIX
								+ savePath);
					}
				}
			}
		});

		// message content
		messageBrowser = new Browser(top, SWT.MULTI | SWT.WRAP );
		messageBrowser.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	@Override
	public void setFocus() {

	}

	public void fillContent(Message message) {
		if (message != null) {
			if (message.getSubject() == null) {
				subject.setText("-");
			} else {
				subject.setText(message.getSubject());
			}

			if (message.getText() == null) {
				messageBrowser.setText("");

			} else {
				messageBrowser.setText(message.getText());
			}

			if (message.getRecipient() != null) {
				toList.removeAll();
				for (Recipient recipient : message.getRecipient()) {
					toList.add(recipient.getEmail());
				}
				toList.select(0);

			}
			if (message.getSender().getEmail() == null) {
				from.setText("-");
			} else {
				from.setText("<a>" + message.getSender().getEmail() + "</a>");
			}

			attachHandler = new AttachmentHandler(message.getAttachment());
			attachmentList.removeAll();
			for (Attachment attachment : attachHandler.getAttachments()) {
				attachmentList.add(attachment.getFileName());
			}
			attachmentList.select(0);

		}
	}

	ISelectionListener listener = new ISelectionListener() {
		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			Message selMsg = SelectionHelper.handleStructuredSelection(
					selection, Message.class);
			if (selMsg != null) {
				fillContent(selMsg);
			}
		}
	};

	@Override
	public void dispose() {
		super.dispose();
		getSite().getPage().removeSelectionListener(listener);
	}

}
