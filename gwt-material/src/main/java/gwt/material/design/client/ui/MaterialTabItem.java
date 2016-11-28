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

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasHref;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.html.ListItem;

//@formatter:off

/**
 * Item for Tab Component, which usually contains icons, links or other navigation component.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code <m:MaterialTabItem waves="YELLOW" grid="l4"><m:MaterialLink text="Tab 1" href="#tab1" textColor="WHITE"/></m:MaterialTabItem>}
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#tabs">Material Tabs</a>
 */
//@formatter:on
public class MaterialTabItem extends ListItem {

    private MaterialTab parent;

    public MaterialTabItem() {
        super(CssName.TAB);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        try {
            parent = (MaterialTab) getParent();
        } catch (ClassCastException ex) {
            throw new ClassCastException("MaterialTabItem must be within a MaterialTab widget.");
        }
    }

    /**
     * Select this tab item.
     */
    public void selectTab() {
        for (Widget child : getChildren()) {
            if (child instanceof HasHref) {
                String href = ((HasHref) child).getHref();
                if (!href.isEmpty()) {
                    parent.selectTab(href.replaceAll("[^a-zA-Z\\d\\s:]", ""));
                    break;
                }
            }
        }
    }
}
