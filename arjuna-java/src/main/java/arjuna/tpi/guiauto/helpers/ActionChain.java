package arjuna.tpi.guiauto.helpers;

import arjuna.tpi.enums.ModifierKey;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.helpers.Keyboard.KeyChord;
import arjuna.tpi.guiauto.helpers.Screen.Offset;
import arjuna.tpi.guiauto.helpers.Screen.Point;
import arjuna.tpi.helpers.Time;

public interface ActionChain {
	
	ActionChain click() throws Exception;
	ActionChain click(Point xy) throws Exception;
	ActionChain click(GuiElement element) throws Exception;
	
	ActionChain press() throws Exception;
	ActionChain press(Point xy) throws Exception;
	ActionChain press(GuiElement element) throws Exception;
	
	ActionChain release() throws Exception;
	
	ActionChain rightClick() throws Exception;
	ActionChain rightClick(Point xy) throws Exception;
	ActionChain rightClick(GuiElement element) throws Exception;
	
	ActionChain doubleClick() throws Exception;
	ActionChain doubleClick(Point xy) throws Exception;
	ActionChain doubleClick(GuiElement element) throws Exception;
	
	ActionChain dragAndDrop(GuiElement dest) throws Exception;
	ActionChain dragAndDrop(Point dest) throws Exception;
	ActionChain dragAndDrop(GuiElement source, GuiElement dest) throws Exception;
	ActionChain dragAndDrop(GuiElement source, Point dest) throws Exception;
	ActionChain dragAndDrop(Point source, GuiElement dest) throws Exception;
	ActionChain dragAndDrop(Point source, Point dest) throws Exception;
	ActionChain dragAndDrop(GuiElement dest, Offset xy) throws Exception;
	ActionChain dragAndDrop(GuiElement source, GuiElement dest, Offset xy) throws Exception;
	ActionChain dragAndDrop(Point source, GuiElement dest, Offset xy) throws Exception;
	
	ActionChain sendText(String text, ModifierKey... keys) throws Exception;
	ActionChain sendText(String text) throws Exception;
	ActionChain sendKeys(KeyChord keyChord) throws Exception;
	
	ActionChain hover(Point point) throws Exception;
	ActionChain hover(Offset offset) throws Exception;
	ActionChain hover(GuiElement element) throws Exception;
	ActionChain hover(GuiElement element, Offset offset) throws Exception;
	
	ActionChain pause(Time time) throws Exception;
	
	CompositeAction build() throws Exception;
	void perform() throws Exception;
}
