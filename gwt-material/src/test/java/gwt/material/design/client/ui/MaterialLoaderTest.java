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
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.LoaderSize;
import gwt.material.design.client.constants.SpinnerColor;

/**
 * Test case for Loaders.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialLoaderTest extends MaterialTestCase {

    public void testLoaderBasic() {
        MaterialLoader.loading(true);
        ComplexPanel panel = RootPanel.get();

        // when / then
        checkLoader(panel);
    }

    public void testLoaderWithinPanel() {
        MaterialPanel panel = new MaterialPanel();
        RootPanel.get().add(panel);
        MaterialLoader.loading(true, panel);

        checkLoader(panel);
    }

    protected void checkLoader(ComplexPanel panel) {
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
}
