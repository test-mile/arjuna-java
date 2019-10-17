package arjuna.client.guiauto.component;

import arjuna.client.core.action.GuiAutoActionType;
import arjuna.client.core.config.ArjunaComponent;
import arjuna.client.core.connector.BaseSetuObject;
import arjuna.client.core.connector.SetuArg;
import arjuna.client.core.connector.SetuConnectUtils;
import arjuna.client.core.connector.SetuResponse;
import arjuna.client.guiauto.automator.AppAutomator;
import arjuna.tpi.guiauto.helpers.GuiSource;
import arjuna.tpi.test.TestConfig;

public class DefaultGuiSource extends BaseSetuObject implements GuiSource{
	
	public DefaultGuiSource(AppAutomator automator, String setuId) {
		this.setTestSessionSetuIdArg(automator.getConfig().getTestSession().getSetuId());
		this.setAutomatorSetuIdArg(automator.getSetuId());
		this.setSetuId(setuId);
	}
	
	public static GuiSource defineSource(AppAutomator automator, SetuArg... args) throws Exception {
		SetuArg[] defArgs = new SetuArg[] {
				SetuArg.arg("testSessionSetuId", automator.getConfig().getTestSession().getSetuId()),
				SetuArg.arg("automatorSetuId", automator.getSetuId())				
		};
		
		SetuResponse response = SetuConnectUtils.sendRequest(
				ArjunaComponent.GUI_AUTOMATOR, 
				GuiAutoActionType.GET_SOURCE,
				SetuArg.combineArrays(defArgs,  args)
		);
		return new DefaultGuiSource(automator, response.getValueForKey("textBlobSetuId").asString());	
	}	
	
	protected String getSourceOfType(GuiAutoActionType actionType) throws Exception {
		SetuResponse response = this.sendRequest(
				ArjunaComponent.GUI_AUTOMATOR, 
				actionType,
				SetuArg.arg("textBlobSetuId", this.getSetuId()));
		return response.getValueForValueAttr();
	}
	
	@Override
	public String getRootContent() throws Exception {
		return this.getSourceOfType(GuiAutoActionType.GET_ROOT_CONTENT);
	}
	
	@Override
	public String getFullContent() throws Exception {
		return this.getSourceOfType(GuiAutoActionType.GET_FULL_CONTENT);
	}
	
	@Override
	public String getInnerContent() throws Exception {
		return this.getSourceOfType(GuiAutoActionType.GET_INNER_CONTENT);
	}
	
	@Override
	public String getTextContent() throws Exception {
		return this.getSourceOfType(GuiAutoActionType.GET_TEXT_CONTENT);
	}

}
