/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.js.JsModalOptions;

import java.util.ArrayList;
import java.util.List;

import static gwt.material.design.client.js.JsMaterialElement.$;

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
 *     modal.open();
 * }
 * </pre>
 * 
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#!dialogs">Material
 *      Modals</a>
 */
// @formatter:on
public class MaterialModal extends MaterialWidget implements HasType<ModalType>, HasTransition,
        HasDismissable, HasCloseHandlers<MaterialModal> {

    static class ModalManager {
            private static List<MaterialModal> modals;
            private static final int index = 1010;

            /**
             * Registers the modal and added to static modal lists
             */
        public static void register(MaterialModal modal) {
            if(modals == null) { modals = new ArrayList<>(); }
            modals.add(modal);
            resetZIndex();
        }

        /**
         *  Unregisters the modal and removed it from static modal lists
         */
        public static void unregister(MaterialModal modal) {
            if(modals == null) { modals = new ArrayList<>(); }
            if(modals.remove(modal)) {
                resetZIndex();
            }
        }

        /**
         * Need to reset every time we have register / unregister process
         */
        protected static void resetZIndex(){
            int i = index;
            for(MaterialModal modal : modals) {
                modal.setDepth(i++);
            }
        }
    }

    private final CssTypeMixin<ModalType, MaterialModal> typeMixin = new CssTypeMixin<>(this);
    private int inDuration = 300;
    private int outDuration = 200;
    private boolean dismissable = false;
    private double opacity = 0.5;

    public MaterialModal() {
        super(Document.get().createDivElement(), "modal");
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        close();
    }

    /**
     * Open the modal programmatically
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
    public void open() {
        // the modal must be added to the document before opening
        if (this.getParent() == null) {
            throw new IllegalStateException(
                "The MaterialModal must be added to the document before calling open().");
        }
        open(getElement(), opacity, dismissable, inDuration, outDuration);
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
    protected void open(Element e, double opacity, boolean dismissable, int inDuration, int outDuration) {
        JsModalOptions options = new JsModalOptions();
        options.opacity = opacity;
        options.dismissible = dismissable;
        options.in_duration = inDuration;
        options.out_duration = outDuration;
        options.complete = () -> {
            onNativeClose(true);
        };
        $(e).openModal(options);
        ModalManager.register(this);
    }

    protected void onNativeClose(boolean autoClosed) {
        CloseEvent.fire(this, this, autoClosed);
    }

    /**
     * Close the modal programmatically. It is the same as calling
     * {@link #close(boolean)} with <code>false</code> as parameter.
     * <p>
     * Note: you may need to remove it MaterialModal from the document if you
     * are not using UiBinder. See {@link #open()}.
     * </p>
     * 
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
     * @param autoClosed
     *            Flag indicating if the modal was automatically dismissed
     * 
     * @see CloseEvent
     */
    public void close(boolean autoClosed) {
        close(getElement(), autoClosed);
        ModalManager.unregister(this);
    }

    protected void close(Element e, boolean autoClosed) {
        $(e).closeModal(() -> onNativeClose(autoClosed));
    }

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

    @Override
    public void setEnabled(boolean enabled) {
        getEnabledMixin().setEnabled(this, enabled);
    }
}
