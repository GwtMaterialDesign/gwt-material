package gwt.material.design.client.ui;

import com.google.gwt.safehtml.shared.SafeHtml;

public class MaterialColumn extends MaterialPanel {

	private String grid = "";
	private String offset = "";
	
	public MaterialColumn(SafeHtml safeHtml) {
		super(safeHtml);
		// TODO Auto-generated constructor stub
	}

	public MaterialColumn(String tag, String html) {
		super(tag, html);
		// TODO Auto-generated constructor stub
	}

	public MaterialColumn(String html) {
		super(html);
		// TODO Auto-generated constructor stub
	}
	
	public MaterialColumn(int small, int medium, int large){
		super("");
		this.addStyleName("s"+small+" m"+medium + " l" + large);
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("col");
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
		this.addStyleName(grid);
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
		this.addStyleName("offset-" + offset);
	}

}
