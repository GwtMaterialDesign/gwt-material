/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2022 GwtMaterialDesign
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
package gwt.material.design.client.base;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialChipContainer;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;

import java.util.ArrayList;
import java.util.List;

public class DefaultMoreChipHandler implements MoreChipHandler {

    protected int visibleChipsSize = 0;
    protected int hiddenChipsSize = 0;
    protected MaterialLink more = new MaterialLink();
    protected boolean showHiddenChips;
    protected List<MaterialChip> hiddenChips = new ArrayList<>();
    protected String localizedMoreText = "Show {0} more items";
    protected MaterialChipContainer container;

    public DefaultMoreChipHandler() {
        more.setDisplay(Display.BLOCK);
        more.setMarginLeft(12);
    }

    @Override
    public void setVisibleChipsSize(int visibleChipsSize) {
        this.visibleChipsSize = visibleChipsSize;
    }

    @Override
    public void load(MaterialChipContainer container) {
        this.container = container;

        more.addClickHandler(clickEvent -> showHiddenChips(!isShowHiddenChips()));
        container.add(more);
        update();
    }

    @Override
    public void reload() {
        update();
    }

    public void update(){

        List<MaterialChip> chipList = container.getChipList();
        if (chipList != null && visibleChipsSize > 0 && visibleChipsSize <= chipList.size()) {
            hiddenChips.clear();
            hiddenChipsSize = chipList.size() - visibleChipsSize;
            for (MaterialChip chip : chipList) {
                if (chipList.indexOf(chip) >= hiddenChipsSize) {
                    hiddenChips.add(chip);
                }
            }
            showHiddenChips(false);
        }
    }

    public void showHiddenChips(boolean showHiddenChips) {
        this.showHiddenChips = showHiddenChips;
        for (MaterialChip hiddenChip : hiddenChips) {
            hiddenChip.setVisible(showHiddenChips);
        }

        if (showHiddenChips) {
            more.setVisibility(Style.Visibility.HIDDEN);
        } else {
            more.setVisibility(Style.Visibility.VISIBLE);
            more.setText(localizedMoreText.replace("{0}", hiddenChipsSize + ""));
        }
    }

    public boolean isShowHiddenChips() {
        return showHiddenChips;
    }
}
