package arjuna.tpi.guiauto.component;

import java.util.HashMap;
import java.util.Map;

import arjuna.lib.enums.GuiActionConfigType;

public class GuiActionConfig {
	private Map<GuiActionConfigType, Object> settings = new HashMap<GuiActionConfigType, Object>();

	public GuiActionConfig(Map<GuiActionConfigType, Object> settings) {
		this.settings = settings;
	}
	
	public Map<GuiActionConfigType, Object> getSettings(){
		return this.settings;
	}
	
	public static GuiActionConfigBuilder builder() {
		return new GuiActionConfigBuilder();
	}

}
