package gwt.material.design.client.ui;

import com.google.gwt.safehtml.shared.SafeHtml;

public class MaterialModalContent extends MaterialPanel{

	public MaterialModalContent(SafeHtml safeHtml) {
		super(safeHtml);
		// TODO Auto-generated constructor stub
	}

	public MaterialModalContent(String tag, String html) {
		super(tag, html);
		// TODO Auto-generated constructor stub
	}

	public MaterialModalContent(String html) {
		super(html);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("modal-content");
	}

	
}
