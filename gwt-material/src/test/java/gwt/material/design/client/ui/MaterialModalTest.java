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

import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * Test case for Modals.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialModalTest extends MaterialWidgetTest<MaterialModal> {

    @Override
    protected MaterialModal createWidget() {
        MaterialModal modal = new MaterialModal();
        MaterialModalContent content = new MaterialModalContent();
        MaterialModalFooter footer = new MaterialModalFooter();

        modal.add(content);
        modal.add(footer);

        assertTrue(modal.getWidget(0) instanceof MaterialModalContent);
        assertTrue(modal.getWidget(1) instanceof MaterialModalFooter);
        return modal;
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.MODAL);
    }

    public void testFullScreenMode() {
        // given
        MaterialModal modal = getWidget();

        // when / then
        // By default fullscreen mode is turned off
        modal.setType(ModalType.DEFAULT);
        assertFalse(modal.isFullscreen());
        // Turn on Fullscreen mode
        modal.setFullscreen(true);
        assertTrue(modal.isFullscreen());
        assertTrue(modal.getElement().hasClassName(CssName.FULLSCREEN));
        // Turn off Fullscreen mode
        modal.setFullscreen(false);
        assertFalse(modal.isFullscreen());
        assertFalse(modal.getElement().hasClassName(CssName.FULLSCREEN));
        // Check for Bottom sheet : Expected not applicable full screen
        modal.setType(ModalType.BOTTOM_SHEET);
        modal.setFullscreen(true);
        assertFalse(modal.isFullscreen());
    }

    public void testMultipleModalZIndexes() {
        final int BASE_ZINDEX = 1000;
        for (int i = 1; i <= 5; i++) {
            MaterialModal modal = new MaterialModal();
            RootPanel.get().add(modal);
            modal.open();
            // Expected Display : BLOCK
            assertEquals(Display.BLOCK.getCssName(), modal.getElement().getStyle().getDisplay());
            checkZIndex(modal, i, BASE_ZINDEX);
        }
    }

    private void checkZIndex(MaterialModal modal, int modalIndex, int modalBaseZIndex) {
        assertEquals(String.valueOf(modalBaseZIndex + (modalIndex * 2) + 1), $(modal.getElement()).css("zIndex"));
        assertEquals(String.valueOf((modalBaseZIndex + (modalIndex * 2))), $(".lean-overlay").eq(modalIndex - 1).css("zIndex"));
    }

    public void testDuration() {
        // given
        MaterialModal modal = getWidget();

        // when / then
        final int IN_DURATION = 500;
        final int OUT_DURATION = 800;
        modal.setInDuration(IN_DURATION);
        assertEquals(IN_DURATION, modal.getInDuration());
        modal.setOutDuration(OUT_DURATION);
        assertEquals(OUT_DURATION, modal.getOutDuration());
    }

    public void testOpenCloseEvent() {
        // given
        MaterialModal modal = getWidget();

        // when / then
        // Check whether overlay is injected
        assertNotNull($(".lean-overlay"));
        // Advance check on open() / close() methods to check whether open / close event fired
        final boolean[] isFiredOpen = {false};
        final boolean[] isFiredClose = {false};
        modal.addOpenHandler(openEvent -> isFiredOpen[0] = true);
        modal.addCloseHandler(closeEvent -> isFiredClose[0] = true);
        fireOpenHandler(modal);
        assertTrue(isFiredOpen[0]);
        fireCloseHandler(modal);
        assertTrue(isFiredClose[0]);
    }

    public void testType() {
        // given
        MaterialModal modal = getWidget();

        // when / then
        modal.setType(ModalType.DEFAULT);
        assertEquals(ModalType.DEFAULT, modal.getType());
        modal.setType(ModalType.BOTTOM_SHEET);
        assertEquals(ModalType.BOTTOM_SHEET, modal.getType());
        assertTrue(modal.getElement().hasClassName(ModalType.BOTTOM_SHEET.getCssName()));
        modal.setType(ModalType.FIXED_FOOTER);
        assertEquals(ModalType.FIXED_FOOTER, modal.getType());
        assertTrue(modal.getElement().hasClassName(ModalType.FIXED_FOOTER.getCssName()));
        modal.setType(ModalType.WINDOW);
        assertEquals(ModalType.WINDOW, modal.getType());
        assertTrue(modal.getElement().hasClassName(ModalType.WINDOW.getCssName()));
    }

    public void testDismissible() {
        // given
        MaterialModal modal = getWidget();

        // when / then
        modal.setDismissible(true);
        assertTrue(modal.isDismissible());

        modal.setDismissible(false);
        assertFalse(modal.isDismissible());
    }

    @Override
    public void testOpacity() {
        // given
        final double INITIAL_OPACITY = 0.5;
        final double FINAL_OPACITY = 0.9;
        MaterialModal modal = getWidget();

        // when
        modal.setOpacity(INITIAL_OPACITY);
        modal.open();

        // then
        assertEquals(INITIAL_OPACITY, modal.getOpacity());
        modal.close();

        // when
        modal.setOpacity(FINAL_OPACITY);
        modal.open();

        // then
        assertEquals(FINAL_OPACITY, modal.getOpacity());
        modal.close();
    }
}
