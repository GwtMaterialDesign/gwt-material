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
package gwt.material.design.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import gwt.material.design.client.resources.MaterialResources;
import gwt.material.design.client.resources.WithJQueryResources;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.animation.AnimationTest;
import gwt.material.design.client.ui.base.helper.ColorHelperTest;
import org.junit.Test;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * Test Wrapper for GMD Components
 *
 * @author kevzlou7979
 */
public class GwtMaterialTestComponent extends GWTTestCase {


    @Override
    public String getModuleName() {
        return "gwt.material.design.GwtMaterialDesign";
    }

    @Override
    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        setup();
    }

    public void setup() {
        WithJQueryResources jquery = GWT.create(WithJQueryResources.class);
        // Test JQuery
        MaterialDesign.injectJs(jquery.jQuery());
        assertTrue(MaterialDesign.isjQueryLoaded());
        // Test Materialize
        MaterialDesign.injectJs(MaterialResources.INSTANCE.materializeJs());
        assertTrue(MaterialDesign.isMaterializeLoaded());
        // gwt-material-jquery Test
        assertNotNull($("body"));
    }

    @Test
    public void testHelpers() {
        new ColorHelperTest();
    }

    @Test
    public void testAnimation() {
        new AnimationTest().init();
    }

    @Test
    public void testBadge() {
        new MaterialBadgeTest().init();
    }

    @Test
    public void testBreadcrumb() {
        new MaterialBreadcrumbTest().init();
    }

    @Test
    public void testButton() {
        new MaterialButtonTest().init();
    }

    @Test
    public void testCard() {
        new MaterialCardTest().init();
    }

    @Test
    public void testCheckBox() {
        new MaterialCheckBoxTest().init();
    }

    @Test
    public void testChip() {
        new MaterialChipTest().init();
    }

    @Test
    public void testCollapsible() {
        new MaterialCollapsibleTest().init();
    }

    @Test
    public void testCollection() {
        new MaterialCollectionTest().init();
    }

    @Test
    public void testDatePicker() {
        new MaterialDatePickerTest().init();
    }

    @Test
    public void testDoubleBox() {
        new MaterialDoubleBoxTest().init();
    }

    @Test
    public void testDropdown() {
        new MaterialDropdownTest().init();
    }

    @Test
    public void testFAB() {
        new MaterialFABTest().init();
    }

    @Test
    public void testFloatBox() {
        new MaterialFloatBoxTest().init();
    }

    @Test
    public void testImage() {
        new MaterialImageTest().init();
    }

    @Test
    public void testIntegerBox() {
        new MaterialIntegerBoxTest().init();
    }

    @Test
    public void testLink() {
        new MaterialLinkTest().init();
    }

    @Test
    public void testListValueBox() {
        new MaterialListValueBoxTest().init();
    }

    @Test
    public void testLoader() {
        new MaterialLoaderTest().init();
    }

    @Test
    public void testLongBox() {
        new MaterialLongBoxTest().init();
    }

    @Test
    public void testModal() {
        new MaterialModalTest().init();
    }

    @Test
    public void testNavBar() {
        new MaterialNavBarTest().init();
    }

    @Test
    public void testParallax() {
        new MaterialParallaxTest().init();
    }

    @Test
    public void testRadioButton() {
        new MaterialRadioButtonTest().init();
    }

    @Test
    public void testRange() {
        new MaterialRangeTest().init();
    }

    @Test
    public void testSearch() {
        new MaterialSearchTest().init();
    }

    @Test
    public void testSideNav() {
        new MaterialSideNavTest().init();
    }

    @Test
    public void testSlider() {
        new MaterialSliderTest().init();
    }

    @Test
    public void testTab() {
        new MaterialTabTest().init();
    }

    @Test
    public void testTextArea() {
        new MaterialTextAreaTest().init();
    }

    @Test
    public void testTextBox() {
        new MaterialTextBoxTest().init();
    }

    @Test
    public void testToast() {
        new MaterialToastTest().init();
    }

    @Test
    public void testTooltip() {
        new MaterialTooltipTest().init();
    }
}
