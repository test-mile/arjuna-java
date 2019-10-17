package arjuna.tpi.test;

import arjuna.client.core.connector.SetuManagedObject;
import arjuna.client.testsession.TestSession;
import arjuna.lib.enums.BrowserName;
import arjuna.lib.enums.GuiAutomationContext;
import arjuna.tpi.enums.ArjunaOption;
import arjuna.tpi.helpers.Value;

public interface TestConfig extends SetuManagedObject{
	Value getArjunaOptionValue(String option) throws Exception;	
	Value getArjunaOptionValue(ArjunaOption option) throws Exception;
	Value getUserOptionValue(String option) throws Exception;
	String getName();
	TestSession getTestSession();	
	String getLogDir() throws Exception;
	GuiAutomationContext getGuiAutoContext() throws Exception;
	BrowserName getBrowserType() throws Exception;
	String getBrowerVersion() throws Exception;
	String getBrowserBinaryPath() throws Exception;
	String getTestRunEnvName() throws Exception;
	String getScreenshotsDir() throws Exception;
	int getGuiAutoMaxWaitTime() throws Exception;
}
