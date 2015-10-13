package gwt.material.design.client.type;

/**
 * Types of CheckBox<br>
 * - ACCORDION<br>
 * - EXPANDABLE<br>
 * - POPOUT<br>
 * @author kevzlou7979
 *
 */
public enum CollapsibleType {
	ACCORDION("accordion"), 
	EXPANDABLE("expandable"),
	POPOUT("popout");
	
	String value;
	
	CollapsibleType(String value){
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
	public static CollapsibleType fromString(String text) {
	    if (text != null) {
	      for (CollapsibleType b : CollapsibleType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
}
