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

package arjuna.tpi.guiauto;
import arjuna.client.guiauto.gui.BaseGui;

public class DefaultGui extends BaseGui{
	
	public DefaultGui(GuiAutomator automator, String label, String defFileName) throws Exception {
		super(automator, label, defFileName);
	}
	
	public DefaultGui(GuiAutomator automator, String label, String defFileName, Gui parent) throws Exception {
		super(automator, label, defFileName, parent);
	}
}

