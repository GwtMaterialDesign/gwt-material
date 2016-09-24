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

import com.google.gwt.junit.tools.GWTTestSuite;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Test suite for all GWT Material Widgets
 *
 * @author kevzlou7979
 */
public class GWTMaterialTestSuite extends TestCase {

    public static Test suite() {
        final GWTTestSuite suite = new GWTTestSuite("gwt-material test suite");
        suite.addTestSuite(MaterialWidgetTest.class);
        suite.addTestSuite(MaterialButtonTest.class);
        suite.addTestSuite(MaterialTextBoxTest.class);
        suite.addTestSuite(MaterialIntegerBoxTest.class);
        suite.addTestSuite(MaterialLongBoxTest.class);
        suite.addTestSuite(MaterialFloatBoxTest.class);
        suite.addTestSuite(MaterialDoubleBoxTest.class);
        suite.addTestSuite(MaterialTextAreaTest.class);
        return suite;
    }
}
