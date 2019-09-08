package arjuna.tpi.guiauto.component;

import java.util.HashMap;
import java.util.Map;

import arjuna.lib.enums.GuiElementConfigType;

public class GuiElementConfig {
	private Map<GuiElementConfigType, Object> settings = new HashMap<GuiElementConfigType, Object>();

	public GuiElementConfig(Map<GuiElementConfigType, Object> settings) {
		this.settings = settings;
	}
	
	public Map<GuiElementConfigType, Object> getSettings(){
		return this.settings;
	}

}
