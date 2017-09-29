/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.FullscreenMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.js.JsModalOptions;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * Dialogs are content that are not original visible on a page but show up with
 * extra information if needed. The transitions should make the appearance of
 * the dialog make sense and not jarring to the user.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code
 * <m:MaterialModal ui:field="modal" type="FIXED_FOOTER" dismissible="true" inDuration="500" outDuration="800">
 *     <m:MaterialModalContent>
 *         <m:MaterialTitle title="Title" description="Description" />
 *     </m:MaterialModalContent>
 *     <m:MaterialModalFooter>
 *         <m:MaterialButton text="Close Modal" type="FLAT"/>
 *     </m:MaterialModalFooter>
 * </m:MaterialModal>
 * }
 * </pre>
 * <p>
 * *
 * <h3>Java Usage:</h3>
 * <p>
 * <pre>
 * {
 *     &#064;code
 *     &#064;UiField
 *     MaterialModal modal;
 *     modal.open();
 * }
 * </pre>
 * <p>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#dialogs">Material Modals</a>
 * @see <a href="https://material.io/guidelines/components/dialogs.html#">Material Design Specification</a>
 */
// @formatter:on
public class MaterialModal extends MaterialWidget implements HasType<ModalType>, HasInOutDurationTransition,
        HasDismissible, HasCloseHandlers<MaterialModal>, HasOpenHandlers<MaterialModal>, HasFullscreen {

    private JsModalOptions options = new JsModalOptions();

    private CssTypeMixin<ModalType, MaterialModal> typeMixin;
    private FullscreenMixin fullscreenMixin;

    public MaterialModal() {
        super(Document.get().createDivElement(), CssName.MODAL);
    }


    @Override
    public void setType(ModalType type) {
        getTypeMixin().setType(type);
    }

    @Override
    public ModalType getType() {
        return getTypeMixin().getType();
    }

    @Override
    public void setInDuration(int inDuration) {
        options.in_duration = inDuration;
    }

    @Override
    public int getInDuration() {
        return options.in_duration;
    }

    @Override
    public void setOutDuration(int outDuration) {
        options.out_duration = outDuration;
    }

    @Override
    public int getOutDuration() {
        return options.out_duration;
    }

    @Override
    public void setDismissible(boolean dismissible) {
        options.dismissible = dismissible;
    }

    @Override
    public boolean isDismissible() {
        return options.dismissible;
    }

    @Override
    public void setOpacity(double opacity) {
        options.opacity = opacity;
    }

    @Override
    public double getOpacity() {
        return options.opacity;
    }

    @Override
    public void setEnabled(boolean enabled) {
        getEnabledMixin().setEnabled(this, enabled);
    }

    @Override
    public void setFullscreen(boolean value) {
        if (getType() != ModalType.BOTTOM_SHEET) {
            getFullscreenMixin().setFullscreen(value);
        }
    }

    @Override
    public boolean isFullscreen() {
        return getFullscreenMixin().isFullscreen();
    }

    /**
     * Open the modal programmatically
     * <p>
     * Note: the MaterialModal component must be added to the document before
     * calling this method. When declaring this modal on a UiBinder file, the
     * MaterialModal is already added, but if you call it using pure Java, you
     * must add it to a container before opening the modal. You can do it by
     * calling, for example:
     * </p>
     * <pre>
     * MaterialModal modal = new MaterialModal();
     * RootPanel.get().add(modal);
     * </pre>
     *
     * @throws IllegalStateException If the MaterialModal is not added to the document
     */
    public void open() {
        open(true);
    }

    /**
     * Open the modal programmatically
     * <p>
     * Note: the MaterialModal component must be added to the document before
     * calling this method. When declaring this modal on a UiBinder file, the
     * MaterialModal is already added, but if you call it using pure Java, you
     * must add it to a container before opening the modal. You can do it by
     * calling, for example:
     * </p>
     * <pre>
     * MaterialModal modal = new MaterialModal();
     * RootPanel.get().add(modal);
     * </pre>
     * @param fireEvent - Flag whether this component fires Open Event
     *
     * @throws IllegalStateException If the MaterialModal is not added to the document
     */
    public void open(boolean fireEvent) {
        // the modal must be added to the document before opening
        if (this.getParent() == null) {
            throw new IllegalStateException(
                    "The MaterialModal must be added to the document before calling open().");
        }
        open(getElement(), fireEvent);
    }

    /**
     * Open modal with additional properties
     *
     * @param e           - Modal Component
     * @param fireEvent   - Flag whether this component fires Open Event
     */
    protected void open(Element e, boolean fireEvent) {
        options.complete = () -> onNativeClose(true, true);
        options.ready = () -> onNativeOpen(fireEvent);
        $(e).openModal(options);
    }

    protected void onNativeOpen(boolean fireEvent) {
        if(fireEvent) {
            OpenEvent.fire(this, this);
        }
    }

    protected void onNativeClose(boolean autoClosed, boolean fireEvent) {
        if (fireEvent) {
            CloseEvent.fire(this, this, autoClosed);
        }
    }

    /**
     * Close the modal programmatically. It is the same as calling
     * {@link #close(boolean)} with <code>false</code> as parameter.
     * <p>
     * Note: you may need to remove it MaterialModal from the document if you
     * are not using UiBinder. See {@link #open()}.
     * </p>
     */
    public void close() {
        close(false);
    }

    /**
     * Close the modal programmatically.
     * <p>
     * Note: you may need to remove it MaterialModal from the document if you
     * are not using UiBinder. See {@link #open()}.
     * </p>
     *
     * @param autoClosed Flag indicating if the modal was automatically dismissed
     * @see CloseEvent
     */
    public void close(boolean autoClosed) {
        close(autoClosed, true);
    }

    /**
     * Close the modal programmatically.
     * <p>
     * Note: you may need to remove it MaterialModal from the document if you
     * are not using UiBinder. See {@link #open()}.
     * </p>
     *
     * @param autoClosed Flag indicating if the modal was automatically dismissed
     * @param fireEvent Flag whether this component fires Close Event
     * @see CloseEvent
     */
    public void close(boolean autoClosed, boolean fireEvent) {
        close(getElement(), autoClosed, fireEvent);
    }

    protected void close(Element e, boolean autoClosed, boolean fireEvent) {
        if (options != null) {
            options.complete = () -> onNativeClose(autoClosed, fireEvent);
            $(e).closeModal(options);
        }
    }

    @Override
    public HandlerRegistration addCloseHandler(CloseHandler<MaterialModal> handler) {
        return addHandler(handler, CloseEvent.getType());
    }

    @Override
    public HandlerRegistration addOpenHandler(OpenHandler<MaterialModal> handler) {
        return addHandler(handler, OpenEvent.getType());
    }

    protected CssTypeMixin<ModalType, MaterialModal> getTypeMixin() {
        if (typeMixin == null) {
            typeMixin = new CssTypeMixin<>(this);
        }
        return typeMixin;
    }

    protected FullscreenMixin getFullscreenMixin() {
        if (fullscreenMixin == null) {
            fullscreenMixin = new FullscreenMixin(this);
        }
        return fullscreenMixin;
    }
}
