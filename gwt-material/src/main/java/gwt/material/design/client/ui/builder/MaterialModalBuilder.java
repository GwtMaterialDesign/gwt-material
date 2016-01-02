package gwt.material.design.client.ui.builder;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.ui.MaterialModal;

/**
 * Created by stefanwasserbauer (wassx) on 29.11.15.
 */
public class MaterialModalBuilder {

	private static MaterialModalBuilder INSTANCE;
	private static MaterialModal materialModal;

	/**
	 * Starts the builder for creating a MaterialModal.
	 * <br>For e.g. <pre>MaterialModalBuilder.create().withWidget(new Label("Test")).isDismissable(true).build())</pre>
	 *
	 * @return an instance of the builder.
	 */
	public static MaterialModalBuilder create() {
		if (INSTANCE == null) {
			INSTANCE = new MaterialModalBuilder();
		}
		if (materialModal == null) {
			materialModal = new MaterialModal();
		}
		materialModal.setDismissable(false);
		materialModal.clear();
		return INSTANCE;
	}

	/**
	 * Adds a widget to the MaterialModal
	 *
	 * @param widget e.g. Label
	 * @return instance of builder
	 */
	public MaterialModalBuilder withWidget(Widget widget) {
		materialModal.add(widget);
		return INSTANCE;
	}

	/**
	 * Sets appearance type of the MaterialModal
	 *
	 * @param type for appearance of modal
	 * @return instance of builder
	 */
	public MaterialModalBuilder withType(ModalType type) {
		materialModal.setType(type);
		return INSTANCE;
	}

	/**
	 * Sets if the MaterialModal is dismissible
	 *
	 * @param isDismissable true/false
	 * @return instance of builder
	 */
	public MaterialModalBuilder isDismissable(boolean isDismissable) {
		materialModal.setDismissable(isDismissable);
		return INSTANCE;
	}

	/**
	 * Finishes the builder flow and creates the MaterialModal
	 *
	 * @return instance of MaterialModal
	 */
	public MaterialModal build() {
		return materialModal;
	}

}
