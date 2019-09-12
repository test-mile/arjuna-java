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
import arjuna.tpi.guiauto.component.DropDown;
import arjuna.tpi.guiauto.component.GuiElementConfig;
import arjuna.tpi.guiauto.component.GuiElementConfigBuilder;
import arjuna.tpi.guiauto.component.RadioGroup;
import arjuna.tpi.testng.TestNGBaseTest;

public class YXDropdownConfigTests extends TestNGBaseTest{
	
	@Test
	public void test1DefaultConfig() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.dropdown.url").asString());

		automator.DropDown(With.id("test")).selectByVisibleText("Another Option");
		
		//automator.quit();
	}
	
	@Test
	public void test2WrongTag() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.dropdown.url").asString());

		automator.DropDown(With.id("Prob1")).selectByIndex(1);
		
		automator.quit();
	}
	
	@Test
	public void test3StateCheckOff() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.dropdown.url").asString());

		GuiElementConfigBuilder confBuilder = new GuiElementConfigBuilder();
		GuiElementConfig config = confBuilder.checkPreState(false).build();
		
		automator.DropDown(With.id("test")).configure(config).selectByIndex(1);;
		
		automator.quit();
	}
	
	@Test
	public void test4WrongTagTypeAndStateCheckOff() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.dropdown.url").asString());

		GuiElementConfigBuilder confBuilder = new GuiElementConfigBuilder();
		GuiElementConfig config = confBuilder.checkType(false).checkPreState(false).build();
		
		automator.DropDown(With.id("Prob1")).configure(config).selectByIndex(1);
		
		//automator.quit();
	}
	
	@Test
	public void test5CustomOptionIdentifier() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.dropdown.url").asString());

		GuiElementConfigBuilder confBuilder = new GuiElementConfigBuilder();
		GuiElementConfig config = confBuilder.checkType(false).checkPostState(false).build();
//		
		DropDown dropdown = automator.DropDown(With.id("DropDown")).configure(config);
		automator.enableSlowMotion(true);
		dropdown.setOptionContainer(With.className("dropdown"));
		dropdown.setOptionLocators(With.className("dropdown-item"));
		dropdown.selectByIndex(2);
//		
//		automator.Element(With.partialText("Mouli")).click();
		
		//automator.quit();
	}
	
}
