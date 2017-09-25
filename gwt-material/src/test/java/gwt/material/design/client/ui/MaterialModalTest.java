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

import java.util.ArrayList;
import java.util.List;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * Test case for Modals
 *
 * @author kevzlou7979
 */
public class MaterialModalTest extends MaterialWidgetTest {

    public void init() {
        MaterialModal modal = new MaterialModal();
        checkModal(modal);
        checkMultipleModalZIndexes();
        checkFullScreenMode(modal);
    }

    protected void checkFullScreenMode(MaterialModal modal) {
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

    protected void checkMultipleModalZIndexes() {
        final int BASE_ZINDEX = 1000;
        List<MaterialModal> modals = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            MaterialModal modal = new MaterialModal();
            modals.add(modal);
            checkModal(modal);
            modal.open();
            // Expected Display : BLOCK
            assertEquals(modal.getElement().getStyle().getDisplay(), Display.BLOCK.getCssName());
            checkZIndex(modal, i, BASE_ZINDEX);
        }
    }

    protected <T extends MaterialModal> void checkZIndex(T modal, int modalIndex, int modalBaseZIndex) {
        assertEquals($(modal.getElement()).css("zIndex"), String.valueOf(modalBaseZIndex + (modalIndex * 2) + 1));
        assertEquals($(".lean-overlay").eq(modalIndex - 1).css("zIndex"), String.valueOf((modalBaseZIndex + (modalIndex * 2))));
    }

    protected <T extends MaterialModal> void checkModal(T modal) {
        generateModalContent(modal);
        checkWidget(modal);
        RootPanel.get().add(modal);
        checkOpenCloseEvent(modal);
        checkType(modal);
        checkDimissible(modal);
        checkDuration(modal);
    }

    protected void checkDuration(MaterialModal modal) {
        final int IN_DURATION = 500;
        final int OUT_DURATION = 800;
        modal.setInDuration(IN_DURATION);
        assertEquals(modal.getInDuration(), IN_DURATION);
        modal.setOutDuration(OUT_DURATION);
        assertEquals(modal.getOutDuration(), OUT_DURATION);
    }

    private void generateModalContent(MaterialModal modal) {
        MaterialModalContent content = new MaterialModalContent();
        MaterialModalFooter footer = new MaterialModalFooter();

        modal.add(content);
        modal.add(footer);

        assertTrue(modal.getWidget(0) instanceof MaterialModalContent);
        assertTrue(modal.getWidget(1) instanceof MaterialModalFooter);
    }

    private <T extends MaterialModal> void checkOpenCloseEvent(T modal) {
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

    public <T extends MaterialModal> void checkType(T modal) {
        modal.setType(ModalType.DEFAULT);
        assertEquals(modal.getType(), ModalType.DEFAULT);
        modal.setType(ModalType.BOTTOM_SHEET);
        assertEquals(modal.getType(), ModalType.BOTTOM_SHEET);
        assertTrue(modal.getElement().hasClassName(ModalType.BOTTOM_SHEET.getCssName()));
        modal.setType(ModalType.FIXED_FOOTER);
        assertEquals(modal.getType(), ModalType.FIXED_FOOTER);
        assertTrue(modal.getElement().hasClassName(ModalType.FIXED_FOOTER.getCssName()));
        modal.setType(ModalType.WINDOW);
        assertEquals(modal.getType(), ModalType.WINDOW);
        assertTrue(modal.getElement().hasClassName(ModalType.WINDOW.getCssName()));
    }

    public <T extends MaterialModal> void checkDimissible(T modal) {
        modal.setDismissible(true);
        assertTrue(modal.isDismissible());
    }
}
