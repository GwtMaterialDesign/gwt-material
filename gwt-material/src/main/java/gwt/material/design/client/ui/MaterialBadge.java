package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import gwt.material.design.client.ui.html.Span;

import com.google.gwt.user.client.ui.HasText;

//@formatter:off
/**
 * Badges can notify you that there are new or unread messages or notifications.
 * Add the new class to the badge to give it the background.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialBadge text="1 new" color="blue"/>}
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#badges">Material Badge</a>
 */
//@formatter:on
public class MaterialBadge extends Span implements HasText {

    /**
     * Creates a badge component that can be added to Link,
     * Collection, DropDown, SideNav and any other Material components.
     */
    public MaterialBadge() {
        setStyleName("badge sideBarBadge");
    }

    /**
     * Badge with text and color
     * @param text - text of the badge
     * @param textColor - text color of the badge
     * @param bgColor - background color of the badge
     */
    public MaterialBadge(String text, String textColor, String bgColor) {
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
        getElement().setInnerHTML(text);
    }
}
