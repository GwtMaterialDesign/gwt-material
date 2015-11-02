package gwt.material.design.client.custom.mixin;

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


import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.custom.HasGrid;

/**
 * @author Ben Dol
 */
public class GridMixin<T extends UIObject & HasGrid> extends AbstractMixin<T> implements HasGrid {

    private String grid = "";
    private String offset = "";

    public GridMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setGrid(String grid) {
        uiObject.removeStyleName("col " + this.grid);
        this.grid = grid;
        uiObject.addStyleName("col " + grid);
    }

    @Override
    public void setOffset(String offset) {
        if(this.offset != null && !this.offset.isEmpty()) {
            uiObject.removeStyleName(this.offset);
        }

        if(offset != null) {
            String cssName = "";
            for (String val : offset.split(" ")) {
                cssName = cssName + " offset-" + val;
            }
            this.offset = cssName;
            uiObject.addStyleName(cssName);
        } else {
            this.offset = null;
        }
    }
}
