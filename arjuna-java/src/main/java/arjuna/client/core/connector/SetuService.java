package arjuna.client.core.connector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;

import arjuna.client.core.action.SetuActionType;
import arjuna.client.core.config.ArjunaComponent;
import arjuna.lib.sysauto.process.CommandExecutor;
import arjuna.lib.sysauto.process.ProcessOutput;

public class SetuService {
	private ProcessOutput po;
	
	public void launch() throws Exception {
		String propPath = System.getProperty("user.dir") + File.separator + "config" + File.separator + "service.properties";
		Properties props;
		try {
			InputStream input = new FileInputStream(propPath);
			props = new Properties();
			props.load(input);
        } catch (Exception e) {
            throw new Exception("Could not load properties file at " + propPath + ". Message: " + e.getMessage());
        }
		
	    // Port is available
		String[] cargs = {props.getProperty("setu.launcher.path"), "launch-setu", "--port", props.getProperty("setu.port")};
		CommandExecutor ce = new CommandExecutor(props.getProperty("python3.bin.path"), cargs);
		po = ce.execute();
		po.waitForLaunch();
		String outAndErr = po.getStderrText() + System.lineSeparator()  + po.getStdoutText();
		if (outAndErr.contains("SET_SVC_ERROR")) {
			throw new Exception("Problem in launching Setu Service: " + System.lineSeparator() + outAndErr);
		}
	}
	
	public void stop() throws Exception{
		SetuRequester setuClient = new DefaultSetuRequester();
		SetuRequest request = new DefaultSetuRequest(ArjunaComponent.SETU.toString(), SetuActionType.EXIT.toString());
		setuClient.postExit(request);
	}

}



