package gwt.material.design.client.constants;

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

import com.google.gwt.dom.client.Style;

/**
 * @author chriswjones
 */
public enum Display {
    FLEX(null),
    NONE(Style.Display.NONE),
    BLOCK(Style.Display.BLOCK),
    INLINE(Style.Display.INLINE),
    INLINE_BLOCK(Style.Display.INLINE_BLOCK),
    INLINE_TABLE(Style.Display.INLINE_TABLE),
    LIST_ITEM(Style.Display.LIST_ITEM),
    RUN_IN(Style.Display.RUN_IN),
    TABLE(Style.Display.TABLE),
    TABLE_CAPTION(Style.Display.TABLE_CAPTION),
    TABLE_COLUMN_GROUP(Style.Display.TABLE_COLUMN_GROUP),
    TABLE_HEADER_GROUP(Style.Display.TABLE_HEADER_GROUP),
    TABLE_FOOTER_GROUP(Style.Display.TABLE_FOOTER_GROUP),
    TABLE_ROW_GROUP(Style.Display.TABLE_ROW_GROUP),
    TABLE_CELL(Style.Display.TABLE_CELL),
    TABLE_COLUMN(Style.Display.TABLE_COLUMN),
    TABLE_ROW(Style.Display.TABLE_ROW),
    INITIAL(Style.Display.INITIAL);

    private final Style.Display gwtDisplay;

    Display(Style.Display gwtDisplay) {
        this.gwtDisplay = gwtDisplay;
    }

    public Style.Display getGwtDisplay() {
        return gwtDisplay;
    }

    public static Display parse(Style.Display gwtDisplay) {
        if (gwtDisplay == null) {
            return null;
        }

        for (Display d : Display.values()) {
            if (d.getGwtDisplay() != null && d.getGwtDisplay().equals(gwtDisplay)) {
                return d;
            }
        }
        return null;
    }
}
