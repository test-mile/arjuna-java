package arjuna.client.testsession;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import arjuna.client.core.action.SetuActionType;
import arjuna.client.core.action.TestSessionActionType;
import arjuna.client.core.config.ArjunaComponent;
import arjuna.client.core.config.DefaultTestConfig;
import arjuna.client.core.connector.BaseSetuObject;
import arjuna.client.core.connector.SetuArg;
import arjuna.client.core.connector.SetuResponse;
import arjuna.client.databroker.DataRecordType;
import arjuna.lib.state.ArjunaSingleton;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.test.TestConfig;
import arjuna.tpi.value.Value;

public class DefaultTestSession extends BaseSetuObject implements TestSession {
	private final String DEF_CONF_NAME = "central";

	@Override
	public TestConfig init(String rootDir) throws Exception {
		SetuResponse response = this.sendRequest(
				ArjunaComponent.TEST_SESSION,
				TestSessionActionType.INIT, 
				SetuArg.arg("projectRootDir", rootDir),
				SetuArg.arg("cliConfig", ArjunaSingleton.INSTANCE.getCliArgsConfig().asMap())
				);
		this.setSetuId(response.getValueForTestSessionSetuId());
		this.setSelfSetuIdArg("testSessionSetuId");
		TestConfig config = new DefaultTestConfig(this, DEF_CONF_NAME, response.getValueForConfigSetuId());
		return config;
	}

	@Override
	public void finish() throws Exception {
		// TODO Auto-generated method stub
		
	}

}