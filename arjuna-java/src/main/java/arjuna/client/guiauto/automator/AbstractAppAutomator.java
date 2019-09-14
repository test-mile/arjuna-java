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

package arjuna.client.guiauto.automator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import arjuna.client.core.action.GuiAutoActionType;
import arjuna.client.core.config.ArjunaComponent;
import arjuna.client.core.connector.BaseSetuObject;
import arjuna.client.core.connector.SetuArg;
import arjuna.client.core.connector.SetuResponse;
import arjuna.client.guiauto.component.GuiAutoComponentFactory;
import arjuna.client.testsession.TestSession;
import arjuna.lib.enums.GuiAutomationContext;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.Alert;
import arjuna.tpi.guiauto.component.Browser;
import arjuna.tpi.guiauto.component.ChildWindow;
import arjuna.tpi.guiauto.component.DomRoot;
import arjuna.tpi.guiauto.component.DropDown;
import arjuna.tpi.guiauto.component.Frame;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.component.GuiMultiElement;
import arjuna.tpi.guiauto.component.MainWindow;
import arjuna.tpi.guiauto.component.RadioGroup;
import arjuna.tpi.test.TestConfig;

public class AbstractAppAutomator extends BaseSetuObject implements AppAutomator {
	private DomRoot domRoot;
	private MainWindow mainWindow;
	private Browser browser;
	private GuiAutomationContext autoContext;
	private TestSession testSession;
	private TestConfig config;
	
	public AbstractAppAutomator() {
		super();
	}

	public AbstractAppAutomator(TestConfig config) {
		this.setConfig(config);
		this.testSession = config.getTestSession();
		this.setTestSessionSetuIdArg(this.testSession.getSetuId());
	}
	
	protected void setAutomationContext(GuiAutomationContext context) {
		this.autoContext = context;
	}
	
	public GuiAutomationContext getAutomationContext() {
		return autoContext;
	}
	
	@Override
	public boolean isGui() {
		return false;
	}

	protected String takeElementFindingAction(GuiAutoActionType actionType, SetuArg... args) throws Exception {
		SetuResponse response = this.sendRequest(ArjunaComponent.GUI_AUTOMATOR, actionType, args);
		return response.getValueForElementSetuId();		
	}

	private String createGenericElement(GuiAutoActionType actionType, With... locators) throws Exception {
		List<Map<String,Object>> arg = new ArrayList<Map<String,Object>>();
		for(With locator: locators) {
			arg.add(locator.asMap());
		}
		return this.takeElementFindingAction(
				actionType,
				SetuArg.arg("locators", arg)
		);	
	}

	@Override
	public GuiElement Element(With... locators) throws Exception {
		String elemSetuId = createGenericElement(GuiAutoActionType.DEFINE_ELEMENT, locators);
		return GuiAutoComponentFactory.Element(this.testSession, this, elemSetuId);
	}

	@Override
	public GuiMultiElement MultiElement(With... locators) throws Exception {
		String elemSetuId = createGenericElement(GuiAutoActionType.DEFINE_MULTIELEMENT, locators);
		return GuiAutoComponentFactory.MultiElement(this.testSession, this, elemSetuId);
	}

	@Override
	public DropDown DropDown(With... locators) throws Exception {
		String elemSetuId = createGenericElement(GuiAutoActionType.DEFINE_DROPDOWN, locators);
		return GuiAutoComponentFactory.DropDown(this.testSession, this, elemSetuId);
	}

	@Override
	public RadioGroup RadioGroup(With... locators) throws Exception {
		String elemSetuId = createGenericElement(GuiAutoActionType.DEFINE_RADIOGROUP, locators);
		return GuiAutoComponentFactory.RadioGroup(this.testSession, this, elemSetuId);
	}

	@Override
	public Alert Alert() throws Exception {
		String elemSetuId = takeElementFindingAction(GuiAutoActionType.DEFINE_ALERT);
		return GuiAutoComponentFactory.Alert(this.testSession, this, elemSetuId);
	}
	
	@Override
	public Frame Frame(With... locators) throws Exception {
		return this.DomRoot().Frame(locators);
	}
	
	@Override
	public ChildWindow childWindow(With... locators) throws Exception {
		return this.mainWindow.childWindow(locators);
	}
	
	@Override
	public ChildWindow LatestChildWindow() throws Exception {
		return this.mainWindow.latestChildWindow();
	}

	@Override
	public void closeAllChildWindows() throws Exception {
		this.mainWindow.closeAllChildWindows();
	}
	
	@Override
	public MainWindow MainWindow() throws Exception {
		return this.mainWindow;
	}
	
	protected void setMainWindow(MainWindow win) throws Exception {
		this.mainWindow = win;
	}

	public TestConfig getConfig() {
		return config;
	}

	protected void setConfig(TestConfig config) {
		this.config = config;
	}
	
	@Override
	public DomRoot DomRoot() {
		return domRoot;
	}

	protected void setDomRoot(DomRoot domRoot) {
		this.domRoot = domRoot;
	}
	
	public TestSession getTestSession() {
		return this.testSession;
	}

	@Override
	public Browser Browser() {
		return this.browser;
	}
	
	protected void setBrowser(Browser browser) throws Exception {
		this.browser = browser;
	}
	
	@Override
	public void enableSlowMotion(boolean on, int interval) throws Exception {
		this.sendRequest(
				ArjunaComponent.GUI_AUTOMATOR,
				GuiAutoActionType.SET_SLOMO,
				SetuArg.arg("on", on),
				SetuArg.arg("interval", interval)
		);
	}
	
	@Override
	public void enableSlowMotion(boolean on) throws Exception {
		this.sendRequest(
				ArjunaComponent.GUI_AUTOMATOR, 
				GuiAutoActionType.SET_SLOMO,
				SetuArg.arg("on", on)
		);
	}
	
	@Override
	public void executeJavaScript(String script) throws Exception {
		this.sendRequest(ArjunaComponent.GUI_AUTOMATOR, 
				GuiAutoActionType.BROWSER_EXECUTE_JAVASCRIPT, 
				SetuArg.arg("script", script)
		);
	}
	
	protected String getSourceOfType(GuiAutoActionType actionType) throws Exception {
		SetuResponse response = this.sendRequest(ArjunaComponent.GUI_AUTOMATOR, actionType);
		return response.getValueForValueAttr();
	}

	@Override
	public String getRootSource() throws Exception {
		return this.getSourceOfType(GuiAutoActionType.GET_ROOT_SOURCE);
	}
	
	@Override
	public String getFullSource() throws Exception {
		return this.getSourceOfType(GuiAutoActionType.GET_FULL_SOURCE);
	}
	
	@Override
	public String getInnerSource() throws Exception {
		return this.getSourceOfType(GuiAutoActionType.GET_INNER_SOURCE);
	}
	
	@Override
	public String getText() throws Exception {
		return this.getSourceOfType(GuiAutoActionType.GET_TEXT);
	}

}