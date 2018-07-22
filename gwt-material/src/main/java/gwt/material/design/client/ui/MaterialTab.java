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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.JsLoader;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ColorsMixin;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.TabType;
import gwt.material.design.client.ui.html.UnorderedList;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * The tabs structure consists of an unordered list of tabs that have hashes corresponding to tab ids.
 * Then when you click on each tab, only the container with the corresponding tab id will become visible.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialTab ui:field="tab"  backgroundColor="BLUE">
 *     <m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 1" href="#tab1" textColor="WHITE"/></m:MaterialTabItem>
 *     <m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 2" href="#tab2" textColor="WHITE"/></m:MaterialTabItem>
 *     <m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 3" href="#tab3" textColor="WHITE"/></m:MaterialTabItem>
 * </m:MaterialTab>
 *
 * <m:MaterialPanel m:id="tab1">
 *     <m:MaterialTitle title="Tab 1" description="Tab 1 Content"/>
 * </m:MaterialPanel>
 * <m:MaterialPanel m:id="tab2">
 *     <m:MaterialTitle title="Tab 2" description="Tab 2 Content"/>
 * <m:MaterialPanel>
 * <i:Panel m:id="tab3">
 *     <m:MaterialTitle title="Tab 3" description="Tab 3 Content"/>
 * </m:MaterialPanel>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#tabs">Material Tabs</a>
 * @see <a href="https://material.io/guidelines/components/tabs.html">Material Design Specification</a>
 */
//@formatter:on
public class MaterialTab extends UnorderedList implements JsLoader, HasType<TabType>, HasSelectionHandlers<Integer> {

    private Color indicatorColor;
    private MaterialWidget indicator;

    private ColorsMixin<MaterialWidget> indicatorColorMixin;
    private CssTypeMixin<TabType, MaterialTab> typeMixin;

    public MaterialTab() {
        super(CssName.TABS);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        load();
    }

    @Override
    public void load() {
        if (getWidgetCount() > 0) {
            getChildren().forEach(child -> registerChildHandler(child));
            $(getElement()).tabs();
            applyIndicator();
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        unload();
    }

    @Override
    public void unload() {
        clearAllIndicators();
    }

    @Override
    public void reload() {
        if (isAttached()) {
            unload();
            load();
        }
    }

    /**
     * Select a given tab by id.
     *
     * @param tabId Tab to selects id.
     */
    public void selectTab(String tabId) {
        $(getElement()).tabs("select_tab", tabId);
    }

    @Override
    public void add(Widget child) {
        super.add(child);
        reload();
    }

    protected void registerChildHandler(Widget child) {
        if (child instanceof MaterialTabItem) {
            MaterialTabItem item = (MaterialTabItem) child;
            item.getHandlerRegistry().clearHandlers();
            item.registerHandler(item.addMouseDownHandler(e -> SelectionEvent.fire(MaterialTab.this, getChildren().indexOf(child))));
        }
    }

    @Override
    public void clear() {
        super.clear();
        reload();
    }

    @Override
    public boolean remove(Widget w) {
        boolean value = super.remove(w);
        reload();
        return value;
    }

    @Override
    public void insert(Widget child, int beforeIndex) {
        super.insert(child, beforeIndex);
        reload();
    }

    protected void applyIndicator() {
        indicator = new MaterialWidget(getIndicatorElement());
        indicatorColorMixin = new ColorsMixin<>(indicator);
        setIndicatorColor(indicatorColor);
        clearAllIndicators();
    }

    protected void clearAllIndicators() {
        Scheduler.get().scheduleDeferred(() -> {
            for (int i = 1; i < $(getElement()).find(".indicator").length(); i++) {
                $(getElement()).find(".indicator").eq(i).remove();
            }
        });
    }

    public int getTabIndex() {
        return $(getElement()).find("li:has(a.active)").index();
    }

    public void setTabIndex(int tabIndex) {
        int i = 0;
        for (Widget w : this) {
            if (i == tabIndex) {
                if (w instanceof MaterialTabItem) {
                    ((MaterialTabItem) w).selectTab();
                    break;
                }
            }
            i++;
        }
    }

    public void setIndicatorColor(Color indicatorColor) {
        this.indicatorColor = indicatorColor;

        if (indicatorColorMixin != null && indicatorColor != null) {
            indicatorColorMixin.setBackgroundColor(indicatorColor);
        }
    }

    protected Element getIndicatorElement() {
        return $(getElement()).find(".indicator").last().asElement();
    }

    @Override
    public void setType(TabType type) {
        getTypeMixin().setType(type);
    }

    @Override
    public TabType getType() {
        return getTypeMixin().getType();
    }

    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
        return addHandler(handler, SelectionEvent.getType());
    }

    protected CssTypeMixin<TabType, MaterialTab> getTypeMixin() {
        if (typeMixin == null) {
            typeMixin = new CssTypeMixin<>(this);
        }
        return typeMixin;
    }
}