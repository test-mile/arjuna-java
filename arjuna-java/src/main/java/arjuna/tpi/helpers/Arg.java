package arjuna.tpi.helpers;

public class Arg {
	private String name;
	private String value;
	
	public Arg(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getValue() {
		return this.value;
	}	

}
