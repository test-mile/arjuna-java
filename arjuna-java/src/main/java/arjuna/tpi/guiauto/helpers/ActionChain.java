package arjuna.tpi.guiauto.helpers;

import static arjuna.tpi.guiauto.helpers.Screen.*;

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
	
	ActionChain clickAndHold() throws Exception;
	ActionChain clickAndHold(Point xy) throws Exception;
	ActionChain clickAndHold(GuiElement element) throws Exception;
	
	ActionChain mouseUp() throws Exception;
	
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
	ActionChain dragAndDropByOffset(GuiElement dest, Offset xy) throws Exception;
	ActionChain dragAndDropByOffset(GuiElement source, GuiElement dest, Offset xy) throws Exception;
	ActionChain dragAndDropByOffset(Point source, GuiElement dest, Offset xy) throws Exception;
	
	ActionChain sendTextWithModifierKeys(String text, ModifierKey... keys) throws Exception;
	ActionChain sendText(String text) throws Exception;
	ActionChain sendKeys(KeyChord keyChord) throws Exception;
	
	ActionChain moveTo() throws Exception;
	ActionChain moveTo(Point point) throws Exception;
	ActionChain moveBy(Offset offset) throws Exception;
	ActionChain moveToElement(GuiElement element) throws Exception;
	ActionChain moveToElementByOffset(GuiElement element, Offset offset) throws Exception;
	
	ActionChain pause(Time time) throws Exception;
	
	CompositeAction build() throws Exception;
	void perform() throws Exception;
}
