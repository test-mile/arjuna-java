package arjuna.tpi.guiauto.helpers;

public interface MultiActionsBuilder {
	ActionChain chain();
	CompositeAction build();
	void perform();
}
