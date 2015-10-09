package gwt.material.design.client.type;

/**
 * Types of Icon
 * - CIRCLE
 * @author kevzlou7979
 *
 */
public enum IconType {
	CIRCLE("circle");
	
	String value;
	
	IconType(String value){
		this.value = value;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Get Type from String
	 * @param text
	 * @return Type
	 */
	public static IconType fromString(String text) {
	    if (text != null) {
	      for (IconType b : IconType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	 }
}
