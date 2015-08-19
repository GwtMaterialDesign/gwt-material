package gwt.material.design.client.resources;

public enum Orientation{
	LANDSCAPE("landscape"), 
	PORTRAIT("portrait");
	
	private String value;
	
	Orientation(String value){
		this.setValue(value);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}