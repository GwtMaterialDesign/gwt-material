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

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.base.MaterialWidgetTest;

/**
 * Test case for Loaders
 *
 * @author kevzlou7979
 */
public class MaterialLoaderTest extends MaterialWidgetTest {

    public void init() {
        RootPanel.get().clear();
        checkLoaderBasic();
        checkLoaderWithinPanel();
        checkProgressBasic();
        checkProgressWithinPanel();
    }

    protected void checkLoaderBasic() {
        checkLoader(RootPanel.get());
    }

    protected void checkLoaderWithinPanel() {
        checkLoader(new MaterialPanel());
    }

    protected void checkProgressBasic() {
        checkProgress(RootPanel.get());
    }

    protected void checkProgressWithinPanel() {
        checkProgress(new MaterialPanel());
    }

    protected void checkLoader(ComplexPanel panel) {
        MaterialLoader.showLoading(true, panel);
        assertNotNull(panel.getWidget(0));
        assertTrue(panel.getWidget(0) instanceof MaterialWidget);
        MaterialWidget loaderWrapper = (MaterialWidget) panel.getWidget(0);
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.LOADER_WRAPPER));
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        assertTrue(loaderWrapper.getWidget(0) instanceof MaterialPreLoader);
        MaterialPreLoader preLoader = (MaterialPreLoader) loaderWrapper.getWidget(0);
        checkPreLoader(preLoader);
        assertEquals(preLoader.getChildren().size(), 4);
        for (Widget w : preLoader.getChildren()) {
            assertTrue(w instanceof MaterialSpinner);
            MaterialSpinner spinner = (MaterialSpinner) w;
            checkSpinner(spinner);
        }

        MaterialLoader.showLoading(false);
        assertEquals(panel.getWidgetCount(), 0);
    }

    protected void checkPreLoader(MaterialPreLoader loader) {
        assertNotNull(loader);
        loader.setSize(LoaderSize.SMALL);
        assertEquals(loader.getSize(), LoaderSize.SMALL);
        loader.setSize(LoaderSize.MEDIUM);
        assertEquals(loader.getSize(), LoaderSize.MEDIUM);
        loader.setSize(LoaderSize.BIG);
        assertEquals(loader.getSize(), LoaderSize.BIG);
    }

    protected void checkSpinner(MaterialSpinner spinner) {
        spinner.setColor(SpinnerColor.YELLOW_ONLY);
        assertEquals(spinner.getColor(), SpinnerColor.YELLOW_ONLY);
        assertTrue(spinner.getElement().hasClassName(SpinnerColor.YELLOW_ONLY.getCssName()));
        spinner.setColor(SpinnerColor.YELLOW);
        assertEquals(spinner.getColor(), SpinnerColor.YELLOW);
        assertTrue(spinner.getElement().hasClassName(SpinnerColor.YELLOW.getCssName()));
    }

    protected void checkProgress(ComplexPanel panel) {
        MaterialLoader.showProgress(true, panel);
        assertNotNull(panel.getWidget(0));
        assertTrue(panel.getWidget(0) instanceof MaterialWidget);
        MaterialWidget loaderWrapper = (MaterialWidget) panel.getWidget(0);
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.PROGRESS_WRAPPER));
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        assertTrue(loaderWrapper.getWidget(0) instanceof MaterialProgress);
        MaterialProgress progress = (MaterialProgress) loaderWrapper.getWidget(0);
        checkProgress(progress);

        MaterialLoader.showLoading(false);
        assertEquals(panel.getWidgetCount(), 0);
    }

    protected void checkProgress(MaterialProgress progress) {
        assertNotNull(progress);
        // Check default type
        progress.setType(ProgressType.INDETERMINATE);
        assertEquals(progress.getType(), ProgressType.INDETERMINATE);
        progress.setType(ProgressType.DETERMINATE);
        assertEquals(progress.getType(), ProgressType.DETERMINATE);

        progress.setPercent(90);
        assertEquals(progress.getPercent(), (double) 90);
        // Get the target widget for percentage update
        assertNotNull(progress.getWidget(0));
        assertTrue(progress.getWidget(0) instanceof MaterialWidget);
        MaterialWidget progressDiv = (MaterialWidget) progress.getWidget(0);
        assertEquals(progressDiv.getElement().getStyle().getWidth(), "90.0%");
        // Check color
        checkColor(progress);
    }
}
