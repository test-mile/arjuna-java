package arjuna.tpi.guiauto.helpers;

import java.util.List;

import arjuna.client.guiauto.actions.PartialAction;

public interface CompositeAction {
	void perform() throws Exception;

	List<PartialAction> getPartialActions();
}
