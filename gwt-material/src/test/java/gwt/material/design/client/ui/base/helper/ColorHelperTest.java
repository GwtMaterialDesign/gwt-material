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
package gwt.material.design.client.ui.base.helper;

import gwt.material.design.client.base.helper.ColorHelper;
import gwt.material.design.client.constants.Color;
import junit.framework.TestCase;

/**
 * Test case for {@link gwt.material.design.client.base.helper.ColorHelper}.
 */
public class ColorHelperTest extends TestCase {

    public ColorHelperTest() {
        checkFromStyleName();
    }

    private void checkFromStyleName() {
        assertEquals(Color.PINK_LIGHTEN_1,
            ColorHelper.fromStyleName("pink lighten-1", Color.class, Color.DEFAULT));
    }
}
