package gwt.material.design.client.custom;

/**
 * Types of NavBar
 * @author kevzlou7979
 *
 */
public enum NavBarType {

	FIXED("fixed"),
	TALL("tall");
	
	String value;
	NavBarType(String value){
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
	public static NavBarType fromString(String text) {
	    if (text != null) {
	      for (NavBarType b : NavBarType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
	
}
