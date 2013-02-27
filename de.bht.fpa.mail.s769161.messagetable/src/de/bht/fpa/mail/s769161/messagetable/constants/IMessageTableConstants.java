package de.bht.fpa.mail.s769161.messagetable.constants;

public interface IMessageTableConstants {

	// SearchField
	int SEARCHFIELD_HEIGHT = 15;
	int SEARCHFIELD_WIDTH = 150;

	// Message List View
	String MAIL_LIST_VIEW_ID = "de.bht.fpa.mail.s769161.messagetable.view";
	int MAIL_LIST_COLUMN_WIDTH_IMPORTANCE = 60;
	int MAIL_LIST_COLUMN_WIDTH_READ = 80;
	int MAIL_LIST_COLUMN_WIDTH_FROM = 200;
	int MAIL_LIST_COLUMN_WIDTH_SUBJECT = 330;
	int MAIL_LIST_COLUMN_WIDTH_DATE = 200;
	int IMPORTANCE_COLUMN_INDEX = 0;
	int READ_COLUMN_INDEX = 1;
	int FROM_COLUMN_INDEX = 2;
	int SUBJECT_COLUMN_INDEX = 3;
	int DATE_COLUMN_INDEX = 4;
	String MAIL_LIST_COLUMN_IMPORTANCE = "";
	String MAIL_LIST_COLUMN_READ = "Read";
	String MAIL_LIST_COLUMN_FROM = "Sender";
	String MAIL_LIST_COLUMN_SUBJECT = "Subject";
	String MAIL_LIST_COLUMN_DATE = "Received";
	String MAIL_LIST_COLUMN_ATACHMENTS = "Atachments";

	// Status Line Messages
	String STATUS_LINE_OPENED_MESSAGE_PREFIX = "Opened Message: ";
	
	// input
	
	String DEFAULT_INPUT = "user.home";
	
	

	// Icons
	String IMPORTANCE_IMAGE = "icons/important.png";
	String READ_IMAGE = "icons/read.png";
	String DATE_IMAGE = "icons/date.png";
	String SENDER_IMAGE = "icons/sender.png";
	String SUBJECT_IMAGE = "icons/subject.png";

}
