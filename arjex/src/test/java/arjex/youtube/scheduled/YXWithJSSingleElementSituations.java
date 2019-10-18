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

package arjex.youtube.scheduled;

import org.testng.annotations.Test;

import arjuna.tpi.Arjuna;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.helpers.With;
import arjuna.tpi.test.TestContext;
import arjuna.tpi.testengine.TestNGBaseTest;

public class YXWithJSSingleElementSituations extends TestNGBaseTest{
	
	private GuiAutomator automator = null;
	private GuiElement element = null;
	
	protected void setUpMethod(TestContext testContext) throws Exception {
		element = null;
		automator = Arjuna.createGuiAutomator();
		automator.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.login.url").asString());
	}
	
	protected void tearDownMethod(TestContext testContext) throws Exception {
		System.out.println(element.Source().getRootContent());
		element = null;
		automator.quit();
		automator = null;
	}
	
	@Test
	public void testSS() throws Exception{
		element = automator.Element(With.js("return document.getElementById('wp-submit')"));
	}
	
	@Test
	public void testSM() throws Exception{
		element = automator.Element(With.js("return document.getElementsByClassName('input')"));
	}
	
	@Test
	public void testSNull() throws Exception{
		element = automator.Element(With.js("return null"));
	}
	
	@Test
	public void testSUndef() throws Exception{
		element = automator.Element(With.js("return undefined"));
	}
	
	@Test
	public void testSEmptyList() throws Exception{
		element = automator.Element(With.js("return []"));
	}
	
	@Test
	public void testSNonElement() throws Exception{
		element = automator.Element(With.js("return 1"));
	}
	
	@Test
	public void testSNonElementInList() throws Exception{
		element = automator.Element(With.js("return [1,2]"));
	}

}
