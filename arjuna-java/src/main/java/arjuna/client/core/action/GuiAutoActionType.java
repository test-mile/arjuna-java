package arjuna.client.core.action;

public enum GuiAutoActionType {
	LAUNCH_AUTOMATOR,
	QUIT_AUTOMATOR,
	
	BROWSER_GO_TO_URL,
	BROWSER_GO_BACK,
	BROWSER_GO_FORWARD,
	BROWSER_REFRESH,
	BROWSER_EXECUTE_JAVASCRIPT,

	DEFINE_ELEMENT,
	DEFINE_MULTIELEMENT,
	DEFINE_DROPDOWN,
	DEFINE_RADIOGROUP,
	DEFINE_ALERT,

	DEFINE_MAIN_WINDOW,
	DEFINE_DOM_ROOT,
	SET_SLOMO,
	
	ELEMENT_ENTER_TEXT,
	ELEMENT_SET_TEXT,
	ELEMENT_CLEAR_TEXT,
	
	ELEMENT_CLICK, 
	
	ELEMENT_WAIT_UNTIL_CLICKABLE, 
	
	ELEMENT_CHECK,
	ELEMENT_UNCHECK,
	
	DROPDOWN_HAS_VALUE_SELECTED,
	DROPDOWN_HAS_INDEX_SELECTED,
	DROPDOWN_SELECT_BY_VALUE,
	DROPDOWN_SELECT_BY_INDEX, 
	DROPDOWN_GET_FIRST_SELECTED_OPTION_VALUE,
	DROPDOWN_HAS_VISIBLE_TEXT_SELECTED,
	DROPDOWN_GET_FIRST_SELECTED_OPTION_TEXT,
	DROPDOWN_SELECT_BY_VISIBLE_TEXT,
	
	RADIOGROUP_HAS_VALUE_SELECTED,
	RADIOGROUP_HAS_INDEX_SELECTED,
	RADIOGROUP_SELECT_BY_VALUE,
	RADIOGROUP_SELECT_BY_INDEX, 
	RADIOGROUP_GET_FIRST_SELECTED_OPTION_VALUE,

	DOMROOT_FOCUS,
	DOMROOT_CREATE_FRAME,
	
	ALERT_CONFIRM,
	ALERT_DISMISS,
	ALERT_GET_TEXT,
	ALERT_SEND_TEXT,
	
	FRAME_FOCUS,
	FRAME_CREATE_FRAME,
	FRAME_GET_PARENT,
	
	WINDOW_FOCUS,
	WINDOW_GET_TITLE, 
	
	MAIN_WINDOW_MAXIMIZE,
	MAIN_WINDOW_CREATE_CHILD_WINDOW,
	MAIN_WINDOW_GET_LATEST_CHILD_WINDOW,
	MAIN_WINDOW_CLOSE_ALL_CHILD_WINDOWS,
	
	CHILD_WINDOW_CLOSE,
}