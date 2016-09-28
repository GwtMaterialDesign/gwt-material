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

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CollapsibleType;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import org.junit.Test;

public class MaterialCollapsibleTest extends MaterialWidgetTest {

    @Test
    public void testCollapsible() {
        MaterialCollapsible colaps = new MaterialCollapsible();
        //checkWidget(colaps);
        checkTypes(colaps);
    }

    @Override
    protected <T extends MaterialWidget> void checkChildren(T widget) {
        /*MaterialCollapsibleItem item = new MaterialCollapsibleItem();
        checkWidget(item);

        MaterialCollapsibleHeader header = new MaterialCollapsibleHeader();
        checkWidget(header);

        MaterialCollapsibleBody body = new MaterialCollapsibleBody();
        checkWidget(body);*/
    }

    // TODO Check Type
    protected <T extends MaterialCollapsible> void checkTypes(T widget) {
        widget.setType(CollapsibleType.FLAT);
        assertEquals(widget.getType(), CollapsibleType.FLAT);
        assertTrue(widget.getElement().hasClassName(CollapsibleType.FLAT.getCssName()));
        widget.setType(CollapsibleType.POPOUT);
        assertEquals(widget.getType(), CollapsibleType.POPOUT);
        assertTrue(widget.getElement().hasClassName(CollapsibleType.POPOUT.getCssName()));

        widget.setAccordion(true);
        assertEquals(widget.getElement().getAttribute("data-collapsible"), "");

        widget.setAccordion(false);
        assertTrue(widget.getElement().hasAttribute("data-collapsible"));
        assertEquals(widget.getElement().getAttribute("data-collapsible"), "expandable");

    }

    // TODO Check PreSelection

    // TODO Check Nested Structure

    // TODO Check Secondary Icons
}
