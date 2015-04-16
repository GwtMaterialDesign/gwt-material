package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSearch extends Composite {

	private static MaterialSearchUiBinder uiBinder = GWT
			.create(MaterialSearchUiBinder.class);

	interface MaterialSearchUiBinder extends UiBinder<Widget, MaterialSearch> {
	}

	public MaterialSearch() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
