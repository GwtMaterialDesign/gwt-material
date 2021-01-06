/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2021 GwtMaterialDesign
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
package gwt.material.design.client.mixin;

import gwt.material.design.client.base.mixin.StatusTextMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;

public class StatusTextMixinTest extends AbstractMixinTest<StatusTextMixin<MaterialTextBox, MaterialLabel>> {

    @Override
    protected void runTest(StatusTextMixin<MaterialTextBox, MaterialLabel> mixin) {
        // Given
        MaterialLabel label = mixin.getTextObject();
        String SUCCESS_TEXT = "Success";
        String ERROR_TEXT = "Error";
        String HELPER_TEXT = "Helper";

        // When Apply Success Text
        mixin.setSuccessText(SUCCESS_TEXT);

        // Then
        assertEquals(label.getText(), SUCCESS_TEXT);
        assertTrue(label.isVisible());
        assertTrue(label.getElement().hasClassName(CssName.FIELD_SUCCESS_LABEL));

        // When Success Text is Cleared
        mixin.clearSuccessText();

        // Then
        assertEquals("", label.getText());
        assertFalse(label.isVisible());
        assertFalse(label.getElement().hasClassName(CssName.FIELD_SUCCESS_LABEL));

        // When Apply Error Text
        mixin.setErrorText(ERROR_TEXT);

        // Then
        assertEquals(label.getText(), ERROR_TEXT);
        assertTrue(label.isVisible());
        assertTrue(label.getElement().hasClassName(CssName.FIELD_ERROR_LABEL));

        // When Error Text is Cleared
        mixin.clearErrorText();
        assertEquals("", label.getText());
        assertFalse(label.isVisible());
        assertFalse(label.getElement().hasClassName(CssName.FIELD_ERROR_LABEL));

        // When Apply Helper Text
        mixin.setHelperText(HELPER_TEXT);

        // Then
        assertEquals(label.getText(), HELPER_TEXT);
        assertTrue(label.isVisible());
        assertTrue(label.getElement().hasClassName(CssName.FIELD_HELPER_LABEL));

        // When Helper Text is cleared
        mixin.clearHelperText();

        // Then
        assertEquals("", label.getText());
        assertFalse(label.isVisible());
        assertFalse(label.getElement().hasClassName(CssName.FIELD_HELPER_LABEL));

        // Given
        mixin.setSuccessText(SUCCESS_TEXT);
        mixin.setErrorText(ERROR_TEXT);
        mixin.setHelperText(HELPER_TEXT);

        // When Status Text is cleared
        mixin.clearStatusText();

        // Then
        assertFalse(label.getElement().hasClassName(CssName.FIELD_ERROR_LABEL));
        assertFalse(label.getElement().hasClassName(CssName.FIELD_SUCCESS_LABEL));
        assertTrue(label.getElement().hasClassName(CssName.FIELD_HELPER_LABEL));
    }

    @Override
    protected StatusTextMixin<MaterialTextBox, MaterialLabel> setupMixin() {
        return new StatusTextMixin<>(new MaterialTextBox(), new MaterialLabel());
    }
}
