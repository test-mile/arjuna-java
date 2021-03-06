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

package arjuna.tpi.guiauto.component;

import arjuna.client.core.connector.SetuManagedObject;
import arjuna.client.guiauto.component.SourceInquirable;
import arjuna.tpi.guiauto.helpers.GuiActionConfig;

public interface RadioGroup extends SetuManagedObject, SourceInquirable{
	
	boolean hasValueSelected(String value) throws Exception;
	boolean hasIndexSelected(int index) throws Exception;
	String getFirstSelectedOptionValue() throws Exception;
	void selectByValue(String value) throws Exception;
	void selectByIndex(int index) throws Exception;
	RadioGroup configure(GuiActionConfig config) throws Exception;
	
}
