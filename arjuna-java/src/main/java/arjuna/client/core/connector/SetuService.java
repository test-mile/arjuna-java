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
import arjuna.client.core.connector.*;

import java.net.*; 
import java.io.*; 

public class SetuService {
	private ProcessOutput po;
	
	private boolean isPortAvailable(int port){
		try {
			Socket socket;
			ServerSocket serverSocket = new ServerSocket(port);
			serverSocket.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	private boolean isSetuRunning() throws Exception{
		// Check if Arjuna Setu is running
		SetuResponse response;
		try {
			DefaultSetuRequester requester = new DefaultSetuRequester();
			DefaultSetuRequest request = new DefaultSetuRequest(ArjunaComponent.SETU.toString(), SetuActionType.HELLO.toString());
			response = requester.post(request);
			System.out.println(response.getValue().asString());
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
		// Setu request was handled. Validating response
		String responseText = response.getValue().asString();
        if (!responseText.toLowerCase().contains("hello")) {
            throw new Exception("SET_SVC_ERROR:: Unexpected Setu error for Hello. Got response: " + responseText);
        } else {
        	return true;
        }	
	}
	
	private String getProcessOutput() {
		String outAndErr = po.getStderrText() + System.lineSeparator()  + po.getStdoutText();
		System.out.println(outAndErr);
		return outAndErr;
	}
	
	private void waitForSetu() throws Exception{
		long stime = System.currentTimeMillis();
		long ctime = stime;
		while(ctime - stime < 70000) {
			if (!po.isProcessAlive()) {
				po.waitUntilComplete();
				String outAndErr = getProcessOutput();
				if (outAndErr.contains("No module named arjuna")) {
					throw new Exception("Arjuna is not installed in the Python environment.");
				} else if (outAndErr.contains("No module named")) {
					throw new Exception("You should run python with 'python -m arjuna' command.");
				} else if (outAndErr.contains("SET_SVC_ERROR")) {
					throw new Exception("Problem in launching Setu Service: " + System.lineSeparator() + outAndErr);
				} else {
					throw new Exception("Problem: " + System.lineSeparator() + outAndErr);
				}
			} else if (!isSetuRunning()) {
				Thread.sleep(500);
				ctime += 500;
				continue;
			} else {
				return;
			}
		}
		
		String output = getProcessOutput();
		po.exitProcess();
		throw new Exception("Arjuna Setu service could not be launched. " + output);
	}
	
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
		
		int port = Integer.parseInt(props.getProperty("setu.port"));
		if (!isPortAvailable(port)) {
			if (!isSetuRunning()) {
				throw new Exception("A non Arjuna Setu server is running on port. Use some other port or close the existing server/service");
			} else {
				System.out.println("Using existing Setu service.");
				return;
			}
		} else {
		    // Port is available
			String[] cargs = {"-m", "arjuna", "launch-setu", "--port", props.getProperty("setu.port")};
			CommandExecutor ce = new CommandExecutor(props.getProperty("python3.bin.path"), cargs);
			po = ce.execute();
			waitForSetu();
		}
	}
	
	public void stop() throws Exception{
		SetuRequester setuClient = new DefaultSetuRequester();
		SetuRequest request = new DefaultSetuRequest(ArjunaComponent.SETU.toString(), SetuActionType.EXIT.toString());
		setuClient.postExit(request);
	}

}



