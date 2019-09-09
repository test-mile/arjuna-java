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

package arjex.youtube.basics;

import org.testng.annotations.Test;

import arjuna.tpi.test.TestConfig;
import arjuna.tpi.testng.TestNGBaseTest;

public class Y09UserOptionsFromProjectConf extends TestNGBaseTest{
	
	@Test
	public void test() throws Exception{
		TestConfig config = this.getTestContext().getConfig();
		
		System.out.println(config.getUserOptionValue("wp.app.url").asString());
		System.out.println(config.getUserOptionValue("wp.login.url").asString());
		System.out.println(config.getUserOptionValue("wp.logout.url").asString());
		
	}
	
}
