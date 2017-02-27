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

import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasPosition;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.constants.HideOn;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;

import java.util.ArrayList;
import java.util.List;

//@formatter:off

/**
 * <p>Material NavSection is a child of MaterialNavBar that will contain toolbar items such as link, icon and other components
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code
 * <m:MaterialNavSection align="RIGHT">
 *     <m:MaterialLink  iconType="ACCOUNT_CIRCLE" iconPosition="LEFT" text="Account"  textColor="WHITE" waves="LIGHT"/>
 *     <m:MaterialLink  iconType="AUTORENEW" iconPosition="LEFT" text="Refresh" textColor="WHITE" waves="LIGHT"/>
 *     <m:MaterialLink  iconType="SEARCH" tooltip="Menu" textColor="WHITE" waves="LIGHT"/>
 *     <m:MaterialLink  iconType="MORE_VERT" tooltip="Starter" textColor="WHITE" waves="LIGHT"/>
 * </m:MaterialNavSection>
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#navbar">Material NavSection</a>
 */
//@formatter:on
public class MaterialNavSection extends UnorderedList implements HasPosition, HasSelectionHandlers<Integer> {

    private final CssNameMixin<MaterialNavSection, Position> posMixin = new CssNameMixin<>(this);
    private List<HandlerRegistration> handlers = new ArrayList<>();

    /**
     * Container for App Toolbar and App Sidebar, contains Material
     * Links, Icons or any other material components.
     */
    public MaterialNavSection() {
        super();
        setHideOn(HideOn.HIDE_ON_MED_DOWN);
    }

    @Override
    public void add(Widget child) {
        super.add(new ListItem(child));
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        for (Widget widget : getChildren()) {
            if (widget instanceof ListItem) {
                handlers.add(((ListItem) widget).addClickHandler(clickEvent -> SelectionEvent.fire(this, getWidgetIndex(widget))));
            }
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        handlers.clear();
    }

    @Override
    public Position getPosition() {
        return posMixin.getCssName();
    }

    @Override
    public void setPosition(Position position) {
        posMixin.setCssName(position);
    }

    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> selectionHandler) {
        return addHandler(selectionHandler, SelectionEvent.getType());
    }
}
