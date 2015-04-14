package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PreLoader extends Composite {

	private static PreLoaderUiBinder uiBinder = GWT
			.create(PreLoaderUiBinder.class);

	interface PreLoaderUiBinder extends UiBinder<Widget, PreLoader> {
	}

	public PreLoader() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	

}
