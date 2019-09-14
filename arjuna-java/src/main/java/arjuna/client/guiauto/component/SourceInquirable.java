package arjuna.client.guiauto.component;

public interface SourceInquirable {
	String getRootSource() throws Exception;
	String getFullSource() throws Exception;
	String getInnerSource() throws Exception;
	String getText() throws Exception;
}
