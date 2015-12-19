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

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.HasDismissable;
import gwt.material.design.client.base.HasTransition;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.ModalType;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

//@formatter:off

/**
 * Dialogs are content that are not original visible on a page but show up with
 * extra information if needed. The transitions should make the appearance of
 * the dialog make sense and not jarring to the user.
 *
 *
 * <p>
 * <h3>UiBinder Usage:</h3>
 *
 * <pre>
 * {@code
 * <m:MaterialModal ui:field="modal" type="FIXED_FOOTER" dismissable="true" inDuration="500" outDuration="800">
 *     <m:MaterialModalContent>
 *         <m:MaterialTitle title="Title" description="Description" />
 *     </m:MaterialModalContent>
 *     <m:MaterialModalFooter>
 *         <m:MaterialButton text="Close Modal" type="FLAT"/>
 *     </m:MaterialModalFooter>
 * </m:MaterialModal>
 * }
 * </pre>
 *
 * *
 * <h3>Java Usage:</h3>
 *
 * <pre>
 * {
 *     &#064;code
 *     &#064;UiField
 *     MaterialModal modal;
 *     modal.openModal();
 * }
 * </pre>
 * 
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#dialogs">Material
 *      Modals</a>
 */
// @formatter:on
public class MaterialModal extends MaterialWidget implements HasType<ModalType>, HasTransition,
    HasDismissable, HasCloseHandlers<MaterialModal> {

    private final CssTypeMixin<ModalType, MaterialModal> typeMixin = new CssTypeMixin<>(this);
    private int inDuration = 300;
    private int outDuration = 200;
    private boolean dismissable = false;
    private double opacity = 0.5;

    public MaterialModal() {
        super(Document.get().createDivElement());
        setStyleName("modal");
    }

    /**
     * Open the modal programatically
     * 
     * <p>
     * Note: the MaterialModal component must be added to the document before
     * calling this method. When declaring this modal on a UiBinder file, the
     * MaterialModal is already added, but if you call it using pure Java, you
     * must add it to a container before opening the modal. You can do it by
     * calling, for example:
     * </p>
     * 
     * <pre>
     * MaterialModal modal = new MaterialModal();
     * RootPanel.get().add(modal);
     * </pre>
     * 
     * @throws IllegalStateException
     *             If the MaterialModal is not added to the document
     */
    public void openModal() {
        // the modal must be added to the document before opening
        if (this.getParent() == null) {
            throw new IllegalStateException(
                "The MaterialModal must be added to the document before calling openModal().");
        }
        openModal(getElement(), opacity, dismissable, inDuration, outDuration);
    }

    /**
     * Open modal with additional properties
     * 
     * @param e
     *            - Modal Component
     * @param opacity
     *            - Opacity of modal background
     * @param dismissable
     *            - Modal can be dismissed by clicking outside of the modal
     * @param inDuration
     *            - Transition in Duration
     * @param outDuration
     *            - Transition out Duration
     */
    private native void openModal(Element e, double opacity, boolean dismissable, int inDuration, int outDuration) /*-{
        var obj = this;
        $wnd.jQuery(e).openModal({
            opacity: opacity,
            dismissible: dismissable,
            in_duration: inDuration,
            out_duration: outDuration,
            complete: function () { obj.@gwt.material.design.client.ui.MaterialModal::onNativeClose(Z)(true); }
        });
    }-*/;

    private void onNativeClose(boolean autoClosed) {
        CloseEvent.fire(this, this, autoClosed);
    }

    /**
     * Close the modal programatically. It is the same as calling
     * {@link #closeModal(boolean)} with <code>false</code> as parameter.
     * <p>
     * Note: you may need to remove it MaterialModal from the document if you
     * are not using UiBinder. See {@link #openModal()}.
     * </p>
     * 
     */
    public void closeModal() {
        closeModal(false);
    }

    /**
     * Close the modal programatically.
     * <p>
     * Note: you may need to remove it MaterialModal from the document if you
     * are not using UiBinder. See {@link #openModal()}.
     * </p>
     * 
     * @param autoClosed
     *            Flag indicating if the modal was automatically dismissed
     * 
     * @see CloseEvent
     */
    public void closeModal(boolean autoClosed) {
        closeModal(getElement(), autoClosed);
    }

    private native void closeModal(Element e, boolean autoClosed) /*-{
        var obj = this;
        $wnd.jQuery(e).closeModal({
            complete: function () { obj.@gwt.material.design.client.ui.MaterialModal::onNativeClose(Z)(autoClosed); }
        });
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

    @Override
    public HandlerRegistration addCloseHandler(CloseHandler<MaterialModal> handler) {
        return this.addHandler(handler, CloseEvent.getType());
    }
}
