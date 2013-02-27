package de.bht.fpa.mail.s769161.messagetable.view;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.bht.fpa.mail.common.SelectionHelper;
import de.bht.fpa.mail.common.model.Message;

import de.bht.fpa.mail.s769161.interfaces.ITreeObject;
import de.bht.fpa.mail.s769161.interpreter.IInterpreter;
import de.bht.fpa.mail.s769161.messagetable.Activator;
import de.bht.fpa.mail.s769161.messagetable.NameSorter;
import de.bht.fpa.mail.s769161.messagetable.TableViewContentProvider;
import de.bht.fpa.mail.s769161.messagetable.constants.IMessageTableConstants;
import de.bht.fpa.mail.s769161.statuslog.IStatusBar;

public class MessageTableView extends ViewPart implements
		IMessageTableConstants {

	public static final String ID = MAIL_LIST_VIEW_ID;
	private TableViewer viewer;
	private Combo filterCombinationType;
	private Combo filterType1;
	private Combo filterType2;
	private Text searchText1;
	private Text searchText2;
	private Button filterBtn;
	private NameSorter nameSorter;

	@Override
	public void createPartControl(Composite parent) {

		Composite top = new Composite(parent, SWT.NONE);

		createSearchBar(top);

		createTableViewer(top);

		getSite().setSelectionProvider(viewer);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Message selMsg = SelectionHelper
						.handleStructuredSelectionEvent(event, Message.class);
				if (selMsg != null) {
					String statusMsg = STATUS_LINE_OPENED_MESSAGE_PREFIX
							+ "ID_" + selMsg.getId();
					BundleContext context = Activator.getDefault().getBundle()
							.getBundleContext();
					ServiceReference<IStatusBar> serviceReference = context
							.getServiceReference(IStatusBar.class);
					if (serviceReference != null) {
						IStatusBar statusBar = context
								.getService(serviceReference);
						statusBar.setMessage(statusMsg);

					}
				}
			}
		});
	}

	public void createSearchBar(Composite top) {

		// searchBar banner
		GridLayout layout = new GridLayout();
		top.setLayout(layout);
		Composite searchBar = new Composite(top, SWT.BORDER);
		GridData grid = new GridData(GridData.FILL,
				GridData.VERTICAL_ALIGN_BEGINNING, false, false);
		GridData columnSize = new GridData();
		columnSize.widthHint = 120;
		GridData btnSize = new GridData();
		btnSize.widthHint = 100;
		searchBar.setLayoutData(grid);
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 15;
		layout.numColumns = 9;
		searchBar.setLayout(layout);

		Label filterTypeLabel = new Label(searchBar, SWT.NONE);
		filterTypeLabel.setText("FilterType: ");

		filterCombinationType = new Combo(searchBar, SWT.DROP_DOWN | SWT.BORDER);
		filterCombinationType.setLayoutData(columnSize);
		filterCombinationType.add("union");
		filterCombinationType.add("intersection");
		filterCombinationType.add("Filter1");
		filterCombinationType.add("Filter2");
		filterCombinationType.select(0);

		Label searchLabel1 = new Label(searchBar, SWT.NONE);
		searchLabel1.setText("Filter1: ");

		filterType1 = new Combo(searchBar, SWT.DROP_DOWN | SWT.BORDER);
		filterType1.setLayoutData(columnSize);
		filterType1.add("read");
		filterType1.add("subject");
		filterType1.add("from");
		filterType1.add("to");
		filterType1.select(0);

		searchText1 = new Text(searchBar, SWT.BORDER);
		searchText1.setLayoutData(columnSize);

		Label searchLabel2 = new Label(searchBar, SWT.NONE);
		searchLabel2.setText("Filter2: ");

		filterType2 = new Combo(searchBar, SWT.DROP_DOWN | SWT.BORDER);
		filterType2.add("read");
		filterType2.add("subject");
		filterType2.add("from");
		filterType2.add("to");
		filterType2.setLayoutData(columnSize);
		filterType2.select(0);

		searchText2 = new Text(searchBar, SWT.BORDER);
		searchText2.setLayoutData(columnSize);

		filterBtn = new Button(searchBar, SWT.NONE);
		filterBtn.setText("Filter");
		filterBtn.setLayoutData(btnSize);
		filterBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String searchText;

				BundleContext context = Activator.getDefault().getBundle()
						.getBundleContext();
				ServiceReference<IInterpreter> serviceReference = context
						.getServiceReference(IInterpreter.class);
				if (serviceReference != null) {
					IInterpreter interpreter = context
							.getService(serviceReference);

					String filterCombinationString = filterCombinationType
							.getItem(filterCombinationType.getSelectionIndex());

					String filterTypeString1 = filterType1.getItem(filterType1
							.getSelectionIndex());

					String filterTypeString2 = filterType2.getItem(filterType2
							.getSelectionIndex());

					if (filterCombinationString == "Filter1") {
						searchText = filterTypeString1 + "(" + "\""
								+ searchText1.getText() + "\"" + ")";
					} else if (filterCombinationString == "Filter2") {
						searchText = filterTypeString2 + "(" + "\""
								+ searchText2.getText() + "\"" + ")";
					} else {
						String filter1 = filterTypeString1 + "(" + "\""
								+ searchText1.getText() + "\"" + ")";
						String filter2 = filterTypeString2 + "(" + "\""
								+ searchText2.getText() + "\"" + ")";
						searchText = filterCombinationString + "(" + filter1
								+ "," + filter2 + ")";
						System.out.println(searchText);
					}

					ViewerFilter filter = interpreter.getFilter(searchText);
					ViewerFilter[] filters = {};
					viewer.setFilters(filters);
					if (filter != null) {
						viewer.addFilter(filter);
					}
					viewer.refresh();

				}
			}

		});

	}

	public void createTableViewer(Composite top) {

		GridLayout layout = new GridLayout();
		top.setLayout(layout);
		Composite tableLayout = new Composite(top, SWT.NONE);
		GridData grid = new GridData(GridData.CENTER, GridData.CENTER, false,
				false);
		grid.heightHint = 180;
		tableLayout.setLayoutData(grid);

		tableLayout.setLayout(layout);

		viewer = new TableViewer(tableLayout, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		viewer.setContentProvider(new TableViewContentProvider());

		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(listener);

		// creates the TableColumns
		createColumns();

		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		nameSorter = new NameSorter();
		viewer.setComparator(nameSorter);

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);

	}

	public void createColumns() {

		TableViewerColumn importance = createColumn(
				MAIL_LIST_COLUMN_IMPORTANCE, IMPORTANCE_IMAGE,
				MAIL_LIST_COLUMN_WIDTH_IMPORTANCE, IMPORTANCE_COLUMN_INDEX);
		importance.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Message) {
					Message msg = (Message) element;
					return msg.getImportance().toString();
				}
				return super.getText(element);
			}
		});

		TableViewerColumn read = createColumn(MAIL_LIST_COLUMN_READ,
				READ_IMAGE, MAIL_LIST_COLUMN_WIDTH_READ, READ_COLUMN_INDEX);
		read.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Message) {
					Message msg = (Message) element;
					return msg.isRead() + "";
				}
				return super.getText(element);
			}
		});

		TableViewerColumn from = createColumn(MAIL_LIST_COLUMN_FROM,
				SENDER_IMAGE, MAIL_LIST_COLUMN_WIDTH_FROM, FROM_COLUMN_INDEX);
		from.getColumn().addSelectionListener(
				getSelectionAdapter(from.getColumn(), FROM_COLUMN_INDEX));
		from.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Message) {
					Message msg = (Message) element;
					return msg.getSender().getEmail();
				}
				return super.getText(element);
			}
		});

		TableViewerColumn subject = createColumn(MAIL_LIST_COLUMN_SUBJECT,
				SUBJECT_IMAGE, MAIL_LIST_COLUMN_WIDTH_SUBJECT,
				SUBJECT_COLUMN_INDEX);
		subject.getColumn().addSelectionListener(
				getSelectionAdapter(subject.getColumn(), SUBJECT_COLUMN_INDEX));
		subject.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Message) {
					Message msg = (Message) element;
					return msg.getSubject();
				}
				return super.getText(element);
			}
		});

		TableViewerColumn date = createColumn(MAIL_LIST_COLUMN_DATE,
				DATE_IMAGE, MAIL_LIST_COLUMN_WIDTH_DATE, DATE_COLUMN_INDEX);
		date.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Message) {
					Message msg = (Message) element;
					return msg.getReceived().toString();
				}
				return super.getText(element);
			}
		});

	}

	// create a column
	private TableViewerColumn createColumn(String title, String imagePath,
			int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setImage(Activator.getImageDescriptor(imagePath).createImage());
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column,
			final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				nameSorter.setColumn(index);
				int direction = nameSorter.getDirection();
				viewer.getTable().setSortDirection(direction);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();

	}

	ISelectionListener listener = new ISelectionListener() {
		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			ITreeObject selDir = SelectionHelper.handleStructuredSelection(
					selection, ITreeObject.class);
			if (selDir != null) {
				viewer.setInput(selDir);
			}
		}
	};

	@Override
	public void dispose() {
		super.dispose();
		getSite().getPage().removeSelectionListener(listener);
	}
}
