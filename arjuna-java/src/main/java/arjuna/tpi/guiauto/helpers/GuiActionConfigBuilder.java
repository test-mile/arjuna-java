package arjuna.tpi.guiauto.helpers;

import java.util.HashMap;
import java.util.Map;

import arjuna.lib.enums.GuiActionConfigType;

public class GuiActionConfigBuilder {
	private Map<GuiActionConfigType, Object> settings = new HashMap<GuiActionConfigType, Object>();
	
	public GuiActionConfigBuilder checkType(boolean flag) {
		settings.put(GuiActionConfigType.CHECK_TYPE, flag);
		return this;
	}
	
	public GuiActionConfigBuilder checkPreState(boolean flag) {
		settings.put(GuiActionConfigType.CHECK_PRE_STATE, flag);
		return this;
	}
	
	public GuiActionConfigBuilder checkPostState(boolean flag) {
		settings.put(GuiActionConfigType.CHECK_POST_STATE, flag);
		return this;
	}
	
	public GuiActionConfig build() {
		return new GuiActionConfig(settings);
	}

}
