package gwt.material.design.client.type;

/**
 * Types of CheckBox<br>
 * - FILLED<br>
 * - INTERMEDIATE<br>
 * @author kevzlou7979
 *
 */
public enum CheckBoxType {
	FILLED("filled"), 
	INTERMEDIATE("intermediate");
	
	String value;
	
	CheckBoxType(String value){
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
	public static CheckBoxType fromString(String text) {
	    if (text != null) {
	      for (CheckBoxType b : CheckBoxType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
}
