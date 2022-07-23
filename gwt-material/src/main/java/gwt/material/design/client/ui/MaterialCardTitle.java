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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.HtmlSanitizer;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HasText;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.base.HasSafeText;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.html.Span;

//@formatter:off

/**
 * Card Element for card title.
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#cards">Material Cards</a>
 * @see <a href="https://material.io/guidelines/components/cards.html">Material Design Specification</a>
 */
//@formatter:on
public class MaterialCardTitle extends MaterialWidget implements HasIcon, HasSafeText, HasText {

    private MaterialIcon icon = new MaterialIcon();
    private Span titleLabel = new Span();

    public MaterialCardTitle() {
        super(Document.get().createSpanElement(), CssName.CARD_TITLE, CssName.ACTIVATOR);
    }

    @Override
    public String getText() {
        return titleLabel.getText();
    }

    @Override
    public void setText(String text) {
        titleLabel.setText(text);

        if (!titleLabel.isAttached()) {
            add(titleLabel);
        }
    }

    @Override
    public void setHtml(SafeHtml html) {
        titleLabel.setHtml(html);
    }

    @Override
    public void setSanitizer(HtmlSanitizer sanitizer) {
        titleLabel.setSanitizer(sanitizer);
    }

    @Override
    public HtmlSanitizer getSanitizer() {
        return titleLabel.getSanitizer();
    }

    @Override
    public MaterialIcon getIcon() {
        return icon;
    }

    @Override
    public void setIconType(IconType iconType) {
        icon.setIconType(iconType);
        add(icon);
    }

    @Override
    public void setIconPosition(IconPosition position) {
        icon.setIconPosition(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        icon.setIconSize(size);
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        icon.setIconFontSize(size, unit);
    }

    @Override
    public void setIconColor(Color iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public Color getIconColor() {
        return icon.getIconColor();
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        icon.setIconPrefix(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return icon.isIconPrefix();
    }

    @Override
    public void setCustomIconType(String iconType) {
        icon.setCustomIconType(iconType);
    }

    @Override
    public String getCustomIconType() {
        return icon.getCustomIconType();
    }

    @Override
    public void setIconDisplay(IconDisplay iconDisplay) {
        icon.setIconDisplay(iconDisplay);
    }

    @Override
    public IconDisplay getIconDisplay() {
        return icon.getIconDisplay();
    }

    public Span getTitleLabel() {
        return titleLabel;
    }
}
