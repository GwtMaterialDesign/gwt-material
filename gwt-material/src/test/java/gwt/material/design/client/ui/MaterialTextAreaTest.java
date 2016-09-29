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

import org.junit.Test;

/**
 * Test case for Text Area
 *
 * @author kevzlou7979
 */
public class MaterialTextAreaTest extends MaterialValueBoxTest {

    @Test
    public void testTextArea() {
        checkValueBox(new MaterialTextArea());
        checkResizeRule(new MaterialTextArea());
    }

    public <T extends MaterialTextArea> void checkResizeRule(T widget) {
        widget.setResizeRule(MaterialTextArea.ResizeRule.AUTO);
        assertEquals(widget.getResizeRule(), MaterialTextArea.ResizeRule.AUTO);
        widget.setResizeRule(MaterialTextArea.ResizeRule.FOCUS);
        assertEquals(widget.getResizeRule(), MaterialTextArea.ResizeRule.FOCUS);
        widget.setResizeRule(MaterialTextArea.ResizeRule.NONE);
        assertEquals(widget.getResizeRule(), MaterialTextArea.ResizeRule.NONE);
    }
}
