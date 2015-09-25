package gwt.material.design.client.custom;


/**
 * Type of the Sidenav
 * - open
 * - close
 * - minu
 * - clip
 * - float
 * - card
 * @author kevzlou7979
 *
 */
public enum SideNavType{

	OPEN("open"), 
	CLOSE("close"), 
	MINI("mini"),
	CLIP("clip"),
	FLOAT("float"),
	CARD("card");
	
	String value;
	SideNavType(String value){
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
	public static SideNavType fromString(String text) {
	    if (text != null) {
	      for (SideNavType b : SideNavType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
	
}