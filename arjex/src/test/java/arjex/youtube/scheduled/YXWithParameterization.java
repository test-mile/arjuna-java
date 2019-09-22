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

import org.testng.annotations.Test;

import arjuna.tpi.Arjuna;
import arjuna.tpi.arg.Args;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.testng.TestNGBaseTest;

public class YXWithParameterization extends TestNGBaseTest{
	
	@Test
	public void test() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.login.url").asString());
		
		// The following code is for user name field.
		// Html of user name: <input type="text" name="log" id="user_login" class="input" value="" size="20">
		
		// Params: 
		// APP URL: E.g. http://192.168.56.103
		// Page: E.g. wp-login
		// Resulting in http://192.168.56.103/wp-login.php
		String identifier = "//*[@action='%app_url%/%page%.php']";
		
		String appURL = automator.getConfig().getUserOptionValue("wp.app.url").asString();
		String page = "wp-login";
		
		// Positional
		GuiElement element = automator.Element(With.xpath(identifier).format(appURL, page));
		element.identify();
		System.out.println(element.Source().getRootContent());

		// Named
		element = automator.Element(With.xpath(identifier).format(Args.arg("app_url", appURL), Args.arg("page", page)));
		element.identify();
		System.out.println(element.Source().getRootContent());
		
		
		// Named params need not be passed in order, providing you flexibility, readability and preventing positional errors.
		element = automator.Element(With.xpath(identifier).format(Args.arg("page", page), Args.arg("app_url", appURL)));
		element.identify();
		System.out.println(element.Source().getRootContent());
		
		// Names for parameters are case-insensitive
		element = automator.Element(With.xpath(identifier).format(Args.arg("PaGe", page), Args.arg("aPP_Url", appURL)));
		element.identify();
		System.out.println(element.Source().getRootContent());

		automator.quit();
	}
	
}
