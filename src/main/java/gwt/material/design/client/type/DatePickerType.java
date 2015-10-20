package gwt.material.design.client.type;

/**
 * Types of Collection<br>
 * - AVATAR<br>
 * - DISMISSABLE<br>
 * @author kevzlou7979
 *
 */
public enum DatePickerType {
	DAY("day"),
	MONTH_DAY("month_day"),
	YEAR_MONTH_DAY("year_month_day"),
	YEAR("year");
	
	String value;
	DatePickerType(String value){
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
	public static DatePickerType fromString(String text) {
	    if (text != null) {
	      for (DatePickerType b : DatePickerType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
}
