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

package arjex.youtube.scheduled;

import java.util.List;

import org.testng.annotations.Test;

import arjuna.tpi.Arjuna;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.testng.TestNGBaseTest;

public class Y17InteractionsRevisited extends TestNGBaseTest{
	
	@Test
	public void test() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.login.url").asString());
		
		List<String> credentials = automator.getConfig().getUserOptionValue("wp.users.admin").splitToStringList();
		
		// Login
		automator.Element(With.id("user_login")).setText(credentials.get(0));
		automator.Element(With.id("user_pass")).setText(credentials.get(1));
		automator.Element(With.id("wp-submit")).click();

		// Wait for dashboard to load by waiting for an element
		automator.Element(With.className("welcome-view-site")).waitUntilVisible();
		
		// Logout
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.logout.url").asString());
		automator.Element(With.partialText("logged out")).waitUntilVisible();

		automator.quit();
	}
	
}
