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
import arjuna.tpi.guiauto.component.DomRoot;
import arjuna.tpi.guiauto.component.DropDown;
import arjuna.tpi.guiauto.component.Frame;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.component.RadioGroup;
import arjuna.tpi.guiauto.helpers.GuiActionConfig;
import arjuna.tpi.guiauto.helpers.GuiSource;
import arjuna.tpi.guiauto.helpers.With;
import arjuna.tpi.testengine.TestNGBaseTest;

public class YXElementSource extends TestNGBaseTest{
	
	private void printSources(GuiSource source) throws Exception {
		System.out.println(source.getRootContent());
		System.out.println(source.getFullContent());
		System.out.println(source.getInnerContent());
		System.out.println(source.getTextContent());		
	}
	
	@Test
	public void testGuiElementSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.login.url").asString());
		
		// Based on Text
		GuiElement element = automator.Element(With.id("loginform"));
		element.identify();
		printSources(element.Source());
		automator.quit();
	}
	
	@Test
	public void testDefaultDropDownSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		WPLoginLogout.login(automator);

		automator.Element(With.linkText("Settings")).click();
		
		DropDown dropdown = automator.DropDown(With.id("default_role"));
		printSources(dropdown.Source());
		
		WPLoginLogout.logout(automator);
	}
	
	@Test
	public void testCustomDropDownSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.dropdown.url").asString());

		GuiActionConfig config = GuiActionConfig.builder().checkType(false).checkPostState(false).build();
		
		DropDown dropdown = automator.DropDown(With.id("DropDown")).configure(config);
		dropdown.setOptionContainer(With.className("dropdown"));
		dropdown.setOptionLocators(With.className("dropdown-item"));
		
		printSources(dropdown.Source());
		
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
		
		printSources(radio.Source());
		
		WPLoginLogout.logout(automator);
	}
	
	@Test
	public void testAutomatorSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());		
		
		WPLoginLogout.login(automator);
		
		automator.Element(With.linkText("Posts")).click();
		automator.Element(With.linkText("Add New")).click();
		
		printSources(automator.Source());
		
		//WPLoginLogout.logout(automator);
	}
	
	@Test
	public void testDomRootSourceRetrieval() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());		
		
		WPLoginLogout.login(automator);
		
		automator.Element(With.linkText("Posts")).click();
		automator.Element(With.linkText("Add New")).click();
		
		DomRoot root = automator.DomRoot();
		
		printSources(root.Source());
		
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
		printSources(frame.Source());
		
		WPLoginLogout.logout(automator);
	}
	
}
