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
import arjuna.tpi.guiauto.component.GuiActionConfig;
import arjuna.tpi.guiauto.component.GuiActionConfigBuilder;
import arjuna.tpi.testng.TestNGBaseTest;

public class YXElementConf extends TestNGBaseTest{
	
	@Test
	public void test() throws Exception{
		GuiAutomator automator = Arjuna.createGuiAutomator(this.getTestContext().getConfig());
		
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("narada.ex.elemstate.url").asString());
		
		// The following would fail because we have switched off auto state checking.
		GuiActionConfig conf = GuiActionConfig.builder().checkPreState(false).build();
		
		automator.Element(With.id("target")).configure(conf).click();
		automator.Alert().confirm();
		
		automator.quit();
	}
	
}
