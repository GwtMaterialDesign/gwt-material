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

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.MaterialTestCase;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.ProgressType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

public class MaterialProgressTest extends MaterialTestCase {

    public void testProgressBasic() {
        MaterialLoader.progress(true);
        ComplexPanel panel = RootPanel.get();

        // when / then
        checkProgress(panel);
    }

    public void testProgressBasicPanel() {
        MaterialPanel panel = new MaterialPanel();
        RootPanel.get().add(panel);
        MaterialLoader.progress(true, panel);

        checkProgress(panel);
    }

    protected void checkProgress(ComplexPanel panel) {
        assertNotNull(panel.getWidget(0));
        assertTrue(panel.getWidget(0) instanceof MaterialWidget);
        MaterialWidget loaderWrapper = (MaterialWidget) panel.getWidget(0);
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.PROGRESS_WRAPPER));
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        assertTrue(loaderWrapper.getWidget(0) instanceof MaterialProgress);
        MaterialProgress progress = (MaterialProgress) loaderWrapper.getWidget(0);
        checkProgressBar(progress);
        checkProgressValue(progress);

        MaterialLoader.loading(false);
        assertEquals(0, panel.getWidgetCount());
    }

    protected void checkProgressBar(MaterialProgress progress) {
        assertNotNull(progress);
        // Check default type
        progress.setType(ProgressType.INDETERMINATE);
        assertEquals(ProgressType.INDETERMINATE, progress.getType());
        progress.setType(ProgressType.DETERMINATE);
        assertEquals(ProgressType.DETERMINATE, progress.getType());

        progress.setPercent(90);
        assertEquals((double) 90, progress.getPercent());
        // Get the target widget for percentage update
        assertNotNull(progress.getWidget(0));
        assertTrue(progress.getWidget(0) instanceof MaterialWidget);
        MaterialWidget progressDiv = (MaterialWidget) progress.getWidget(0);
        assertEquals("90%", progressDiv.getElement().getStyle().getWidth());
        // Check color
        MaterialWidgetTest.checkColor(progress);
    }

    protected void checkProgressValue(MaterialProgress progress) {
        progress.setPercent(50);
        assertEquals((double) 50, progress.getPercent());

        // Check if progress value is less than 0
        progress.setPercent(-10);
        assertEquals((double) 0, progress.getPercent());

        // Check if progress value is greater than 100
        progress.setPercent(100);
        assertEquals((double) 100, progress.getPercent());
    }
}
