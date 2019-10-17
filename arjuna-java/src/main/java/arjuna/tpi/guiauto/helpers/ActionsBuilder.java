package arjuna.tpi.guiauto.helpers;

public interface ActionsBuilder {
	ActionChain chain();
	CompositeAction build();
	void perform();
}
