package gwt.material.design.client.type;

/**
 * Types of Collection<br>
 * - AVATAR<br>
 * - DISMISSABLE<br>
 * @author kevzlou7979
 *
 */
public enum CollectionType {
	AVATAR("avatar");
	
	String value;
	CollectionType(String value){
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
	public static CollectionType fromString(String text) {
	    if (text != null) {
	      for (CollectionType b : CollectionType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
}
