package arjuna.lib.setu.testsession.requester;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import arjuna.lib.setu.core.requester.action.SetuActionType;
import arjuna.lib.setu.core.requester.action.TestSessionActionType;
import arjuna.lib.setu.core.requester.config.ArjunaComponent;
import arjuna.lib.setu.core.requester.config.DefaultTestConfig;
import arjuna.lib.setu.core.requester.connector.BaseSetuObject;
import arjuna.lib.setu.core.requester.connector.SetuArg;
import arjuna.lib.setu.core.requester.connector.SetuResponse;
import arjuna.lib.setu.databroker.requester.DataRecordType;
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
