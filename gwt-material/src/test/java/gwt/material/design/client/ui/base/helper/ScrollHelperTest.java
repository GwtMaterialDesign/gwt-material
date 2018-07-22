/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.ui.base.helper;

import gwt.material.design.client.base.helper.ScrollHelper;
import gwt.material.design.client.constants.OffsetPosition;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import gwt.material.design.jquery.client.api.Functions;

import static gwt.material.design.client.js.JsMaterialElement.$;

/**
 * Test case for {@link ScrollHelper}
 *
 * @author kevzlou7979@gmail.com
 */
public class ScrollHelperTest extends MaterialWidgetTest<MaterialPanel> {

    @Override
    protected MaterialPanel createWidget() {
        MaterialPanel container = new MaterialPanel();
        MaterialPanel target = new MaterialPanel();
        container.add(target);
        return container;
    }

    public void testProperties() {
        // UiBinder
        // Given
        MaterialPanel container = getWidget(false);
        MaterialPanel target = (MaterialPanel) container.getWidget(0);

        checkProperties(target, container);

        // Standard
        // Given
        attachWidget();

        checkProperties(target, container);
    }

    protected void checkProperties(MaterialPanel target, MaterialPanel container) {
        final boolean[] firedCallback = {false};
        final String LINEAR_EASING = "linear";
        Functions.Func CALLBACK = () -> {
            firedCallback[0] = true;
        };

        ScrollHelper scrollHelper = new ScrollHelper();

        scrollHelper.setEasing(LINEAR_EASING);
        scrollHelper.setCompleteCallback(CALLBACK);
        scrollHelper.setContainer(container);
        scrollHelper.scrollTo(target);
        checkOffsetPositioning(scrollHelper);

        assertEquals(LINEAR_EASING, scrollHelper.getEasing());
        assertEquals(CALLBACK, scrollHelper.getCompleteCallback());
        assertEquals(container.getElement(), scrollHelper.getContainerElement().asElement());
    }

    protected void checkOffsetPositioning(ScrollHelper scrollHelper) {
        scrollHelper.setOffsetPosition(OffsetPosition.TOP);
        assertEquals(OffsetPosition.TOP, scrollHelper.getOffsetPosition());
        scrollHelper.setOffsetPosition(OffsetPosition.MIDDLE);
        assertEquals(OffsetPosition.MIDDLE, scrollHelper.getOffsetPosition());
        scrollHelper.setOffsetPosition(OffsetPosition.BOTTOM);
        assertEquals(OffsetPosition.BOTTOM, scrollHelper.getOffsetPosition());
    }

    public void testScrollOnTarget() {
        // UiBinder
        // Given
        MaterialPanel container = getWidget();
        MaterialPanel target = (MaterialPanel) container.getWidget(0);
        target.setMarginTop(2000);
        target.setPaddingTop(0);

        double targetOffset = $(target.getElement()).offset().top;
        ScrollHelper scrollHelper = new ScrollHelper();
        scrollHelper.setCompleteCallback(() -> assertEquals(2000.0, targetOffset));

        scrollHelper.scrollTo(target);
    }

    public void testDefaultContainer() {
        MaterialPanel target = new MaterialPanel();
        ScrollHelper scrollHelper = new ScrollHelper();
        scrollHelper.scrollTo(target);

        assertEquals($("html, body").asElement(), scrollHelper.getContainerElement().asElement());
    }

    public void testScrollToOffset() {
        final double OFFSET_TARGET = 3000.0;

        ScrollHelper scrollHelper = new ScrollHelper();
        scrollHelper.scrollTo(OFFSET_TARGET);

        assertEquals(OFFSET_TARGET, scrollHelper.getOffset());
    }
}
