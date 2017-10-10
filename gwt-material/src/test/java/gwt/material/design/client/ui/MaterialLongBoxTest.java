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

/**
 * Test case for Long Box.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialLongBoxTest extends MaterialValueBoxTest<MaterialLongBox> {

    @Override
    protected MaterialLongBox createWidget() {
        return new MaterialLongBox();
    }

    @Override
    public void testValue() {
        // given
        MaterialLongBox longBox = getWidget();

        // when / then
        longBox.setValue(100000l);
        assertEquals(100000l, (long)longBox.getValue());
    }

    public void testValueChangeEvent() {
        // given
        MaterialLongBox longBox = getWidget();

        // when / then
        checkValueChangeEvent(longBox, 100000l,200000l);
    }
}
