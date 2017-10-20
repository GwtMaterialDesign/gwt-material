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
 * Test case for Float Box
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialFloatBoxTest extends MaterialValueBoxTest<MaterialFloatBox> {

    @Override
    protected MaterialFloatBox createWidget() {
        return new MaterialFloatBox();
    }

    @Override
    public void testValue() {
        // given
        MaterialFloatBox floatBox = getWidget();

        // when / then
        floatBox.setValue(123f);
        assertEquals(123f, floatBox.getValue());
    }

    public void testValueChangeEvent() {
        // given
        MaterialFloatBox floatBox = getWidget();

        // when / then
        checkValueChangeEvent(floatBox, 1.0f, 2.0f);
    }
}
