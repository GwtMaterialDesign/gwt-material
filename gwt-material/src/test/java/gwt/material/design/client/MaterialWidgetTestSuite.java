/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
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

import com.google.gwt.junit.tools.GWTTestSuite;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.animation.AnimationTest;
import gwt.material.design.client.ui.helper.ColorHelperTest;
import gwt.material.design.client.ui.helper.EnumHelperTest;
import gwt.material.design.client.ui.helper.ScrollHelperTest;
import gwt.material.design.client.ui.layout.GridLayoutTest;
import gwt.material.design.client.ui.pwa.PwaManagerTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class MaterialWidgetTestSuite extends GWTTestSuite {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test Suite for GMD Widgets");

        // Core / Materialize Widget Tests
        suite.addTestSuite(MaterialBadgeTest.class);
        suite.addTestSuite(MaterialBigDecimalBoxTest.class);
        suite.addTestSuite(MaterialBreadcrumbTest.class);
        suite.addTestSuite(MaterialButtonTest.class);
        suite.addTestSuite(MaterialCardTest.class);
        suite.addTestSuite(MaterialCardTest.class);
        suite.addTestSuite(MaterialCheckBoxTest.class);
        suite.addTestSuite(MaterialChipTest.class);
        suite.addTestSuite(MaterialCollapsibleTest.class);
        suite.addTestSuite(MaterialCollectionTest.class);
        suite.addTestSuite(MaterialDatePickerTest.class);
        suite.addTestSuite(MaterialDialogTest.class);
        suite.addTestSuite(MaterialDoubleBoxTest.class);
        suite.addTestSuite(MaterialDropdownTest.class);
        suite.addTestSuite(MaterialFABTest.class);
        suite.addTestSuite(MaterialFloatBoxTest.class);
        suite.addTestSuite(MaterialImageTest.class);
        suite.addTestSuite(MaterialIntegerBoxTest.class);
        suite.addTestSuite(MaterialLinkTest.class);
        suite.addTestSuite(MaterialListBoxTest.class);
        suite.addTestSuite(MaterialListValueBoxTest.class);
        suite.addTestSuite(MaterialLoaderTest.class);
        suite.addTestSuite(MaterialLongBoxTest.class);
        suite.addTestSuite(MaterialNavBarTest.class);
        suite.addTestSuite(MaterialNavBrandTest.class);
        suite.addTestSuite(MaterialNavSectionTest.class);
        suite.addTestSuite(MaterialPanelTest.class);
        suite.addTestSuite(MaterialParallaxTest.class);
        suite.addTestSuite(MaterialProgressTest.class);
        suite.addTestSuite(MaterialRadioButtonTest.class);
        suite.addTestSuite(MaterialRangeTest.class);
        suite.addTestSuite(MaterialSearchTest.class);
        suite.addTestSuite(MaterialSideNavCardTest.class);
        suite.addTestSuite(MaterialSideNavDrawerTest.class);
        suite.addTestSuite(MaterialSideNavMiniTest.class);
        suite.addTestSuite(MaterialSideNavPushTest.class);
        suite.addTestSuite(MaterialSideNavTest.class);
        suite.addTestSuite(MaterialSliderTest.class);
        suite.addTestSuite(MaterialSwitchTest.class);
        suite.addTestSuite(MaterialTabTest.class);
        suite.addTestSuite(MaterialTextAreaTest.class);
        suite.addTestSuite(MaterialTextBoxTest.class);
        suite.addTestSuite(MaterialToastTest.class);
        suite.addTestSuite(MaterialTooltipTest.class);
        // Progressive Web App (PWA Test)
        suite.addTestSuite(PwaManagerTest.class);
        // Animation Test
        suite.addTestSuite(AnimationTest.class);
        // Helper Util Tests
        suite.addTestSuite(ColorHelperTest.class);
        suite.addTestSuite(EnumHelperTest.class);
        suite.addTestSuite(ScrollHelperTest.class);
        // Layout Tests
        suite.addTestSuite(GridLayoutTest.class);
        return suite;
    }
}
