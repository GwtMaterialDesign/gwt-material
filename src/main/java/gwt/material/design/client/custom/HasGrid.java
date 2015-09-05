package gwt.material.design.client.custom;

public interface HasGrid {

	public String grid = "";
	/**
	 * Setting the grid for responsiveness don't forget to build rows first before setting
	 * the grid on each material widgets
	 * @param small
	 * @param medium
	 * @param large
	 */
	public void setGrid(String grid);
	
}
