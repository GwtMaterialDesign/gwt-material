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

import com.google.gwt.dom.client.Document;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.html.Span;

//@formatter:off

/**
 * Badges can notify you that there are new or unread messages or notifications.
 * Add the new class to the badge to give it the background.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialBadge text="1 new" color="BLUE"/>}
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#badges">Material Badge</a>
 */
//@formatter:on
public class MaterialBadge extends Span {

    /**
     * Creates a badge component that can be added to Link,
     * Collection, DropDown, SideNav and any other Material components.
     */
    public MaterialBadge() {
        super(Document.get().createSpanElement(), CssName.BADGE, CssName.SIDEBAR_BADGE);
    }

    /**
     * @param text text of the badge
     */
    public MaterialBadge(String text) {
        this();
        setText(text);
    }

    /**
     * @param text     text of the badge
     * @param isCircle is a circle badge
     */
    public MaterialBadge(String text, boolean isCircle) {
        this();
        setText(text);
        setCircle(isCircle);
    }

    /**
     * Badge with text and color
     *
     * @param text      text of the badge
     * @param textColor text color of the badge
     * @param bgColor   background color of the badge
     */
    public MaterialBadge(String text, Color textColor, Color bgColor) {
        this();
        setText(text);
        setTextColor(textColor);
        setBackgroundColor(bgColor);
    }

    @Override
    public String getText() {
        return getElement().getInnerHTML();
    }

    @Override
    public void setText(String text) {
        getElement().setInnerSafeHtml(SafeHtmlUtils.fromString(text));
    }
}
