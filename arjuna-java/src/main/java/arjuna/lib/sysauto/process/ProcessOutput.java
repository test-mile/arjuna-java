package arjuna.lib.sysauto.process;

import java.io.IOException;

public class ProcessOutput {
	private Process proc = null;
	private String stdout = null;
	private String stderr = null;
	private int returnCode = -1000;
	private ThreadSafeStreamReader errorGobbler;
	private ThreadSafeStreamReader outputGobbler;
	
	public ProcessOutput(Process proc) throws Exception {
		this.proc = proc;
		// any error message?
		errorGobbler = new ThreadSafeStreamReader(proc.getErrorStream(), "ERROR");

		// any output?
		outputGobbler = new ThreadSafeStreamReader(proc.getInputStream(), "OUTPUT");

		// kick them off
		errorGobbler.start();
		outputGobbler.start();	
	}
	
	public void waitUntilComplete() throws Exception{
		int exitVal = proc.waitFor();
		this.stderr = errorGobbler.getOutput();
		this.stdout = outputGobbler.getOutput();
		this.returnCode = exitVal;	
	}
	
	public void waitForLaunch() throws Exception {
		Thread.sleep(2000);
		proc.getOutputStream().close();	
		this.stderr = errorGobbler.getOutput();
		this.stdout = outputGobbler.getOutput();
//		this.returnCode = exitVal;	
	}
	
	public boolean isProcessAlive() {
		return this.proc.isAlive();
	}
	
	public String getStdoutText() {
		return this.stdout;
	}

	public String getStderrText() {
		return this.stderr;
	}

	public int getReturnCode() {
		return this.returnCode;
	}

	public void exitProcess() throws Exception{
		proc.destroyForcibly();
	}

}
