package arjuna.client.core.config;

import java.util.HashMap;
import java.util.Map;

import arjuna.client.core.action.ConfigActionType;
import arjuna.client.core.action.SetuActionType;
import arjuna.client.core.connector.BaseSetuObject;
import arjuna.client.core.connector.SetuArg;
import arjuna.client.core.connector.SetuConnectUtils;
import arjuna.client.core.connector.SetuResponse;
import arjuna.client.testsession.TestSession;
import arjuna.lib.enums.BrowserName;
import arjuna.lib.enums.GuiAutomationContext;
import arjuna.lib.state.ArjunaSingleton;
import arjuna.tpi.enums.ArjunaOption;
import arjuna.tpi.helpers.Value;
import arjuna.tpi.test.TestConfig;

public class DefaultTestConfig extends BaseSetuObject implements TestConfig {
	private String name;
	private TestSession session;
	
	public DefaultTestConfig(TestSession testSession, String name, String setuId) {
		this.session = testSession;
		this.name = name;
		
		this.setSetuId(setuId);
		this.setSelfSetuIdArg("configSetuId");
		this.setTestSessionSetuIdArg(testSession.getSetuId());
	}
	
	private static Map<String,String> convertUserOptionValueMap(Map<String, Value> map){
		Map<String,String> outMap  = new HashMap<String,String>();
		for(String key: map.keySet()) {
			outMap.put(key, map.get(key).toString());
		}
		return outMap;
	}
	
	private static String registerConfig(TestSession testSession, boolean hasParent, String parentConfigId, Map<String, String> arjunaOptions, Map<String, Value> userOptions) throws Exception {
		SetuResponse response = SetuConnectUtils.sendRequest(
				ArjunaComponent.CONFIGURATOR,
				ConfigActionType.REGISTER_NEW_CONFIG, 
				SetuArg.testSessionArg(testSession.getSetuId()),
				SetuArg.arg("hasParent", hasParent),
				SetuArg.arg("parentConfigId", parentConfigId),
				SetuArg.arg("arjunaOptions", arjunaOptions),
				SetuArg.arg("userOptions", convertUserOptionValueMap(userOptions))
		);
		return response.getValueForConfigSetuId();
	}

	public static String registerConfig(TestSession testSession, Map<String, String> arjunaOptions, Map<String, Value> userOptions) throws Exception {
		return registerConfig(testSession, false, null, arjunaOptions, userOptions);
	}
	
	public static String registerChildConfig(TestSession testSession, String parentConfigId, Map<String, String> arjunaOptions, Map<String, Value> userOptions) throws Exception {
		return registerConfig(testSession, true, parentConfigId, arjunaOptions, userOptions);
	}

	@Override
	public TestSession getTestSession() {
		return this.session;
	}
	
	private Value fetchConfOptionValue(ConfigActionType actionType, String option) throws Exception {
		SetuResponse response = this.sendRequest(
				ArjunaComponent.CONFIGURATOR,
				actionType,
				SetuArg.arg("option", option)
		);
		return response.getValue();	
	}
	
	public Value getArjunaOptionValue(String option) throws Exception{
		return this.fetchConfOptionValue(
				ConfigActionType.GET_ARJUNA_OPTION_VALUE,
				ArjunaSingleton.INSTANCE.normalizeArjunaOption(option).toString()
		);
	}	
	
	public Value getArjunaOptionValue(ArjunaOption option) throws Exception{
		return this.fetchConfOptionValue(
				ConfigActionType.GET_ARJUNA_OPTION_VALUE,
				option.toString()
		);
	}
	
	public Value getUserOptionValue(String option) throws Exception{
		return this.fetchConfOptionValue(
				ConfigActionType.GET_USER_OPTION_VALUE,
				ArjunaSingleton.INSTANCE.normalizeUserOption(option)
		);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public GuiAutomationContext getGuiAutoContext() throws Exception {
		return GuiAutomationContext.valueOf(getArjunaOptionValue(ArjunaOption.GUIAUTO_CONTEXT).asString());
	}
	
	@Override
	public BrowserName getBrowserType() throws Exception {
		return getArjunaOptionValue(ArjunaOption.BROWSER_NAME).asEnum(BrowserName.class);
	}

	@Override
	public String getBrowerVersion() throws Exception {
		return getArjunaOptionValue(ArjunaOption.BROWSER_VERSION).asString();
	}

	@Override
	public String getBrowserBinaryPath() throws Exception {
		return getArjunaOptionValue(ArjunaOption.BROWSER_BIN_PATH).asString();
	}
	
	@Override
	public String getTestRunEnvName() throws Exception {
		return getArjunaOptionValue(ArjunaOption.TESTRUN_ENVIRONMENT).asString();
	}

	@Override
	public String getScreenshotsDir() throws Exception {
		return getArjunaOptionValue(ArjunaOption.SCREENSHOTS_DIR).asString();
	}
	
	@Override
	public String getLogDir() throws Exception {
		return getArjunaOptionValue(ArjunaOption.LOG_DIR).asString();
	}
	
	public int getGuiAutoMaxWaitTime() throws Exception {
		return getArjunaOptionValue(ArjunaOption.GUIAUTO_MAX_WAIT).asInt();
	}
}
