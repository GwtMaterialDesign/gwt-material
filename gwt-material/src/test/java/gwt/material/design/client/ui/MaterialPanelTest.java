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

import gwt.material.design.client.base.HasResize;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.base.MaterialWidgetTestCase;

/**
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialPanelTest extends MaterialWidgetTestCase<MaterialPanel> {

    @Override
    protected MaterialPanel createWidget() {
        return new MaterialPanel();
    }

    public void testContainer() {
        // given
        MaterialPanel panel = getWidget();

        // when / then
        panel.setContainerEnabled(true);
        assertTrue(panel.isContainerEnabed());
        assertTrue(panel.getElement().hasClassName(CssName.CONTAINER));

        panel.setContainerEnabled(false);
        assertFalse(panel.isContainerEnabed());
        assertFalse(panel.getElement().hasClassName(CssName.CONTAINER));
    }

    public void testContentEditable() {
        MaterialPanel panel = getWidget();
        panel.setContentEditable(true);
        assertTrue(panel.isContentEditable());
        assertTrue(panel.getElement().hasAttribute("contenteditable"));
        panel.setContentEditable(false);
        assertFalse(panel.isContentEditable());
    }

    public void testResizable() {
        MaterialPanel panel = getWidget();
        panel.setResize(HasResize.Resizable.BOTH);
        assertEquals(HasResize.Resizable.BOTH.getName(), panel.getResize());
        assertEquals(HasResize.Resizable.BOTH.getName(), panel.getElement().getStyle().getProperty("resize"));
        panel.setResize(HasResize.Resizable.HORIZONTAL);
        assertEquals(HasResize.Resizable.HORIZONTAL.getName(), panel.getElement().getStyle().getProperty("resize"));
    }
}
