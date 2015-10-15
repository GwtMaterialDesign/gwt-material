package gwt.material.design.client.type;

/**
 * Types of NavBar<br>
 * - FIXED<br>
 * - TALL<br>
 * @author kevzlou7979
 *
 */
public enum ImageType {
	CIRCLE("circle"),
	MATERIALBOXED("materialboxed");
	
	String value;
	ImageType(String value){
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
	public static ImageType fromString(String text) {
	    if (text != null) {
	      for (ImageType b : ImageType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
}
