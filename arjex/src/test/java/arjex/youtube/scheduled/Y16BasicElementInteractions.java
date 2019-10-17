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
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.helpers.With;
import arjuna.tpi.testengine.TestNGBaseTest;

public class Y16BasicElementInteractions extends TestNGBaseTest{
	
	@Test
	public void test() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.login.url").asString());
		
		List<String> credentials = automator.getConfig().getUserOptionValue("wp.users.admin").splitToStringList();
		
		// Login
		GuiElement userName = automator.Element(With.id("user_login"));
		userName.identify();
		userName.waitUntilClickable();
		userName.setText(credentials.get(0));
		
		GuiElement password = automator.Element(With.id("user_pass"));
		password.identify();
		password.waitUntilClickable();
		password.setText(credentials.get(1));
		
		GuiElement submit = automator.Element(With.id("wp-submit"));
		submit.identify();
		submit.waitUntilClickable();
		submit.click();

		// Wait for dashboard to load by waiting for an element
		automator.Element(With.className("welcome-view-site")).waitUntilVisible();

		// Go to logout URL
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.logout.url").asString());
		GuiElement confirmation = automator.Element(With.partialLinkText("log out"));
		confirmation.identify();
		confirmation.waitUntilClickable();
		confirmation.click();
		
		GuiElement message = automator.Element(With.partialText("logged out"));
		message.identify();
		message.waitUntilVisible();

		automator.quit();
	}
	
}
