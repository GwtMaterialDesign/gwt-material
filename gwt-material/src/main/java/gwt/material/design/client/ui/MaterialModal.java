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
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWindowHeader;
import gwt.material.design.client.constants.ModalType;

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
    MaterialModal.showModal(new ModalContent(), ModalType.DEFAULT, false);
    MaterialModal.showModal(new ModalContent(), ModalType.FIXED_FOOTER, false);
    MaterialModal.showModal(new ModalContent(), ModalType.BOTTOM_SHEET, false);
    MaterialModal.showModal(new ModalContent(), ModalType.WINDOW, false);
}
* </pre>
* 
* <h3>Parameters</h3>
* Widget - a widget to attach inside modal<br>
  ModalType - type of modal<br>
  AutoHideEnabled - if true then clicking outside will hide the modal<br>
* </p>
* 
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#badges">Material Badge</a>
*/
//@formatter:on
public class MaterialModal extends Widget {

    private static HTMLPanel panel = new HTMLPanel("");

    /**
     * Show the modal with composite as a content of the modal
     * @param type - Type of modal: FIXED_FOOTER, BOTTOM_SHEET, DEFAULT, WINDOW
     * @param composite - the content of the modal could be any kind of widgets
     * @param autoHideEnabled - set true if you want to be your modal closable when clicking outside
     */
    public static void showModal(Widget composite, ModalType type, final boolean autoHideEnabled) {
        panel.clear();
        confirmType(type);
        panel.add(composite);
        RootPanel.get().add(panel);

        Scheduler.get().scheduleDeferred(new Command() {
            @Override
            public void execute() {
                showModal(panel.getElement(), autoHideEnabled);
            }
        });
    }

    /**
     * Show the window with composite as a content of the modal
     * Its an advance modal with advance features to be added inside like MaterialNavBar, MaterialTabs etc.
     * @param composite - the content of the modal could be any kind of widgets
     * @param type - Type of modal:  WINDOW
     * @param title - Window title of the modal
     * @param headerColor - the color theme of the modal
     * @param autoHideEnabled - set true if you want to be your modal closable when clicking outside
     */
    public static void showWindow(Widget composite, ModalType type, String title,
                                  String headerColor, final boolean autoHideEnabled) {
        panel.clear();
        switch (type) {
            case WINDOW:
                panel.getElement().setId("modal1");
                panel.setStyleName("modal material-window");
                panel.getElement().getStyle().clearBottom();
                panel.add(new MaterialWindowHeader(panel, title, headerColor));
                break;
            default:
                break;
        }
        panel.add(composite);
        RootPanel.get().add(panel);

        Scheduler.get().scheduleDeferred(new Command() {
            @Override
            public void execute() {
                showModal(panel.getElement(), autoHideEnabled);
            }
        });
    }

    /**
     * Setting the modal type for styles and behaviours.
     */
    private static void confirmType(ModalType type) {
        switch (type) {
        case FIXED_FOOTER:
            panel.getElement().setId("modalFix");
            panel.setStyleName("modal modal-fixed-footer");
            panel.getElement().getStyle().clearBottom();
            break;
        case BOTTOM_SHEET:
            panel.getElement().setId("modalBottom");
            panel.getElement().getStyle().setProperty("transform", "");
            panel.setStyleName("modal bottom-sheet");
            break;
        case DEFAULT:
            panel.getElement().setId("modal");
            panel.setStyleName("modal");
            break;
        default:
            break;
        }
    }

    /**
     * Hide the modal.
     */
    public void hide() {
        panel.getElement().removeAttribute("style");
        closeModal();
    }

    /**
     * Show the modal with provided id.
     */
    private static native void showModal(Element e, boolean autoHideEnabled) /*-{
        $wnd.jQuery(e).openModal({
            dismissible: autoHideEnabled
        });
    }-*/;

    /**
     * Closing the modal with provided id.
     */
    public static native void closeModal(Element e) /*-{
        $wnd.jQuery(e).closeModal();
    }-*/;

    /**
     * Close the modal.
     */
    public static void closeModal() {
        panel.clear();
        closeModal(panel.getElement());
    }
}
