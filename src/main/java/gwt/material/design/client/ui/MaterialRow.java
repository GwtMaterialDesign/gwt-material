package gwt.material.design.client.ui;

import com.google.gwt.safehtml.shared.SafeHtml;

public class MaterialRow extends MaterialPanel {

	
	public MaterialRow() {
		super("");
	}

	public MaterialRow(SafeHtml safeHtml) {
		super(safeHtml);
		// TODO Auto-generated constructor stub
	}

	public MaterialRow(String tag, String html) {
		super(tag, html);
		// TODO Auto-generated constructor stub
	}

	public MaterialRow(String html) {
		super(html);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("row");
	}

}
