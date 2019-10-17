package arjuna.client.guiauto.actions;

import java.util.HashMap;
import java.util.Map;

import arjuna.client.core.action.PartialActionType;
import arjuna.lib.value.AnyRefValue;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.helpers.Keyboard.KeyChord;
import arjuna.tpi.guiauto.helpers.Screen.Offset;
import arjuna.tpi.guiauto.helpers.Screen.Point;
import arjuna.tpi.helpers.Time;
import arjuna.tpi.helpers.Value;

public class PartialAction {
	private PartialActionType actionType;
	private Map<String, Object> args = new HashMap<String,Object>();
	
	public PartialAction(PartialActionType type) {
		this.actionType = type;
	}

	public void addTargetGuiElement(GuiElement element) {
		args.put("targetElement", element.getSetuId());
	}
	
	public void addSourceGuiElement(GuiElement element) {
		args.put("sourceElement", element.getSetuId());
	}
	
	public void addTargetPoint(Point xy) {
		args.put("targetPoint", xy.getLocation());
	}
	
	public void addSourcePoint(Point xy) {
		args.put("sourcePoint", xy.getLocation());
	}
	
	public void addOffset(Offset xy) {
		args.put("offset", xy.getLocation());
	}	
	
	public void addKeyChord(KeyChord chord) {
		args.put("keyChord", chord.getChordParts());
	}
	
	public void addTime(Time time) {
		args.put("keyChord", time.asMap());
	}
	
	public PartialActionType getActionType() {
		return this.actionType;
	}
	
	public Map<String, Object> getArgs() {
		return this.args;
	}
}
