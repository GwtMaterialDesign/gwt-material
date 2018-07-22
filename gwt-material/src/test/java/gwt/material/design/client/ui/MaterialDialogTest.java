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
import gwt.material.design.client.constants.DialogType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * Test case for Dialogs.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialDialogTest extends MaterialWidgetTest<MaterialDialog> {

    @Override
    protected MaterialDialog createWidget() {
        MaterialDialog dialog = new MaterialDialog();
        MaterialDialogContent content = new MaterialDialogContent();
        MaterialDialogFooter footer = new MaterialDialogFooter();

        dialog.add(content);
        dialog.add(footer);

        assertTrue(dialog.getWidget(0) instanceof MaterialDialogContent);
        assertTrue(dialog.getWidget(1) instanceof MaterialDialogFooter);
        return dialog;
    }

    @Override
    public void testInitialClasses() {
        checkInitialClasses(CssName.MODAL);
    }

    public void testFullScreenMode() {
        // UiBinder
        // given
        MaterialDialog dialog = getWidget(false);

        // when / then
        // By default fullscreen mode is turned off
        dialog.setType(DialogType.DEFAULT);
        assertFalse(dialog.isFullscreen());
        // Turn on Fullscreen mode
        dialog.setFullscreen(true);
        assertTrue(dialog.isFullscreen());
        assertTrue(dialog.getElement().hasClassName(CssName.FULLSCREEN));
        // Turn off Fullscreen mode
        dialog.setFullscreen(false);
        assertFalse(dialog.isFullscreen());
        assertFalse(dialog.getElement().hasClassName(CssName.FULLSCREEN));
        // Check for Bottom sheet : Expected not applicable full screen
        dialog.setType(DialogType.BOTTOM_SHEET);
        dialog.setFullscreen(true);
        assertFalse(dialog.isFullscreen());

        // Standard
        // given
        attachWidget();

        // when / then
        // By default fullscreen mode is turned off
        dialog.setType(DialogType.DEFAULT);
        // Turn on Fullscreen mode
        dialog.setFullscreen(true);
        assertTrue(dialog.getElement().hasClassName(CssName.FULLSCREEN));
        // Turn off Fullscreen mode
        dialog.setFullscreen(false);
        assertFalse(dialog.getElement().hasClassName(CssName.FULLSCREEN));
    }

    public void testMultipleDialogZIndexes() {
        final int BASE_ZINDEX = 1000;
        for (int i = 1; i <= 5; i++) {
            MaterialDialog dialog = new MaterialDialog();
            RootPanel.get().add(dialog);
            dialog.open();
            // Expected Display : BLOCK
            assertEquals(Display.BLOCK.getCssName(), dialog.getElement().getStyle().getDisplay());
            /*checkZIndex(dialog, i, BASE_ZINDEX);*/
        }
    }

    private void checkZIndex(MaterialDialog dialog, int dialogIndex, int dialogBaseZIndex) {
        assertEquals(String.valueOf(dialogBaseZIndex + (dialogIndex * 2) + 1), $(dialog.getElement()).css("zIndex"));
        assertEquals(String.valueOf((dialogBaseZIndex + (dialogIndex * 2))), $(".lean-overlay").eq(dialogIndex - 1).css("zIndex"));
    }

    public void testDuration() {
        // given
        MaterialDialog dialog = getWidget();

        // when / then
        final int IN_DURATION = 500;
        final int OUT_DURATION = 800;
        dialog.setInDuration(IN_DURATION);
        assertEquals(IN_DURATION, dialog.getInDuration());
        dialog.setOutDuration(OUT_DURATION);
        assertEquals(OUT_DURATION, dialog.getOutDuration());
    }

    public void testOpenCloseEvent() {
        // given
        MaterialDialog dialog = getWidget();

        // when / then
        // Check whether overlay is injected
        assertNotNull($(".lean-overlay"));
        // Advance check on open() / close() methods to check whether open / close event fired
        final boolean[] isFiredOpen = {false};
        final boolean[] isFiredClose = {false};
        dialog.addOpenHandler(openEvent -> isFiredOpen[0] = true);
        dialog.addCloseHandler(closeEvent -> isFiredClose[0] = true);
        fireOpenHandler(dialog);
        assertTrue(isFiredOpen[0]);
        fireCloseHandler(dialog);
        assertTrue(isFiredClose[0]);
    }

    public void testType() {
        // given
        MaterialDialog dialog = getWidget();

        // when / then
        dialog.setType(DialogType.DEFAULT);
        assertEquals(DialogType.DEFAULT, dialog.getType());
        dialog.setType(DialogType.BOTTOM_SHEET);
        assertEquals(DialogType.BOTTOM_SHEET, dialog.getType());
        assertTrue(dialog.getElement().hasClassName(DialogType.BOTTOM_SHEET.getCssName()));
        dialog.setType(DialogType.FIXED_FOOTER);
        assertEquals(DialogType.FIXED_FOOTER, dialog.getType());
        assertTrue(dialog.getElement().hasClassName(DialogType.FIXED_FOOTER.getCssName()));
        dialog.setType(DialogType.WINDOW);
        assertEquals(DialogType.WINDOW, dialog.getType());
        assertTrue(dialog.getElement().hasClassName(DialogType.WINDOW.getCssName()));
    }

    public void testDismissible() {
        // given
        MaterialDialog dialog = getWidget();

        // when / then
        dialog.setDismissible(true);
        assertTrue(dialog.isDismissible());

        dialog.setDismissible(false);
        assertFalse(dialog.isDismissible());
    }

    @Override
    public void testOpacity() {
        // given
        final double INITIAL_OPACITY = 0.5;
        final double FINAL_OPACITY = 0.9;
        MaterialDialog dialog = getWidget();

        // when
        dialog.setOpacity(INITIAL_OPACITY);
        dialog.open();

        // then
        assertEquals(INITIAL_OPACITY, dialog.getOpacity());
        dialog.close();

        // when
        dialog.setOpacity(FINAL_OPACITY);
        dialog.open();

        // then
        assertEquals(FINAL_OPACITY, dialog.getOpacity());
        dialog.close();
    }
}
