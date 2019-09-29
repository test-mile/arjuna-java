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

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import arjuna.tpi.Arjuna;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.DropDown;
import arjuna.tpi.guiauto.component.GuiActionConfig;
import arjuna.tpi.guiauto.component.GuiActionConfigBuilder;
import arjuna.tpi.guiauto.component.RadioGroup;
import arjuna.tpi.testengine.TestNGBaseTest;

public class YXDropdown extends TestNGBaseTest{
	
	/*
	 * Testers use 3 approaches for Dropdown controls in web test automation using Selenium.
	 * 1. Using Selenium's Select class as it provides higher level methods.
	 * 2. Using sendKeys() method of WebElement.
	 * 3. (Especially for custom select controls) - Click the drop down control and then click the option.
	 * 
	 * Arjuna tries to cater to all of them with a single abstraction - its DropDown object.
	 */
	
	@Test
	public void test1WordPressDropDownDefaultWay() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		WPLoginLogout.login(automator);

		automator.Element(With.linkText("Settings")).click();
		
		DropDown roleSelect = automator.DropDown(With.id("default_role"));
		roleSelect.selectByValue("editor");
		
		roleSelect.selectByVisibleText("Subscriber");
		System.out.println(roleSelect.hasVisibleTextSelected("Subscriber"));
		System.out.println(roleSelect.hasValueSelected("subscriber"));
		System.out.println(roleSelect.hasIndexSelected(2));
		System.out.println(roleSelect.getFirstSelectedOptionText());
		
		roleSelect.selectByIndex(4);
		
		WPLoginLogout.logout(automator);
	}
	
	@Test
	public void test1WordPressDropDownSendText() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		WPLoginLogout.login(automator);

		automator.Element(With.linkText("Settings")).click();
		
		DropDown roleSelect = automator.DropDown(With.id("default_role"));
		String text = "Subscriber";
		
		roleSelect.sendOptionText(text);
		assertTrue(roleSelect.hasVisibleTextSelected("Subscriber"), "Expected option not selected.");
		
		WPLoginLogout.logout(automator);
	}
	
	@Test
	public void test5CustomOptionIdentifierDefaultWay() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.dropdown.url").asString());

		GuiActionConfigBuilder confBuilder = new GuiActionConfigBuilder();
		GuiActionConfig config = confBuilder.checkType(false).checkPostState(false).build();
		
		DropDown dropdown = automator.DropDown(With.id("DropDown")).configure(config);
		dropdown.setOptionContainer(With.className("dropdown"));
		dropdown.setOptionLocators(With.className("dropdown-item"));
		dropdown.selectByIndex(2);

		automator.quit();
	}
	
}
