package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialRange extends Composite {

	private static MaterialRangeUiBinder uiBinder = GWT
			.create(MaterialRangeUiBinder.class);

	interface MaterialRangeUiBinder extends UiBinder<Widget, MaterialRange> {
	}

	public MaterialRange() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
