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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasType;
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
 * The tabs structure consists of an unordered list of tabs that have hashes corresponding to tab ids. Then when you click on each tab, only the container with the corresponding tab id will become visible.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialTab ui:field="tab"  backgroundColor="BLUE">
 * <m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 1" href="#tab1" textColor="WHITE"/></m:MaterialTabItem>
 * <m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 2" href="#tab2" textColor="WHITE"/></m:MaterialTabItem>
 * <m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 3" href="#tab3" textColor="WHITE"/></m:MaterialTabItem>
 * </m:MaterialTab>
 * <i:Panel m:id="tab1">
 * <i:Title title="Tab 1" description="Tab 1 Content"/>
 * </i:Panel>
 * <i:Panel m:id="tab2">
 * <i:Title title="Tab 2" description="Tab 2 Content"/>
 * </i:Panel>
 * <i:Panel m:id="tab3">
 * <i:Title title="Tab 3" description="Tab 3 Content"/>
 * </i:Panel>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#!tabs">Material Tabs</a>
 */
//@formatter:on
public class MaterialTab extends UnorderedList implements HasType<TabType> {

    private int tabIndex;
    private Color indicatorColor;

    private MaterialWidget indicator;
    private ColorsMixin<MaterialWidget> indicatorColorMixin;

    private final CssTypeMixin<TabType, MaterialTab> typeMixin = new CssTypeMixin<>(this);

    public MaterialTab() {
        super(CssName.TABS);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        initialize();

        indicator = new MaterialWidget(getIndicatorElement());
        indicatorColorMixin = new ColorsMixin<>(indicator);

        setIndicatorColor(indicatorColor);
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
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

    /**
     * Select a given tab by id.
     *
     * @param tabId Tab to selects id.
     */
    public void selectTab(String tabId) {
        Scheduler.get().scheduleDeferred(() -> $(getElement()).tabs("select_tab", tabId));
    }

    protected void initialize() {
        if (getWidgetCount() > 0) {
            $(getElement()).tabs();
            for (int i = 1; i < $(getElement()).find(".indicator").length(); i++) {
                $(getElement()).find(".indicator").eq(i).remove();
            }
        }
    }

    protected Element getIndicatorElement() {
        return $(getElement()).find(".indicator").get(0);
    }

    @Override
    public void setType(TabType type) {
        typeMixin.setType(type);
    }

    @Override
    public TabType getType() {
        return typeMixin.getType();
    }
}