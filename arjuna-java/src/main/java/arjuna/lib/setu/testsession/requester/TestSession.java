package arjuna.lib.setu.testsession.requester;

import arjuna.lib.setu.core.requester.connector.SetuManagedObject;
import arjuna.tpi.test.TestConfig;

public interface TestSession extends SetuManagedObject {

	TestConfig init(String rootDir) throws Exception;
	void finish() throws Exception;
	
}
