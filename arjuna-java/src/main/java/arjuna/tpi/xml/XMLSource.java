package arjuna.tpi.xml;

public interface XMLSource {
	String getRootContent() throws Exception;
	String getInnerContent() throws Exception;
	String getInnerContent(int start, int end) throws Exception;
	String getFullContent() throws Exception;
	String getFullContent(int start, int end) throws Exception;
	String getText() throws Exception;
	String getText(int start, int end) throws Exception;
}
