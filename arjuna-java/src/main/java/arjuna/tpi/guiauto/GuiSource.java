package arjuna.tpi.guiauto;

public interface GuiSource {
	String getRootContent() throws Exception;
	String getInnerContent() throws Exception;
	String getFullContent() throws Exception;
	String getTextContent() throws Exception;
}
