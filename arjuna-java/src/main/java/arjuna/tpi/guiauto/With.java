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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arjuna.tpi.arg.Arg;
import arjuna.tpi.enums.ArgsType;

public class With {
	private String withType;
	private Object withValue;
	private boolean isChildLocator = false;
	private With child;
	private boolean formatCalled = false;
	private ArgsType argsType = ArgsType.NAMED;
	private List<Arg> withArgs = null;
	
	private With(WithType withType, Object withValue) throws Exception{
		this.withType = withType.toString();
		if (withType == WithType.CHILD_LOCATOR) {
			if (!(withValue instanceof With)) {
				throw new Exception("For identification with child locator, the argument must be a With object.");
			}
			isChildLocator = true;
			child = (With) withValue;
		} else {
			this.withValue = withValue;	
		}
	}
	
	private void raiseFormatException() throws Exception {
		if (formatCalled) throw new Exception("You can not format a With object more than once.");
	}
	
	public With format(String... args) throws Exception {
		raiseFormatException();
		formatCalled = true;
		argsType = ArgsType.POSITIONAL;
		withArgs = new ArrayList<Arg>();
		for (int i=0; i< args.length; i++) {
			withArgs.add(new PosArg(i + 1, args[i]));
		}	
		return this;
	}
	
	public With format(Arg... args) throws Exception {
		raiseFormatException();
		formatCalled = true;
		argsType = ArgsType.NAMED;
		withArgs = Arrays.asList(args);	
		return this;
	}
	
	public Map<String,Object> asMap() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("withType", this.withType);
		if (!this.isChildLocator) {
			map.put("withValue", this.withValue);
		} else {
			map.put("withValue", this.child.asMap());		
		}
		if (formatCalled) {
			map.put("argsType", argsType);
			map.put("args", withArgs);
		}
		return map;
	}
	
	public static With id(String id) throws Exception {
		return new With(WithType.ID, id);
	}
	
	public static With name(String name) throws Exception {
		return new With(WithType.NAME, name);
	}
	
	public static With className(String name) throws Exception {
		return new With(WithType.CLASS_NAME, name);
	}
	
	public static With tagName(String name) throws Exception {
		return new With(WithType.TAG_NAME, name);
	}
	
	public static With linkText(String text) throws Exception {
		return new With(WithType.LINK_TEXT, text);
	}
	
	public static With partialLinkText(String text) throws Exception {
		return new With(WithType.PARTIAL_LINK_TEXT, text);
	}
	
	public static With cssSelector(String selector) throws Exception {
		return new With(WithType.CSS_SELECTOR, selector);
	}
	
	public static With xpath(String xpath) throws Exception {
		return new With(WithType.XPATH, xpath);
	}
	
	public static With text(String text) throws Exception {
		return new With(WithType.TEXT, text);
	}
	
	public static With title(String title) throws Exception {
		return new With(WithType.TITLE, title);
	}
	
	public static With partialText(String text) throws Exception {
		return new With(WithType.PARTIAL_TEXT, text);
	}
	
	public static With attrValue(String value) throws Exception {
		return new With(WithType.ATTR_VALUE, value);
	}
	
	public static With attrPartialValue(String value) throws Exception {
		return new With(WithType.ATTR_PARTIAL_VALUE, value);
	}
	
	public static With type(String type) throws Exception {
		return new With(WithType.TYPE, type);
	}
	
	public static With value(String value) throws Exception {
		return new With(WithType.VALUE, value);
	}
	
	public static With index(int index) throws Exception {
		return new With(WithType.INDEX, index);
	}
	
	public static With windowTitle(String title) throws Exception {
		return new With(WithType.WINDOW_TITLE, title);
	}
	
	public static With partialWindowTitle(String title) throws Exception {
		return new With(WithType.PARTIAL_WINDOW_TITLE, title);
	}
	
	public static With childLocator(With withObj) throws Exception {
		return new With(WithType.CHILD_LOCATOR, withObj);
	}
	
	public static With gnsName(String name) throws Exception {
		return new With(WithType.GNS_NAME, name);
	}
	
	public static With classNames(String namesString) throws Exception {
		return new With(WithType.CLASS_NAMES, namesString);
	}
	
	public static With classNames(String... names) throws Exception {
		return new With(WithType.CLASS_NAMES, String.join(" ", names));
	}
}

class PosArg extends Arg{
	
	public PosArg(int pos, String value) {
		super(String.valueOf(pos), value);
	}
	
}


enum WithType {
	ID,
	NAME,
	CLASS_NAME, 
	LINK_TEXT, 
	PARTIAL_LINK_TEXT,
	XPATH,
	CSS_SELECTOR, 
	TAG_NAME,
    CLASS_NAMES,
    
    TEXT,
    TITLE,
    PARTIAL_TEXT,
    ATTR_VALUE,
    ATTR_PARTIAL_VALUE,
    TYPE,
    VALUE,
    
	INDEX,
	WINDOW_TITLE,
	PARTIAL_WINDOW_TITLE,
	CHILD_LOCATOR,
	GNS_NAME
}
