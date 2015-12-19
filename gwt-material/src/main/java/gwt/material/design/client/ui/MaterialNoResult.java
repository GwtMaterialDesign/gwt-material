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

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.base.HasTitle;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.html.Div;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;

//@formatter:off
/**
* 
* <p>Material No result is a component that will have to display once content is empty.
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
<m:MaterialNoResult iconType="POLYMER" title="No Inbox" description="You dont have new message" backgroundColor="blue"/>
}
</pre>
* </p>
* 
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#no-result">Material No Result</a>
*/
//@formatter:on
public class MaterialNoResult extends MaterialWidget implements HasIcon, HasTitle{


    private MaterialIcon icon = new MaterialIcon();
    private MaterialTitle title = new MaterialTitle();
    private Div div = new Div();

    public MaterialNoResult() {
        super(Document.get().createDivElement());
        setStyleName("valign-wrapper");
        setTextAlign(TextAlign.CENTER);
        setHeight("100%");
        add(div);
        div.setWidth("100%");
        div.setStyleName("valign center");
        div.add(title);
        icon.setIconSize(IconSize.LARGE);
        title.insert(icon, 0);
        title.setMarginTop(-100);
    }

    public MaterialNoResult(String bgColor, String textColor, IconType iconType, String title, String description) {
        this();
        setBackgroundColor(bgColor);
        setTextColor(textColor);
        setIconType(iconType);
        setTitle(title);
        setDescription(description);
    }

    @Override
    public void setDescription(String description) {
        title.setDescription(description);
    }

    @Override
    public void setTitle(String titleText) {
        title.setTitle(titleText);
    }

    @Override
    public MaterialIcon getIcon() {
        return icon;
    }

    @Override
    public void setIconType(IconType iconType) {
        icon.setIconType(iconType);
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
    public void setIconFontSize(double size, Unit unit) {
        icon.setIconFontSize(size, unit);
    }

    @Override
    public void setIconColor(String iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        icon.setIconPrefix(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return icon.isIconPrefix();
    }
}
