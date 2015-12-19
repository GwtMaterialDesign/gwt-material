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
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ColorsMixin;
import gwt.material.design.client.ui.html.UnorderedList;

//@formatter:off

/**
 * The tabs structure consists of an unordered list of tabs that have hashes corresponding to tab ids. Then when you click on each tab, only the container with the corresponding tab id will become visible.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code
<m:MaterialTab ui:field="tab"  backgroundColor="blue">
<m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 1" href="#tab1" textColor="white"/></m:MaterialTabItem>
<m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 2" href="#tab2" textColor="white"/></m:MaterialTabItem>
<m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 3" href="#tab3" textColor="white"/></m:MaterialTabItem>
</m:MaterialTab>
<i:Panel m:id="tab1">
<i:Title title="Tab 1" description="Tab 1 Content"/>
</i:Panel>
<i:Panel m:id="tab2">
<i:Title title="Tab 2" description="Tab 2 Content"/>
</i:Panel>
<i:Panel m:id="tab3">
<i:Title title="Tab 3" description="Tab 3 Content"/>
</i:Panel>
}
 * </pre>
 * @see <a href="http://gwt-material-demo.herokuapp.com/#tabs">Material Tabs</a>
 * @author kevzlou7979
 * @author Ben Dol
 */
//@formatter:on
public class MaterialTab extends UnorderedList {

    private int tabIndex;
    private String indicatorColor;

    private MaterialWidget indicator;
    private ColorsMixin<MaterialWidget> indicatorColorMixin;

    public MaterialTab() {
        super();
        setStyleName("tabs");
    }

    @Override
    public void onLoad() {
        super.onLoad();

        initialize();

        indicator = new MaterialWidget(getIndicatorElement(getElement()));
        indicatorColorMixin = new ColorsMixin<>(indicator);

        setIndicatorColor(indicatorColor);
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
        int i = 0;
        for(Widget w : this){
            if(i == tabIndex){
                if(w instanceof MaterialTabItem) {
                    ((MaterialTabItem) w).selectTab();
                    break;
                }
            }
            i++;
        }
    }

    public void setIndicatorColor(String indicatorColor){
        this.indicatorColor = indicatorColor;

        if(indicatorColorMixin != null && indicatorColor != null) {
            indicatorColorMixin.setBackgroundColor(indicatorColor);
        }
    }

    /**
     * Select a given tab by id.
     * @param tabId Tab to selects id.
     */
    public void selectTab(String tabId) {
        selectTab(getElement(), tabId);
    }

    protected void initialize() {
        initialize(getElement());
    }

    private native void initialize(Element e) /*-{
        $wnd.jQuery(document).ready(function(){
            $wnd.jQuery(e).tabs();
        });
    }-*/;

    private native Element getIndicatorElement(Element e)/*-{
        return $wnd.jQuery(e).find(".indicator")[0];
    }-*/;

    private native void selectTab(Element e, String tabId)/*-{
        $wnd.jQuery(e).tabs("select_tab", tabId);
    }-*/;
}
