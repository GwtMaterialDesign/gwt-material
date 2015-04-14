package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialFileInput extends Composite {

	private static MaterialFileInputUiBinder uiBinder = GWT
			.create(MaterialFileInputUiBinder.class);

	interface MaterialFileInputUiBinder extends
			UiBinder<Widget, MaterialFileInput> {
	}

	public MaterialFileInput() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
