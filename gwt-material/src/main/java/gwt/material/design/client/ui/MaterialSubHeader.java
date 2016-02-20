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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HasText;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.html.Div;
import gwt.material.design.client.ui.html.Span;

//@formatter:off

/**
 * Sub-headers are special list tiles that delineate distinct sections of a list or grid
 * list and are typically related to the current filtering or sorting criteria. Sub-header
 * tiles are either displayed in-line with tiles or can be associated with content, for
 * example, in an adjacent column.
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialSubHeader text="Unread Messages" textColor="purple"/>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#subheaders">Material SubHeaders</a>
 */
//@formatter:on
public class MaterialSubHeader extends Div implements HasText, HasIcon {

    private MaterialIcon icon = new MaterialIcon();
    private Span span = new Span();

    private boolean initialized = false;

    public MaterialSubHeader() {
        setStyleName("subheader");
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        initialize();
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

    @Override
    public String getText() {
        return span.getText();
    }

    @Override
    public void setText(String text) {
        span.setText(text);
        add(span);
    }

    private void initialize() {
        if(!initialized) {
            initialize(getElement());
            initialized = true;
        }
    }

    private native void initialize(Element e) /*-{
        $wnd.stickyHeaders.load($wnd.jQuery(".subheader"));
    }-*/;
}
