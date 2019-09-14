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
import arjuna.tpi.guiauto.component.DomRoot;
import arjuna.tpi.guiauto.component.DropDown;
import arjuna.tpi.guiauto.component.Frame;
import arjuna.tpi.guiauto.component.GuiActionConfig;
import arjuna.tpi.guiauto.component.GuiActionConfigBuilder;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.component.RadioGroup;
import arjuna.tpi.testng.TestNGBaseTest;

public class YXElementSource extends TestNGBaseTest{
	
	@Test
	public void testGuiElementSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.login.url").asString());
		
		// Based on Text
		GuiElement element = automator.Element(With.id("loginform"));
		element.identify();
		System.out.println(element.getRootSource());
		System.out.println(element.getFullSource());
		System.out.println(element.getInnerSource());
		System.out.println(element.getText());

		automator.quit();
	}
	
	@Test
	public void testDefaultDropDownSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		WPLoginLogout.login(automator);

		automator.Element(With.linkText("Settings")).click();
		
		DropDown dropdown = automator.DropDown(With.id("default_role"));
		System.out.println(dropdown.getRootSource());
		System.out.println(dropdown.getFullSource());
		System.out.println(dropdown.getInnerSource());
		System.out.println(dropdown.getText());
		
		WPLoginLogout.logout(automator);
	}
	
	@Test
	public void testCustomDropDownSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.dropdown.url").asString());

		GuiActionConfigBuilder confBuilder = new GuiActionConfigBuilder();
		GuiActionConfig config = confBuilder.checkType(false).checkPostState(false).build();
		
		DropDown dropdown = automator.DropDown(With.id("DropDown")).configure(config);
		dropdown.setOptionContainer(With.className("dropdown"));
		dropdown.setOptionLocators(With.className("dropdown-item"));
		
		System.out.println(dropdown.getRootSource());
		System.out.println(dropdown.getFullSource());
		System.out.println(dropdown.getInnerSource());
		System.out.println(dropdown.getText());
		
		automator.quit();
	}
	
	@Test
	public void testRadioGroupSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		WPLoginLogout.login(automator);
		
		automator.Element(With.linkText("Settings")).click();
		
		RadioGroup radio = automator.RadioGroup(With.name("date_format"));
		System.out.println(radio.hasValueSelected("Y-m-d"));
		System.out.println(radio.hasIndexSelected(1));
		System.out.println(radio.getFirstSelectedOptionValue());
		radio.selectByValue("\\c\\u\\s\\t\\o\\m");
		radio.selectByIndex(2);
		
		System.out.println(radio.getRootSource());
		System.out.println(radio.getFullSource());
		System.out.println(radio.getInnerSource());
		System.out.println(radio.getText());
		
		WPLoginLogout.logout(automator);
	}
	
	@Test
	public void testAutomatorSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());		
		
		WPLoginLogout.login(automator);
		
		automator.Element(With.linkText("Posts")).click();
		automator.Element(With.linkText("Add New")).click();
		
		System.out.println(automator.getRootSource());
		System.out.println(automator.getFullSource());
		System.out.println(automator.getInnerSource());
		System.out.println(automator.getText());
		
		//WPLoginLogout.logout(automator);
	}
	
	@Test
	public void testDomRootSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());		
		
		WPLoginLogout.login(automator);
		
		automator.Element(With.linkText("Posts")).click();
		automator.Element(With.linkText("Add New")).click();
		
		DomRoot root = automator.DomRoot();
		
		System.out.println(root.getRootSource());
		System.out.println(root.getFullSource());
		System.out.println(root.getInnerSource());
		System.out.println(root.getText());
		
		//WPLoginLogout.logout(automator);
	}
	
	@Test
	public void testFrameSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		WPLoginLogout.login(automator);
		
		automator.Element(With.linkText("Posts")).click();
		automator.Element(With.linkText("Add New")).click();
		
		// Frame by identifier and jump to root
		Frame frame = automator.Frame(With.id("content_ifr"));
		frame.focus();
		
		// !!!!!!!!!!!!! This does not work yet. Needs feature development.
		
		System.out.println(frame.getRootSource());
		System.out.println(frame.getFullSource());
		System.out.println(frame.getInnerSource());
		System.out.println(frame.getText());
		
		WPLoginLogout.logout(automator);
	}
	
}
