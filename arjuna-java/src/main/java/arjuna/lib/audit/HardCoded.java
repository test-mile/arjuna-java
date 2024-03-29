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

package arjuna.lib.audit;

import arjuna.lib.utils.StackBatteries;
import arjuna.tpi.Arjuna;

public class HardCoded {
	
	public static void log(String klass, String method, String why, float seconds) throws Exception {
		Arjuna.getLogger().warn(String.format("Hardcoded sleep executed for %s seconds in %s.%s method. Reason by author: %s", seconds, klass, method, why));
	}

	public static void sleep(String why, int seconds) throws Exception {
		Thread.sleep(seconds * 1000);
		log(StackBatteries.getInvokingClassName(), StackBatteries.getInvokingMethodName(), why, seconds);
	}

	public static void sleep(String why, float seconds) throws Exception {
		Thread.sleep(new Float(seconds).longValue());
		log(StackBatteries.getInvokingClassName(), StackBatteries.getInvokingMethodName(), why, seconds);
	}
}
