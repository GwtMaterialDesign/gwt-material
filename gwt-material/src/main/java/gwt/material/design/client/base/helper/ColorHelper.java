package gwt.material.design.client.base.helper;

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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.annotation.Gwt3Incompatible;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;

public class ColorHelper {

    /**
     * Returns first enum constant found..
     *
     * @param styleName    Space-separated list of styles
     * @param enumClass    Type of enum
     * @param defaultValue Default value of no match was found
     * @return First enum constant found or default value
     */
    public static <E extends Enum<? extends Style.HasCssName>> E fromStyleName(final String styleName,
                                                                               final Class<E> enumClass,
                                                                               final E defaultValue) {
        return EnumHelper.fromStyleName(styleName, enumClass, defaultValue, true);
    }

    public static String setupComputedBackgroundColor(Color color) {
        MaterialWidget temp = new MaterialWidget(Document.get().createDivElement());
        temp.setBackgroundColor(color);
        RootPanel.get().add(temp);
        String computed = getComputedBackgroundColor(temp.getElement()).toLowerCase();
        temp.removeFromParent();
        return computed;
    }

    /**
     * Native call to getComputedStyle.
     */
    @Gwt3Incompatible
    protected static native String getComputedBackgroundColor(Element e)/*-{
        var cs = $wnd.document.defaultView.getComputedStyle(e, null);
        return cs.getPropertyValue('background-color');
    }-*/;

    public static Color addShade(Color color, Shade shade, int value) {
        Color shadedColor = color;
        String className = color.getCssName();
        if (className != null && !className.isEmpty() && !hasShade(color) && color != Color.WHITE && color != Color.TRANSPARENT && color != Color.BLACK && color != Color.DEFAULT) {
            if (value > 0 && value < 6) {
                className = className + " " + shade.getName() + "-" + value;
                shadedColor = Color.fromStyleName(className, true);
            }
        }
        return shadedColor;
    }

    public static boolean hasShade(Color color) {
        String className = color.getCssName();
        return className.contains("lighten") || className.contains("darken") || className.contains("accent");
    }

    public enum Shade {
        LIGHTEN("lighten"),
        DARKEN("darken"),
        ACCENT("accent");

        private String name;

        Shade(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
