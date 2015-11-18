package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.CssType;
import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.constants.Type;

//@formatter:off

/**
 * Dialogs are content that are not original visible on a page but show up with extra information if needed. The transitions should make the appearance of the dialog make sense and not jarring to the user.
 *
 *
 * <p>
 * <h3>UiBinder Usage:</h3>
 *
 * <pre>
 * {@code
<m:MaterialModal ui:field="modal" type="FIXED_FOOTER" dismissable="true" inDuration="500" outDuration="800">
    <m:MaterialModalContent>
        <m:MaterialTitle title="Title" description="Description" />
    </m:MaterialModalContent>
    <m:MaterialModalFooter>
        <m:MaterialButton text="Close Modal" type="FLAT"/>
    </m:MaterialModalFooter>
</m:MaterialModal>
}
 * </pre>
 *
 * * <h3>Java Usage:</h3>
 *
 * <pre>
 * {@code
@UiField MaterialModal modal;
modal.openModal();
}
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#dialogs">Material Modals</a>
 */
//@formatter:on
public class MaterialModal extends ComplexWidget implements HasType<ModalType>, HasTransition, HasDismissable {

    private final CssTypeMixin<ModalType, MaterialModal> typeMixin = new CssTypeMixin<>(this);
    private int inDuration = 300;
    private int outDuration = 200;
    private boolean dismissable = false;
    private double opacity = 0.5;

    public MaterialModal() {
        super(Document.get().createDivElement());
        setStyleName("modal");
    }

    @Override
    protected void onLoad() {
        super.onLoad();
    }

    /**
     * Open the modal programatically
     */
    public void openModal() {
        openModal(getElement(),opacity, dismissable, inDuration, outDuration);
    }

    /**
     * Open modal with additional properties
     * @param e - Modal Component
     * @param opacity - Opacity of modal background
     * @param dismissable - Modal can be dismissed by clicking outside of the modal
     * @param inDuration - Transition in Duration
     * @param outDuration - Transition out Duration
     */
    private native void openModal(Element e,double opacity, boolean dismissable, int inDuration, int outDuration) /*-{
        $wnd.jQuery(e).openModal({
            opacity: opacity,
            dismissible: dismissable,
            in_duration: inDuration,
            out_duration: outDuration
        });
    }-*/;

    /**
     * Close the modal programatically
     */
    public void closeModal() {
        closeModal(getElement());
    }

    private native void closeModal(Element e) /*-{
        $wnd.jQuery(e).closeModal();
    }-*/;

    @Override
    public void setType(ModalType type) {
        typeMixin.setType(type);
    }

    @Override
    public ModalType getType() {
        return typeMixin.getType();
    }

    @Override
    public void setInDuration(int inDuration) {
        this.inDuration = inDuration;
    }

    @Override
    public void setOutDuration(int outDuration) {
        this.outDuration = outDuration;
    }

    @Override
    public void setDismissable(boolean dismissable) {
        this.dismissable = dismissable;
    }

    @Override
    public boolean isDismissable() {
        return dismissable;
    }

    @Override
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }
}
