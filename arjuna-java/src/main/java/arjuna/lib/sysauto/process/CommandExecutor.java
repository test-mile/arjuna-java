/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package arjuna.lib.sysauto.process;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandExecutor {
	private String command = null;
	private List<String> args = null;

	public CommandExecutor(String command, String[] args) {
		this.command = command;
		this.args = Arrays.asList(args);
	}

	public CommandExecutor(String command, List<String> args) {
		this.command = command;
		this.args = args;
	}

	public CommandExecutor(String command, String argString) {
		this.command = command;
		this.args = DelimitedDataParser.parse(argString);
	}

	public CommandExecutor(String commandString) {
		List<String> arr = DelimitedDataParser.parse(commandString);
		this.command = arr.get(0);
		this.args = arr.subList(1, arr.size());
	}

	private void addPrefix(List<String> commandList) {
		String[] windows = { "cmd.exe", "/C" };
		String[] lin = {};

		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			commandList.addAll(Arrays.asList(windows));
		} else {
			commandList.addAll(Arrays.asList(lin));
		} 
	}

	private String[] getCommandArray() {
		List<String> commandList = new ArrayList<String>();
		addPrefix(commandList);
		commandList.add(this.command);
		commandList.addAll(this.args);
		return commandList.toArray(new String[commandList.size()]);
	}

	public ProcessOutput execute() throws Exception {
		System.out.println(Arrays.asList(getCommandArray()));
		//Process proc = Runtime.getRuntime()..exec(getCommandArray());
		ProcessBuilder pb = new ProcessBuilder(getCommandArray());
		String logDir = System.getProperty("user.dir") + File.separator + "log" + File.separator;
		pb.redirectError(new File(logDir + "service_stderr.txt"));
		pb.redirectOutput(new File(logDir + "service_stdout.txt"));
		return new ProcessOutput(pb.start());
	}

}
