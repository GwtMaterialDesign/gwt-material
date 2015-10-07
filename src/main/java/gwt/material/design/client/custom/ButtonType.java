package gwt.material.design.client.custom;

/**
 * Type of the Sidenav
 * - raised
 * - flat
 * - floating
 * @author kevzlou7979
 *
 */
public enum ButtonType {
	RAISED("raised"), 
	FLAT("flat"), 
	FLOATING("floating");
	
	String value;
	
	ButtonType(String value){
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
	public static ButtonType fromString(String text) {
	    if (text != null) {
	      for (ButtonType b : ButtonType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
}
