package arjuna.tpi.enums;

public enum ArjunaOption {
	// Arjuna Directories
	ROOT_DIR,
	LOG_DIR,
	TOOLS_DIR,
	APPS_DIR,
	DATA_DIR,
	DATA_SOURCES_DIR,
	DATA_REFERENCES_DIR,
	GUIAUTO_NAMESPACE_DIR,
	SCREENSHOTS_DIR,
	PROJECT_CONF_DIR,
	PROJECT_CONF_FILE,

	// Test Run
	TESTRUN_ENVIRONMENT,
	
	GUIAUTO_AUTOMATOR_NAME,
	
	// Browser (Common)
	BROWSER_NAME,
	BROWSER_VERSION,
	BROWSER_MAXIMIZE,
	BROWSER_HEADLESS_MODE,
	BROWSER_DIM_WIDTH,
	BROWSER_DIM_HEIGHT,
	BROWSER_BIN_PATH,
	BROWSER_PROXY_ON,
	BROWSER_PROXY_HOST,
	BROWSER_PROXY_PORT,
	
	// Application
	APP_URL,
	
	// General Gui Automation
	GUIAUTO_CONTEXT,
	GUIAUTO_SCROLL_PIXELS,
	GUIAUTO_SWIPE_TOP,
	GUIAUTO_SWIPE_BOTTOM,
	GUIAUTO_SWIPE_MAX_WAIT,
	GUIAUTO_MAX_WAIT,
	GUIAUTO_SLOMO_ON,
	GUIAUTO_SLOMO_INTERVAL,
	
	// Mobile automation
	MOBILE_OS_NAME,
	MOBILE_OS_VERSION,
	MOBILE_DEVICE_NAME,
	MOBILE_DEVICE_UDID,
	MOBILE_APP_FILE_PATH,
	MOBILE_APP_PACKAGE, 
	MOBILE_APP_ACTIVITY,

	// Selenium
	SELENIUM_DRIVER_PROP,
	SELENIUM_DRIVERS_DIR, // Place to find all drivers
	SELENIUM_DRIVER_PATH, // Overrides general look-up. If provided this absolute path is used.

	// Appium
	APPIUM_HUB_URL,
	APPIUM_AUTO_LAUNCH,
	
	// Image comparison
	IMAGE_COMPARISON_MIN_SCORE
}
