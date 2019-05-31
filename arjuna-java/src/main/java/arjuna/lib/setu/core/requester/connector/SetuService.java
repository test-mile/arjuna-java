package arjuna.lib.setu.core.requester.connector;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import arjuna.lib.setu.core.requester.config.SetuActionType;
import arjuna.lib.sysauto.process.CommandExecutor;
import arjuna.lib.sysauto.process.ProcessOutput;

public class SetuService {
	private ProcessOutput po;
	
	public void launch() throws Exception {
		try {
		    // Port is available
			String[] cargs = {"/Users/rahulverma/Documents/github_tm/arjuna/scripts/arjuna_launcher.py", "launch-setu"};
			CommandExecutor ce = new CommandExecutor("/Library/Frameworks/Python.framework/Versions/3.6/bin/python3", cargs);
			po = ce.execute();
			po.waitForLaunch();
			String outAndErr = "STDERR:" + System.lineSeparator() + po.getStderrText() + "STDOUT:" + System.lineSeparator()  + po.getStdoutText();
			if (po.isProcessAlive()) {
				System.out.println("Setu service launched successfully.");
			} else {
				if (outAndErr.indexOf("Address alreadd in use") == -1) {
					throw new SocketException(outAndErr);
				}
				throw new Exception("Setu service could not be launched. Look at the information below and take corrective actions." + System.lineSeparator() + outAndErr);
			}
		} catch(SocketException e) {
			SetuRequester setuClient = new DefaultSetuRequester();
			SetuRequest request = new DefaultSetuRequest(SetuActionType.SETU_HELLO);
			SetuResponse response = setuClient.post(request);
			if (!response.getValue().asString().equalsIgnoreCase("hello")) {
				// It is not a Setu Service
				throw new Exception("Another service is running at port 9000. Setu could not be launched");
			} else {
				SetuRequest killSession = new DefaultSetuRequest(SetuActionType.SETU_KILL_EXISTING_TESTSESSION);
				SetuResponse sessionResponse = setuClient.post(killSession);
			}
		}

	}
	
	public void stop() throws Exception{
		po.exitProcess();
	}

}



