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
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.MaterialTestCase;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Loaders.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialLoaderTest extends MaterialTestCase {

    public void testLoaderBasic() {
        checkLoader(RootPanel.get());
    }

    public void testLoaderWithinPanel() {
        checkLoader(new MaterialPanel());
    }

    public void testProgressBasic() {
        checkProgress(RootPanel.get());
    }

    public void testProgressWithinPanel() {
        checkProgress(new MaterialPanel());
    }

    protected void checkLoader(ComplexPanel panel) {
        MaterialLoader.loading(true, panel);
        assertNotNull(panel.getWidget(0));
        assertTrue(panel.getWidget(0) instanceof MaterialWidget);
        MaterialWidget loaderWrapper = (MaterialWidget) panel.getWidget(0);
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.LOADER_WRAPPER));
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        assertTrue(loaderWrapper.getWidget(0) instanceof MaterialPreLoader);
        MaterialPreLoader preLoader = (MaterialPreLoader) loaderWrapper.getWidget(0);
        checkPreLoader(preLoader);
        assertEquals(4, preLoader.getChildren().size());
        for (Widget w : preLoader.getChildren()) {
            assertTrue(w instanceof MaterialSpinner);
            MaterialSpinner spinner = (MaterialSpinner) w;
            checkSpinner(spinner);
        }

        MaterialLoader.loading(false);
        assertEquals(panel.getWidgetCount(), 0);
    }

    protected void checkPreLoader(MaterialPreLoader loader) {
        assertNotNull(loader);
        loader.setSize(LoaderSize.SMALL);
        assertEquals(LoaderSize.SMALL, loader.getSize());
        loader.setSize(LoaderSize.MEDIUM);
        assertEquals(LoaderSize.MEDIUM, loader.getSize());
        loader.setSize(LoaderSize.BIG);
        assertEquals(LoaderSize.BIG, loader.getSize());
    }

    protected void checkSpinner(MaterialSpinner spinner) {
        spinner.setColor(SpinnerColor.YELLOW_ONLY);
        assertEquals(SpinnerColor.YELLOW_ONLY, spinner.getColor());
        assertTrue(spinner.getElement().hasClassName(SpinnerColor.YELLOW_ONLY.getCssName()));
        spinner.setColor(SpinnerColor.YELLOW);
        assertEquals(SpinnerColor.YELLOW, spinner.getColor());
        assertTrue(spinner.getElement().hasClassName(SpinnerColor.YELLOW.getCssName()));
    }

    protected void checkProgress(ComplexPanel panel) {
        MaterialLoader.progress(true, panel);
        assertNotNull(panel.getWidget(0));
        assertTrue(panel.getWidget(0) instanceof MaterialWidget);
        MaterialWidget loaderWrapper = (MaterialWidget) panel.getWidget(0);
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.PROGRESS_WRAPPER));
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        assertTrue(loaderWrapper.getWidget(0) instanceof MaterialProgress);
        MaterialProgress progress = (MaterialProgress) loaderWrapper.getWidget(0);
        checkProgress(progress);
        checkProgressValue(progress);

        MaterialLoader.loading(false);
        assertEquals(0, panel.getWidgetCount());
    }

    protected void checkProgress(MaterialProgress progress) {
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
