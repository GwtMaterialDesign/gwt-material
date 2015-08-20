package gwt.material.design.client.custom;

public interface HasError {

	String error = "";
	/**
	 * Errors occur when an app fails to complete what is expected, such as:
	 *  - The app does not understand user input
	 *	- The system or app fails
	 *	- A user intends to run incompatible operations concurrently
	 */
	public void setError(String error);
	
	public void setSuccess(String success);
	
}
