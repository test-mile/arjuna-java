package arjuna.lib.setu.core.requester.connector;

import java.util.Arrays;
import java.util.Map;

public class SetuConnectUtils {
	private static SetuRequester setuClient = new DefaultSetuRequester();
	
	public static void setTestSessionSetuIdArg(Map<String, Object> inMap, String id) {
		inMap.put("testSessionSetuId", id);
	}
	
	public static void setAutomatorSetuIdArg(Map<String, Object> inMap, String id) {
		inMap.put("automatorSetuId", id);
	}
	
	public static void setGuiSetuIdArg(Map<String, Object> inMap, String id) {
		inMap.put("guiSetuId", id);
	}
	
	public static void addArgs(Map<String, Object> inMap, SetuArg... args) {
		for(SetuArg arg: args) {
			inMap.put(arg.getName(), arg.getObject());
		}		
	}

	private static void prepareRequestFromMap(SetuRequest request, Map<String, Object> inMap) {
		for(String name: inMap.keySet()) {
			request.addArg(name, inMap.get(name));
		}		
	}
	
	private static void prepareRequestFromArgs(SetuRequest request, SetuArg...args) {
		for(SetuArg arg: args) {
			request.addArg(arg.getName(), arg.getObject());
		}		
	}
	
	private static SetuRequest createRequest(String component, String actionType, Map<String, Object> inMap, SetuArg... args) {
		SetuRequest request = new DefaultSetuRequest(component, actionType);
		if (inMap != null) {
			prepareRequestFromMap(request, inMap);
		}
		prepareRequestFromArgs(request, args);
		return request;
	}
	
	public static <E1 extends Enum<E1>,E2 extends Enum<E2>> SetuResponse sendRequest(E1 component, E2 actionType) throws Exception {
		SetuRequest request = createRequest(component.toString(), actionType.toString(), null);
		return setuClient.post(request);
	}
	
	public static  <E1 extends Enum<E1>,E2 extends Enum<E2>> SetuResponse sendRequest(E1 component, E2 actionType, Map<String, Object> inMap) throws Exception {
		SetuRequest request = createRequest(component.toString(), actionType.toString(), inMap);
		return setuClient.post(request);
	}
	
	public static  <E1 extends Enum<E1>,E2 extends Enum<E2>> SetuResponse sendRequest(E1 component, E2 actionType, SetuArg... args) throws Exception {
		SetuRequest request = createRequest(component.toString(), actionType.toString(), null, args);
		return setuClient.post(request);
	}
	
	public static  <E1 extends Enum<E1>,E2 extends Enum<E2>> SetuResponse sendRequest(E1 component, E2 actionType, Map<String, Object> inMap, SetuArg... args) throws Exception {
		SetuRequest request = createRequest(component.toString(), actionType.toString(), inMap, args);
		SetuResponse resp = setuClient.post(request);
		return resp;
	}
	
	// From Joachim Sauer's Stackoverflow answer: https://stackoverflow.com/a/784842
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
}
