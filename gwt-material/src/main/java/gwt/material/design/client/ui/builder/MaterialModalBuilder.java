package gwt.material.design.client.ui.builder;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.ui.MaterialModal;

/**
 * Created by stefanwasserbauer on 29.11.15.
 */
public class MaterialModalBuilder {

	private static MaterialModalBuilder INSTANCE;
	private static MaterialModal materialModal;

	public static MaterialModalBuilder create() {
		INSTANCE = new MaterialModalBuilder();
		materialModal = new MaterialModal();
		return INSTANCE;
	}

	public MaterialModalBuilder withWidget(Widget widget) {
		materialModal.add(widget);
		return INSTANCE;
	}

	public MaterialModalBuilder withType(ModalType type) {
		materialModal.setType(type);
		return INSTANCE;
	}

	public MaterialModalBuilder isDismissable(boolean b) {
		materialModal.setDismissable(b);
		return INSTANCE;
	}

	public MaterialModal build() {
		return materialModal;
	}

}
