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

import arjuna.tpi.Arjuna;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.testengine.TestNGBaseTest;

public class Y10BasicWebIdentifiers extends TestNGBaseTest{
	
	@Test
	public void test() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.login.url").asString());
		
		// The following code is for user name field.
		// Html of user name: <input type="text" name="log" id="user_login" class="input" value="" size="20">
		GuiElement element = automator.Element(With.id("user_login"));
		element.identify();
		System.out.println(element.Source().getRootContent());
		
		element = automator.Element(With.name("log"));
		element.identify();
		System.out.println(element.Source().getRootContent());
		
		element = automator.Element(With.className("input"));
		element.identify();
		System.out.println(element.Source().getRootContent());
		
		element = automator.Element(With.tagName("input"));
		element.identify();
		System.out.println(element.Source().getRootContent());
		
		// The following options are for 
		// Html of link: <a href="http://192.168.56.103/wp-login.php?action=lostpassword" title="Password Lost and Found">Lost your password?</a>
		element = automator.Element(With.linkText("Lost your password?"));
		element.identify();
		System.out.println(element.Source().getRootContent());
		
		element = automator.Element(With.partialLinkText("password"));
		element.identify();
		System.out.println(element.Source().getRootContent());

		automator.quit();
	}
	
}
