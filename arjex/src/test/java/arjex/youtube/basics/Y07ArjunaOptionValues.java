/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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

package arjex.youtube.basics;

import org.testng.annotations.Test;

import arjuna.tpi.enums.ArjunaOption;
import arjuna.tpi.helpers.Value;
import arjuna.tpi.test.TestConfig;
import arjuna.tpi.testengine.TestNGBaseTest;

public class Y07ArjunaOptionValues extends TestNGBaseTest{
	
	@Test
	public void test() throws Exception{
		TestConfig config = this.getTestContext().getConfig();
		
		int waitTime = config.getGuiAutoMaxWaitTime();
		System.out.println(waitTime);
		
		Value maxWaitValue = config.getArjunaOptionValue(ArjunaOption.GUIAUTO_MAX_WAIT);
		System.out.println(maxWaitValue.asInt());
		
		maxWaitValue = config.getArjunaOptionValue("GUIAUTO_MAX_WAIT");
		System.out.println(maxWaitValue.asInt());	
		
		maxWaitValue = config.getArjunaOptionValue("GuIAuTo_MaX_WaIt");
		System.out.println(maxWaitValue.asInt());
		
		maxWaitValue = config.getArjunaOptionValue("guiauto.max.wait");
		System.out.println(maxWaitValue.asInt());
		
		System.out.println(config.getArjunaOptionValue(ArjunaOption.BROWSER_MAXIMIZE).asBoolean());
		
	}
	
}
