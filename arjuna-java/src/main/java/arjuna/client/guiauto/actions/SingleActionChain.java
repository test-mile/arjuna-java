package arjuna.client.guiauto.actions;

import java.util.ArrayList;
import java.util.List;

import arjuna.client.core.action.PartialActionType;
import arjuna.client.guiauto.automator.AppAutomator;
import arjuna.tpi.enums.ModifierKey;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.helpers.ActionChain;
import arjuna.tpi.guiauto.helpers.CompositeAction;
import arjuna.tpi.guiauto.helpers.Keyboard.KeyChord;
import arjuna.tpi.guiauto.helpers.Screen.Offset;
import arjuna.tpi.guiauto.helpers.Screen.Point;
import arjuna.tpi.helpers.Time;

public class SingleActionChain implements ActionChain{
	private List<PartialAction> partialActions = new ArrayList<PartialAction>();
	private AppAutomator automator;
	
	public SingleActionChain(AppAutomator automator) {
		this.automator = automator;
	}
	
	private ActionChain defaultAction(PartialActionType type) throws Exception{
		PartialAction action = new PartialAction(type);
		partialActions.add(action);
		return this;		
	}
	
	private ActionChain defaultPointAction(PartialActionType type, Point xy) throws Exception{
		PartialAction action = new PartialAction(type);
		action.addTargetPoint(xy);
		partialActions.add(action);
		return this;		
	}
	
	private ActionChain defaultElementAction(PartialActionType type, GuiElement element) throws Exception{
		PartialAction action = new PartialAction(type);
		action.addTargetGuiElement(element);
		partialActions.add(action);
		return this;		
	}

	@Override
	public ActionChain click() throws Exception {
		return defaultAction(PartialActionType.CLICK);
	}

	@Override
	public ActionChain click(Point xy) throws Exception {
		return defaultPointAction(PartialActionType.CLICK, xy);
	}

	@Override
	public ActionChain click(GuiElement element) throws Exception {
		return defaultElementAction(PartialActionType.CLICK, element);
	}

	@Override
	public ActionChain press() throws Exception {
		return defaultAction(PartialActionType.CLICK_AND_HOLD);
	}

	@Override
	public ActionChain press(Point xy) throws Exception {
		return defaultPointAction(PartialActionType.CLICK_AND_HOLD, xy);
	}

	@Override
	public ActionChain press(GuiElement element) throws Exception {
		return defaultElementAction(PartialActionType.CLICK_AND_HOLD, element);
	}

	@Override
	public ActionChain release() throws Exception {
		return defaultAction(PartialActionType.MOUSE_UP);
	}

	@Override
	public ActionChain rightClick() throws Exception {
		return defaultAction(PartialActionType.RIGHT_CLICK);
	}

	@Override
	public ActionChain rightClick(Point xy) throws Exception {
		return defaultPointAction(PartialActionType.RIGHT_CLICK, xy);
	}

	@Override
	public ActionChain rightClick(GuiElement element) throws Exception {
		return defaultElementAction(PartialActionType.RIGHT_CLICK, element);
	}

	@Override
	public ActionChain doubleClick() throws Exception {
		return defaultAction(PartialActionType.DOUBLE_CLICK);
	}

	@Override
	public ActionChain doubleClick(Point xy) throws Exception {
		return defaultPointAction(PartialActionType.DOUBLE_CLICK, xy);
	}

	@Override
	public ActionChain doubleClick(GuiElement element) throws Exception {
		return defaultElementAction(PartialActionType.DOUBLE_CLICK, element);
	}
	
	private ActionChain dragAndDropVariant(GuiElement source, Point pointSource, GuiElement dest, Point pointDest, Offset xy) throws Exception {
		PartialAction action = new PartialAction(PartialActionType.DRAG_AND_DROP);
		if(source != null) action.addSourceGuiElement(source);
		if(pointSource != null) action.addSourcePoint(pointSource);
		if(dest != null) action.addTargetGuiElement(dest);
		if(xy != null) action.addOffset(xy);
		if(pointDest != null) action.addTargetPoint(xy);
		partialActions.add(action);
		return this;
	}

	@Override
	public ActionChain dragAndDrop(GuiElement dest) throws Exception {
		return dragAndDropVariant(null, null, dest, null, null);
	}
	
	@Override
	public ActionChain dragAndDrop(Point dest) throws Exception {
		return dragAndDropVariant(null, null, null, dest, null);
	}

	@Override
	public ActionChain dragAndDrop(GuiElement source, GuiElement dest) throws Exception {
		return dragAndDropVariant(source, null, dest, null, null);
	}
	
	@Override
	public ActionChain dragAndDrop(GuiElement source, Point dest) throws Exception {
		return dragAndDropVariant(source, null, null, dest, null);
	}
	
	@Override
	public ActionChain dragAndDrop(Point source, GuiElement dest) throws Exception {
		return dragAndDropVariant(null, source, dest, null, null);
	}
	
	@Override
	public ActionChain dragAndDrop(Point source, Point dest) throws Exception {
		return dragAndDropVariant(null, source, null, dest, null);
	}

	@Override
	public ActionChain dragAndDrop(GuiElement dest, Offset xy) throws Exception {
		return dragAndDropVariant(null, null, dest, null, xy);
	}

	@Override
	public ActionChain dragAndDrop(GuiElement source, GuiElement dest, Offset xy) throws Exception {
		return dragAndDropVariant(source, null, dest, null, xy);
	}
	
	@Override
	public ActionChain dragAndDrop(Point source, GuiElement dest, Offset xy) throws Exception {
		return dragAndDropVariant(null, source, dest, null, xy);
	}

	@Override
	public ActionChain sendText(String text, ModifierKey... keys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionChain sendText(String text) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionChain sendKeys(KeyChord keyChord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ActionChain moveToVariant(GuiElement dest, Point pointDest, Offset xy) throws Exception {
		PartialAction action = new PartialAction(PartialActionType.MOVE_TO);
		if(dest != null) action.addTargetGuiElement(dest);
		if(xy != null) action.addOffset(xy);
		if(pointDest != null) action.addTargetPoint(xy);
		partialActions.add(action);
		return this;
	}

	@Override
	public ActionChain hover(Point xy) throws Exception {
		return moveToVariant(null, xy, null);
	}

	@Override
	public ActionChain hover(Offset offset) throws Exception {
		return moveToVariant(null, null, offset);
	}

	@Override
	public ActionChain hover(GuiElement element) throws Exception {
		return moveToVariant(element, null, null);
	}

	@Override
	public ActionChain hover(GuiElement element, Offset offset) throws Exception {
		return moveToVariant(element, null, offset);
	}

	@Override
	public ActionChain pause(Time time) throws Exception {
		PartialAction action = new PartialAction(PartialActionType.PAUSE);
		action.addTime(time);
		partialActions.add(action);
		return this;
	}

	@Override
	public CompositeAction build() throws Exception {
		return new DefaultCompositeAction(this.automator, this.partialActions);
	}

	@Override
	public void perform() throws Exception {
		CompositeAction action = this.build();
		action.perform();
	}

}
