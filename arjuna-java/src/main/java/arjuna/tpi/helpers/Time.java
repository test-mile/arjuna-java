package arjuna.tpi.helpers;

import java.util.HashMap;
import java.util.Map;

import arjuna.lib.value.AnyRefValue;
import arjuna.tpi.enums.TimeUnit;

public class Time {
	private TimeUnit unit;
	private long value;
	
	private Time(TimeUnit unit, long value) {
		this.unit = unit;
		this.value = value;
	}

	public Map<String,Value> asMap() {
		Map<String,Value> retMap = new HashMap<String,Value>();
		retMap.put("unit", new AnyRefValue(unit));
		retMap.put("value", new AnyRefValue(value));
		return retMap;
	}
}
