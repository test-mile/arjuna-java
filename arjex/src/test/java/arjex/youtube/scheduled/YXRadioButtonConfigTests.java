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
import arjuna.tpi.guiauto.component.GuiElementConfig;
import arjuna.tpi.guiauto.component.GuiElementConfigBuilder;
import arjuna.tpi.guiauto.component.RadioGroup;
import arjuna.tpi.testng.TestNGBaseTest;

public class YXRadioButtonConfigTests extends TestNGBaseTest{
	
	@Test
	public void test1DefaultConfig() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.radio.url").asString());

		automator.RadioGroup(With.name("Traditional")).selectByIndex(1);
		
		automator.quit();
	}
	
	@Test
	public void test2TagMixUp() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.radio.url").asString());

		automator.RadioGroup(With.name("Prob1")).selectByIndex(1);
		
		automator.quit();
	}
	
	@Test
	public void test3TypeMixUp() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.radio.url").asString());

		automator.RadioGroup(With.name("Prob2")).selectByIndex(1);
		
		automator.quit();
	}
	
	@Test
	public void test4GroupMixUp() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.radio.url").asString());

		automator.RadioGroup(With.className("Prob3")).selectByIndex(1);
		
		automator.quit();
	}
	
	@Test
	public void test4StateCheckOff() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.radio.url").asString());

		GuiElementConfigBuilder confBuilder = new GuiElementConfigBuilder();
		GuiElementConfig config = confBuilder.checkPreState(false).build();
		
		RadioGroup group = automator.RadioGroup(With.name("Traditional")).configure(config);
		group.selectByIndex(1);
		
		automator.quit();
	}
	
	@Test
	public void test5TagMixUpWithoutStateCheck() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.radio.url").asString());

		GuiElementConfigBuilder confBuilder = new GuiElementConfigBuilder();
		GuiElementConfig config = confBuilder.checkType(false).checkPreState(false).build();
		
		RadioGroup group = automator.RadioGroup(With.name("Prob1")).configure(config);
		group.selectByIndex(1);
		
		//automator.quit();
	}
	
}
