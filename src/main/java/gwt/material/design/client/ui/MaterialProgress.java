package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialProgress extends Composite {

	private static MaterialProgressUiBinder uiBinder = GWT.create(MaterialProgressUiBinder.class);

	interface MaterialProgressUiBinder extends UiBinder<Widget, MaterialProgress> {
	}

	public MaterialProgress() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
