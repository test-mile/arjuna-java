package arjuna.tpi.guiauto.component;

import java.util.HashMap;
import java.util.Map;

import arjuna.lib.enums.GuiElementConfigType;

public class GuiElementConfigBuilder {
	private Map<GuiElementConfigType, Object> settings = new HashMap<GuiElementConfigType, Object>();
	
	public GuiElementConfigBuilder checkState(boolean flag) {
		settings.put(GuiElementConfigType.CHECK_STATE, flag);
		return this;
	}
	
	public GuiElementConfigBuilder checkType(boolean flag) {
		settings.put(GuiElementConfigType.CHECK_TYPE, flag);
		return this;
	}	
	
	public GuiElementConfig build() {
		return new GuiElementConfig(settings);
	}

}
