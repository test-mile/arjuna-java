package arjuna.client.testsession;

import arjuna.client.core.connector.SetuManagedObject;
import arjuna.tpi.test.TestConfig;

public interface TestSession extends SetuManagedObject {

	TestConfig init(String rootDir) throws Exception;
	void finish() throws Exception;
	
}
