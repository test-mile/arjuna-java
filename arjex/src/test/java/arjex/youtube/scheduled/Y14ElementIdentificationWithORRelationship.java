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
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.testng.TestNGBaseTest;

public class Y14ElementIdentificationWithORRelationship extends TestNGBaseTest{
	
	@Test
	public void test() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.login.url").asString());
		
		// Any number of With constructors can be used.
		
		// Two identifiers. Only first one would be tried as it succeeds.
		GuiElement element = automator.Element(With.id("user_login"), With.name("log"));
		element.identify();
		System.out.println(element.getSource());
		
		// Two identifiers. First invalid, second valid. Hence it succeeds by using second With construct
		// Identification max wait time is for all With constructs clubbed together.
		element = automator.Element(With.id("INVALID"), With.name("log"));
		element.identify();
		System.out.println(element.getSource());
		
		automator.quit();
	}
	
}
