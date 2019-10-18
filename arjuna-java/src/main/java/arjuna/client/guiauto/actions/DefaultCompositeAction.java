package arjuna.client.guiauto.actions;

import java.util.ArrayList;
import java.util.List;

import arjuna.client.guiauto.automator.AppAutomator;
import arjuna.tpi.guiauto.helpers.CompositeAction;

public class DefaultCompositeAction implements CompositeAction{
	private List<PartialAction> partialActions = new ArrayList<PartialAction>();
	private AppAutomator automator;

	public DefaultCompositeAction(AppAutomator automator, List<PartialAction> partialActions) {
		this.automator = automator;
		this.setPartialActions(partialActions);
	}

	@Override
	public void perform() throws Exception {
		this.automator.takeCompositeAction(this);
	}

	@Override
	public List<PartialAction> getPartialActions() {
		return partialActions;
	}

	private void setPartialActions(List<PartialAction> partialActions) {
		this.partialActions = partialActions;
	}

}
