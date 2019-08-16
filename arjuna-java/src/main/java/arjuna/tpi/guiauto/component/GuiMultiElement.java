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

public interface GuiMultiElement extends SetuManagedObject{

	GuiElement atIndex(int index) throws Exception;
	GuiElement first() throws Exception;
	GuiElement last() throws Exception;
	GuiElement random() throws Exception;
	int length() throws Exception;	
}