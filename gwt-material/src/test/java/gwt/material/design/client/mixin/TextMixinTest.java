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
package gwt.material.design.client.mixin;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import gwt.material.design.client.base.DefaultHtmlSanitizer;
import gwt.material.design.client.base.mixin.TextMixin;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.sanitizer.CustomSanitizer;

public class TextMixinTest extends AbstractMixinTest<TextMixin<MaterialLabel>> {

    @Override
    protected void runTest(TextMixin<MaterialLabel> mixin) {
        // Test Defaults
        assertEquals(DefaultHtmlSanitizer.class, TextMixin.getDefaultSanitizer().getClass());
        assertNull(mixin.getSanitizer());

        // Updating DefaultSanitizer
        SimpleHtmlSanitizer simpleHtmlSanitizer = SimpleHtmlSanitizer.getInstance();
        TextMixin.setDefaultSanitizer(simpleHtmlSanitizer);
        assertEquals(simpleHtmlSanitizer, TextMixin.getDefaultSanitizer());

        // Updating CustomSanitizer
        CustomSanitizer customSanitizer = CustomSanitizer.getInstance();
        mixin.setSanitizer(customSanitizer);
        assertEquals(customSanitizer, mixin.getSanitizer());

        // Test setText(String)
        mixin.setText("<b>Test</b>");
        assertEquals("<b>Test</b>", mixin.getText());

        // Test XSS Injection example
        mixin.setText("<script>alert('xss')</script>");
        assertEquals("<script>alert('xss')</script>", mixin.getText());


        // Test setText(SafeHtml)
        String anchorTag = "<a>test</a>";
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        builder.appendEscaped(anchorTag);
        SafeHtml safeHtml = builder.toSafeHtml();
        mixin.setHtml(safeHtml);
        assertEquals(safeHtml, SafeHtmlUtils.fromString(mixin.getText()));
        assertEquals(anchorTag, mixin.getText());

        // Check Null safety when having a null text
        mixin.setText(null);
        assertNull(mixin.getText());

        // Check if we provided an empty text
        mixin.setText("");
        assertEquals("", mixin.getText());
    }

    @Override
    protected TextMixin<MaterialLabel> setupMixin() {
        return new TextMixin<>(new MaterialLabel());
    }
}
