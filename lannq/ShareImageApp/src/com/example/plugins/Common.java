package com.example.plugins;

public class Common {
	public static final int SELECT_PICTURE = 1;
	public static String CONTACT_EXTRA = "contact";
	public static String ACTION_EXTRA = "action";
	public static int CONTACT_ACTIVITY_REQUEST_CODE = 0;

	// Define CONSTANTS
	public static final int SERVER_LISTEN_PORT = 6789;
	public static final int PACKET_SEND_LENGTH = 1024;
	public static final int MAX_FILE_NAME_LENGTH = 200;
	public static final String SHARED_FOLDER_NAME = "com.seedotech.photosharing";
	public static final String THUMBNAIL_FOLDER_NAME = ".thumbnails";

	// Define SIGNALS
	public static final int UNKNOWN_SIGNAL = -1;
	public static final int COMPLETED_RECEIVING_DATA_SIGNAL = 0;
	public static final int COMPLETED_SENDING_DATA_SIGNAL = 1;
	public static final int PROGRESS_SIGNAL = 2;
	public static final int SUCCESSFUL_CREATED_SERVER_SIGNAL = 3;
	public static final int SERVER_HAS_BEEN_CREATED_SIGNAL = 4;
	public static final int SESSION_NOT_EXISTING_SIGNAL = 5; // The session Id
																// is not
																// available
	// Error SIGNALS
	public static final int UNKNOWN_ERROR_SIGNAL = 6; // The session Id is not
														// available
	public static final int TIMEOUT_ERROR_SIGNAL = 7;

	// Define TRANSFER DIRECTION
	public static final int UNKNOWN_TRANSFER = -1;
	public static final int INCOMING_TRANSFER = 0;
	public static final int OUTGOING_TRANSFER = 1;

	public static boolean SUPPORT_PROGRESS = false;

	public static final int THUMBNAIL_WIDTH = 96;
	public static final int THUMBNAIL_HEIGHT = 96;
}
