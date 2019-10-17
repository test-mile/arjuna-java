package arjuna.tpi.guiauto.helpers;

public interface GuiSource {
	String getRootContent() throws Exception;
	String getInnerContent() throws Exception;
	String getFullContent() throws Exception;
	String getTextContent() throws Exception;
}
