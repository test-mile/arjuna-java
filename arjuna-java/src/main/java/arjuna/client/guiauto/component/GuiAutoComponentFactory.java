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

package arjuna.client.guiauto.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import arjuna.client.core.action.GuiAutoActionType;
import arjuna.client.core.config.ArjunaComponent;
import arjuna.client.core.connector.BaseSetuObject;
import arjuna.client.core.connector.SetuArg;
import arjuna.client.core.connector.SetuResponse;
import arjuna.client.guiauto.automator.AppAutomator;
import arjuna.client.testsession.TestSession;
import arjuna.tpi.guiauto.GuiSource;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.*;

public class GuiAutoComponentFactory {

	public static GuiElement Element(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultGuiElement(session, automator, setuId);
	}

	public static GuiMultiElement MultiElement(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultGuiMultiElement(session, automator, setuId);
	}

	public static DropDown DropDown(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultDropDown(session, automator, setuId);
	}

	public static RadioGroup RadioGroup(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultRadioGroup(session, automator, setuId);
	}

	public static Frame Frame(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultFrame(session, automator, setuId);
	}

	public static Alert Alert(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultAlert(session, automator, setuId);
	}

	public static MainWindow MainWindow(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultMainWindow(session, automator, setuId);
	}

	public static DomRoot DomRoot(TestSession session, AppAutomator automator) {
		return new DefaultDomRoot(session, automator);
	}
	
	public static Browser Browser(TestSession session, AppAutomator automator) {
		return new DefaultBrowser(session, automator);
	}
	
	private static class BaseComponent extends BaseSetuObject{
		private AppAutomator automator;
		private TestSession testSession;
		private GuiComponentType compType;

		public BaseComponent(TestSession session, AppAutomator automator, GuiComponentType compType) {
			this.testSession = session;
			this.automator = automator;
			if (automator.isGui()) {
				this.setGuiSetuIdArg(automator.getSetuId());
			} else {
				this.setAutomatorSetuIdArg(automator.getSetuId());	
			}
			this.compType = compType;
			this.setTestSessionSetuIdArg(testSession.getSetuId());
		}
		
		private SetuArg[] getTypeArgArray() {
			return new SetuArg[] {SetuArg.arg("origGuiComponentType", this.compType)};
		}

		protected AppAutomator getAutomator() {
			return this.automator;
		}
		
		protected TestSession getTestSession() {
			return this.testSession;
		}

		protected SetuResponse sendSetuRequest(GuiAutoActionType actionType, SetuArg... args) throws Exception {
			return this.sendRequest(ArjunaComponent.GUI_AUTOMATOR, actionType, SetuArg.combineArrays(getTypeArgArray(), args));
		}
		
		public GuiSource Source() throws Exception {
			return new DefaultGuiSource(this.getAutomator(), this.sendSetuRequest(GuiAutoActionType.GET_SOURCE).getValueForKey("textBlobSetuId").asString());
		}
		
		protected GuiComponentType getComponentType() {
			return this.compType;
		}
	}

	private static class BaseElement extends BaseComponent{
		
		public BaseElement(TestSession session, AppAutomator automator, GuiComponentType compType) {
			super(session, automator, compType);
		}

		public BaseElement(TestSession session, AppAutomator automator, GuiComponentType compType, String elemSetuId) {
			super(session, automator, compType);
			this.setSetuId(elemSetuId);
			this.setSelfSetuIdArg("elementSetuId");
		}
		
		public BaseElement(TestSession testSession, AppAutomator automator, GuiComponentType compType, String elemSetuId, int index) {
			this(testSession, automator, compType, elemSetuId);
			this.addArgs(
					SetuArg.arg("isInstanceAction", true),
					SetuArg.arg("instanceIndex", index)
			);
		}
		
		protected void updateConfig(GuiActionConfig config) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.CONFIGURE, SetuArg.arg("elementConfig", config.getSettings()));
		}

	}

	private static class DefaultGuiElement extends BaseElement implements GuiElement {

		public DefaultGuiElement(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, GuiComponentType.ELEMENT, setuId);
		}
		
		public DefaultGuiElement(TestSession session, AppAutomator automator, String setuId, int index) {
			super(session, automator, GuiComponentType.ELEMENT, setuId, index);
		}
		
		@Override
		public void enterText(String text) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.ENTER_TEXT, SetuArg.textArg(text));
		}
		
		@Override
		public void setText(String text) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.SET_TEXT, SetuArg.textArg(text));
		}

		@Override
		public void click() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.CLICK);
		}
		
		@Override
		public void waitUntilPresent() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.WAIT_UNTIL_PRESENT);
		}

		@Override
		public void waitUntilVisible() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.WAIT_UNTIL_VISIBLE);
		}

		@Override
		public void waitUntilClickable() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.WAIT_UNTIL_CLICKABLE);
		}

		@Override
		public void check() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.CHECK);
		}

		@Override
		public void uncheck() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.UNCHECK);
		}
		
		@Override
		public void identify() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.IDENTIFY);
		}
		
		@Override
		public GuiElement configure(GuiActionConfig config) throws Exception {
			super.updateConfig(config);
			return this;
		}
	}

	private static class DefaultGuiMultiElement extends BaseElement implements GuiMultiElement {

		public DefaultGuiMultiElement(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, GuiComponentType.MULTI_ELEMENT, setuId);
		}

		@Override
		public GuiElement atIndex(int index) {
			return new DefaultGuiElement(this.getTestSession(), this.getAutomator(), this.getSetuId(), index);
		}

		@Override
		public GuiElement first() throws Exception {
			return atIndex(0);
		}

		@Override
		public GuiElement last() throws Exception {
			return atIndex(this.length() - 1);
		}

		@Override
		public GuiElement random() throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.GET_RANDOM_INDEX);
			return atIndex(response.getValue().asInt());
		}
		
		@Override
		public int length() throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.GET_INSTANCE_COUNT);
			return response.getValue().asInt();
		}

	}
	
	private static class MultiElementSelectable extends BaseElement {
		
		protected MultiElementSelectable(TestSession session, AppAutomator automator, GuiComponentType compType, String setuId) {
			super(session, automator, compType, setuId);
		}	
		
		protected List<Map<String,Object>> convertToMap(With... locators) throws Exception{
			List<Map<String,Object>> args = new ArrayList<Map<String,Object>>();
			for(With locator: locators) {
				args.add(locator.asMap());
			}
			return args;
		}
		
		public boolean hasValueSelected(String value) throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.HAS_VALUE_SELECTED, SetuArg.valueArg(value));
			return response.getValueForCheckResult();
		}

		public boolean hasIndexSelected(int index) throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.HAS_INDEX_SELECTED, SetuArg.indexArg(index));
			return response.getValueForCheckResult();
		}
		
		public void selectByValue(String value) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.SELECT_BY_VALUE, SetuArg.valueArg(value));
		}

		public void selectByIndex(int index) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.SELECT_BY_INDEX, SetuArg.indexArg(index));
		}

		public String getFirstSelectedOptionValue() throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.GET_FIRST_SELECTED_OPTION_VALUE);
			return response.getValueForValueAttr();
		}
	}

	private static class DefaultDropDown extends MultiElementSelectable implements DropDown {

		public DefaultDropDown(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, GuiComponentType.DROPDOWN, setuId);
		}
		
		@Override
		public DropDown configure(GuiActionConfig config) throws Exception {
			super.updateConfig(config);
			return this;
		}
		
		@Override
		public DropDown setOptionLocators(With... locators) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.SET_OPTION_LOCATORS, SetuArg.arg("locators", convertToMap(locators)));
			return this;
		}

		@Override
		public DropDown setOptionContainer(With... locators) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.SET_OPTION_CONTAINER, SetuArg.arg("locators", convertToMap(locators)));
			return this;
		}
		
		public String getFirstSelectedOptionText() throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.GET_FIRST_SELECTED_OPTION_TEXT);
			return response.getValueForText();
		}

		@Override
		public boolean hasVisibleTextSelected(String text) throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.HAS_VISIBLE_TEXT_SELECTED, SetuArg.textArg(text));
			return response.getValueForCheckResult();
		}

		@Override
		public void selectByVisibleText(String text) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.SELECT_BY_VISIBLE_TEXT, SetuArg.textArg(text));
		}

		@Override
		public void sendOptionText(String text) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.SEND_OPTION_TEXT, SetuArg.textArg(text));
		}
	}

	private static class DefaultRadioGroup extends MultiElementSelectable implements RadioGroup {

		public DefaultRadioGroup(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, GuiComponentType.RADIOGROUP, setuId);
		}
				
		@Override
		public RadioGroup configure(GuiActionConfig config) throws Exception {
			super.updateConfig(config);
			return this;
		}
	}

	private static class DefaultAlert extends BaseElement implements Alert {

		public DefaultAlert(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, GuiComponentType.ALERT, setuId);
		}

		@Override
		public void confirm() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.CONFIRM);
		}

		@Override
		public void dismiss() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.DISMISS);
		}

		@Override
		public String getText() throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.GET_TEXT);
			return response.getValueForText();
		}

		@Override
		public void sendText(String text) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.SEND_TEXT, SetuArg.textArg(text));
		}

	}

	private static class DefaultBrowser extends BaseComponent implements Browser {

		public DefaultBrowser(TestSession session, AppAutomator automator) {
			super(session, automator, GuiComponentType.BROWSER);
		}

		@Override
		public void goToUrl(String url) throws Exception {
			this.sendSetuRequest(GuiAutoActionType.GO_TO_URL, SetuArg.arg("url", url));	
		}

		@Override
		public void goBack() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.GO_BACK);
		}

		@Override
		public void goForward() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.GO_FORWARD);
		}

		@Override
		public void refresh() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.REFRESH_CONTENT);
		}
	}
	
	private static abstract class BaseFrame extends BaseElement implements Frame {

		protected BaseFrame(TestSession session, AppAutomator automator, GuiComponentType compType, String setuId) {
			super(session, automator, compType, setuId);
			this.setSelfSetuIdArg("elementSetuId");
		}

		protected BaseFrame(TestSession session, AppAutomator automator, GuiComponentType compType) {
			super(session, automator, compType);
			this.setSelfSetuIdArg("elementSetuId");
		}
		
		
		protected List<Map<String,Object>> getLocatorsAsMap(With... locators) throws Exception{
			List<Map<String,Object>> args = new ArrayList<Map<String,Object>>();
			for(With locator: locators) {
				args.add(locator.asMap());
			}
			return args;
		}
		
		@Override
		public void focus() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.FOCUS);
		}
		
		@Override
		public Frame Frame(With... locators) throws Exception {
			SetuResponse response = this.sendRequest(
					ArjunaComponent.GUI_AUTOMATOR, GuiAutoActionType.DEFINE,
					SetuArg.arg("origGuiComponentType", this.getComponentType()),
					SetuArg.arg("elementSetuId", this.getSetuId()),
					SetuArg.arg("defGuiComponentType", GuiComponentType.FRAME),
					SetuArg.arg("locators", getLocatorsAsMap(locators))
			);
			return new DefaultFrame(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}
		
		@Override
		public Frame ParentFrame() throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.GET_PARENT);
			return new DefaultFrame(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}
	}

	private static class DefaultFrame extends BaseFrame implements Frame {

		public DefaultFrame(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, GuiComponentType.FRAME, setuId);
			this.setSelfSetuIdArg("elementSetuId");
		}		

	}

	private static class DefaultDomRoot extends BaseFrame implements DomRoot{
		
		public DefaultDomRoot(TestSession session, AppAutomator automator) {
			super(session, automator, GuiComponentType.DOMROOT);
		}
		
		@Override
		public Frame Frame(With... locators) throws Exception {
			SetuResponse response = this.sendRequest(
					ArjunaComponent.GUI_AUTOMATOR, GuiAutoActionType.DEFINE,
					SetuArg.arg("origGuiComponentType", GuiComponentType.DOMROOT),
					SetuArg.arg("elementSetuId", this.getSetuId()),
					SetuArg.arg("defGuiComponentType", GuiComponentType.FRAME),
					SetuArg.arg("locators", getLocatorsAsMap(locators))
			);
			return new DefaultFrame(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}

		public Frame ParentFrame() throws Exception {
			throw new Exception("DomRoot does not have a parent frame.");
		}
	}

	private static class AbstractBasicWindow extends BaseElement {

		public AbstractBasicWindow(TestSession session, AppAutomator automator, GuiComponentType compType, String setuId) {
			super(session, automator, compType, setuId);
		}
		
		public String getTitle() throws Exception {
			SetuResponse response = this.sendSetuRequest(GuiAutoActionType.GET_TITLE);
			return response.getValueForKey("title").asString();
		}
		
		public void focus() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.FOCUS);
		}
	}

	private static class DefaultChildWindow extends AbstractBasicWindow implements ChildWindow {

		public DefaultChildWindow(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, GuiComponentType.CHILD_WINDOW, setuId);
		}

		@Override
		public void close() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.CLOSE);
		}
		
		@Override
		public MainWindow mainWindow() throws Exception {
			return this.getAutomator().MainWindow();
		}
	}

	private static class DefaultMainWindow extends AbstractBasicWindow implements MainWindow {

		public DefaultMainWindow(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, GuiComponentType.MAIN_WINDOW, setuId);
		}
		
		@Override
		public void maximize() throws Exception {
			this.sendSetuRequest(GuiAutoActionType.MAXIMIZE);
		}
		
		protected String takeElementFindingAction(GuiAutoActionType actionType, SetuArg... args) throws Exception {
			SetuResponse response = this.sendSetuRequest(actionType, args);
			return response.getValueForElementSetuId();		
		}
		
		@Override
		public ChildWindow ChildWindow(With... locators) throws Exception {
			List<Map<String,Object>> arg = new ArrayList<Map<String,Object>>();
			for(With locator: locators) {
				arg.add(locator.asMap());
			}
			SetuResponse response = this.sendRequest(
					ArjunaComponent.GUI_AUTOMATOR, GuiAutoActionType.DEFINE,
					SetuArg.arg("defGuiComponentType", GuiComponentType.CHILD_WINDOW),
					SetuArg.arg("locators", arg)
			);
			return new DefaultChildWindow(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}
		
		@Override
		public ChildWindow ChildWindow() throws Exception {
			SetuResponse response = sendSetuRequest(GuiAutoActionType.GET_LATEST_CHILD_WINDOW);
			return new DefaultChildWindow(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}

		@Override
		public void closeAllChildWindows() throws Exception {
			sendSetuRequest(GuiAutoActionType.CLOSE_ALL_CHILD_WINDOWS);
		}
	}

}


