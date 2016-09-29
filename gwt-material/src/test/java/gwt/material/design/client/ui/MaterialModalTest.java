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

import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import org.junit.Test;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * Test case for Modals
 *
 * @author kevzlou7979
 */
public class MaterialModalTest extends MaterialWidgetTest {

    @Test
    public void testModal() {
        MaterialModal modal = new MaterialModal();
        generateModalContent(modal);
        checkWidget(modal);
        RootPanel.get().add(modal);
        checkOpenCloseEvent(modal);
        checkType(modal);
        checkDimissible(modal);
    }

    private void generateModalContent(MaterialModal modal) {
        MaterialModalContent content = new MaterialModalContent();
        MaterialModalFooter footer = new MaterialModalFooter();

        modal.add(content);
        modal.add(footer);

        assertTrue(modal.getWidget(0) instanceof MaterialModalContent);
        assertTrue(modal.getWidget(1) instanceof MaterialModalFooter);
    }

    private <T extends MaterialModal> void checkOpenCloseEvent(T widget) {
        final boolean[] isOpenFired = new boolean[1];
        widget.addOpenHandler(openEvent -> {
            assertTrue(openEvent.getTarget() == widget);
            isOpenFired[0] = true;
        });
        widget.open();
        assertTrue(isOpenFired[0]);

        // Check whether overlay is injected
        assertNotNull($(".lean-overlay"));

        final boolean[] isCloseFired = new boolean[1];
        widget.addCloseHandler(closeEvent -> {
            assertTrue(closeEvent.getTarget() == widget);
            isCloseFired[0] = true;
        });
        widget.close();
        assertTrue(isCloseFired[0]);
    }

    public <T extends MaterialModal> void checkType(T widget) {
        widget.setType(ModalType.DEFAULT);
        assertEquals(widget.getType(), ModalType.DEFAULT);
        widget.setType(ModalType.BOTTOM_SHEET);
        assertEquals(widget.getType(), ModalType.BOTTOM_SHEET);
        assertTrue(widget.getElement().hasClassName(ModalType.BOTTOM_SHEET.getCssName()));
        widget.setType(ModalType.FIXED_FOOTER);
        assertEquals(widget.getType(), ModalType.FIXED_FOOTER);
        assertTrue(widget.getElement().hasClassName(ModalType.FIXED_FOOTER.getCssName()));
        widget.setType(ModalType.WINDOW);
        assertEquals(widget.getType(), ModalType.WINDOW);
        assertTrue(widget.getElement().hasClassName(ModalType.WINDOW.getCssName()));
    }

    public <T extends MaterialModal> void checkDimissible(T widget) {
        widget.setDismissible(true);
        assertTrue(widget.isDismissible());
    }
}
