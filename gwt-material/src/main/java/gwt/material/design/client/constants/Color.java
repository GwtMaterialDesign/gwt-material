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
package gwt.material.design.client.constants;

import gwt.material.design.client.base.helper.EnumHelper;

/**
 * Color constants.
 *
 * @author kevzlou7979
 */
public enum Color implements CssType {
    DEFAULT(""),
    BLACK("black"),
    WHITE("white"),
    TRANSPARENT("transparent"),
    // Blue Grey
    BLUE_GREY_LIGHTEN_5("blue-grey lighten-5"),
    BLUE_GREY_LIGHTEN_4("blue-grey lighten-4"),
    BLUE_GREY_LIGHTEN_3("blue-grey lighten-3"),
    BLUE_GREY_LIGHTEN_2("blue-grey lighten-2"),
    BLUE_GREY_LIGHTEN_1("blue-grey lighten-1"),
    BLUE_GREY("blue-grey"),
    BLUE_GREY_DARKEN_1("blue-grey darken-1"),
    BLUE_GREY_DARKEN_2("blue-grey darken-2"),
    BLUE_GREY_DARKEN_3("blue-grey darken-3"),
    BLUE_GREY_DARKEN_4("blue-grey darken-4"),
    // Grey
    GREY_LIGHTEN_5("grey lighten-5"),
    GREY_LIGHTEN_4("grey lighten-4"),
    GREY_LIGHTEN_3("grey lighten-3"),
    GREY_LIGHTEN_2("grey lighten-2"),
    GREY_LIGHTEN_1("grey lighten-1"),
    GREY("grey"),
    GREY_DARKEN_1("grey darken-1"),
    GREY_DARKEN_2("grey darken-2"),
    GREY_DARKEN_3("grey darken-3"),
    GREY_DARKEN_4("grey darken-4"),
    // Brown
    BROWN_LIGHTEN_5("brown lighten-5"),
    BROWN_LIGHTEN_4("brown lighten-4"),
    BROWN_LIGHTEN_3("brown lighten-3"),
    BROWN_LIGHTEN_2("brown lighten-2"),
    BROWN_LIGHTEN_1("brown lighten-1"),
    BROWN("brown"),
    BROWN_DARKEN_1("brown darken-1"),
    BROWN_DARKEN_2("brown darken-2"),
    BROWN_DARKEN_3("brown darken-3"),
    BROWN_DARKEN_4("brown darken-4"),
    // Deep Orange
    DEEP_ORANGE_LIGHTEN_5("deep-orange lighten-5"),
    DEEP_ORANGE_LIGHTEN_4("deep-orange lighten-4"),
    DEEP_ORANGE_LIGHTEN_3("deep-orange lighten-3"),
    DEEP_ORANGE_LIGHTEN_2("deep-orange lighten-2"),
    DEEP_ORANGE_LIGHTEN_1("deep-orange lighten-1"),
    DEEP_ORANGE("deep-orange"),
    DEEP_ORANGE_DARKEN_1("deep-orange darken-1"),
    DEEP_ORANGE_DARKEN_2("deep-orange darken-2"),
    DEEP_ORANGE_DARKEN_3("deep-orange darken-3"),
    DEEP_ORANGE_DARKEN_4("deep-orange darken-4"),
    DEEP_ORANGE_ACCENT_1("deep-orange accent-1"),
    DEEP_ORANGE_ACCENT_2("deep-orange accent-2"),
    DEEP_ORANGE_ACCENT_3("deep-orange accent-3"),
    DEEP_ORANGE_ACCENT_4("deep-orange accent-4"),
    // Orange
    ORANGE_LIGHTEN_5("orange lighten-5"),
    ORANGE_LIGHTEN_4("orange lighten-4"),
    ORANGE_LIGHTEN_3("orange lighten-3"),
    ORANGE_LIGHTEN_2("orange lighten-2"),
    ORANGE_LIGHTEN_1("orange lighten-1"),
    ORANGE("orange"),
    ORANGE_DARKEN_1("orange darken-1"),
    ORANGE_DARKEN_2("orange darken-2"),
    ORANGE_DARKEN_3("orange darken-3"),
    ORANGE_DARKEN_4("orange darken-4"),
    ORANGE_ACCENT_1("orange accent-1"),
    ORANGE_ACCENT_2("orange accent-2"),
    ORANGE_ACCENT_3("orange accent-3"),
    ORANGE_ACCENT_4("orange accent-4"),
    // amber
    AMBER_LIGHTEN_5("amber lighten-5"),
    AMBER_LIGHTEN_4("amber lighten-4"),
    AMBER_LIGHTEN_3("amber lighten-3"),
    AMBER_LIGHTEN_2("amber lighten-2"),
    AMBER_LIGHTEN_1("amber lighten-1"),
    AMBER("amber"),
    AMBER_DARKEN_1("amber darken-1"),
    AMBER_DARKEN_2("amber darken-2"),
    AMBER_DARKEN_3("amber darken-3"),
    AMBER_DARKEN_4("amber darken-4"),
    AMBER_ACCENT_1("amber accent-1"),
    AMBER_ACCENT_2("amber accent-2"),
    AMBER_ACCENT_3("amber accent-3"),
    AMBER_ACCENT_4("amber accent-4"),
    // Yellow
    YELLOW_LIGHTEN_5("yellow lighten-5"),
    YELLOW_LIGHTEN_4("yellow lighten-4"),
    YELLOW_LIGHTEN_3("yellow lighten-3"),
    YELLOW_LIGHTEN_2("yellow lighten-2"),
    YELLOW_LIGHTEN_1("yellow lighten-1"),
    YELLOW("yellow"),
    YELLOW_DARKEN_1("yellow darken-1"),
    YELLOW_DARKEN_2("yellow darken-2"),
    YELLOW_DARKEN_3("yellow darken-3"),
    YELLOW_DARKEN_4("yellow darken-4"),
    YELLOW_ACCENT_1("yellow accent-1"),
    YELLOW_ACCENT_2("yellow accent-2"),
    YELLOW_ACCENT_3("yellow accent-3"),
    YELLOW_ACCENT_4("yellow accent-4"),
    // Lime
    LIME_LIGHTEN_5("lime lighten-5"),
    LIME_LIGHTEN_4("lime lighten-4"),
    LIME_LIGHTEN_3("lime lighten-3"),
    LIME_LIGHTEN_2("lime lighten-2"),
    LIME_LIGHTEN_1("lime lighten-1"),
    LIME("lime"),
    LIME_DARKEN_1("lime darken-1"),
    LIME_DARKEN_2("lime darken-2"),
    LIME_DARKEN_3("lime darken-3"),
    LIME_DARKEN_4("lime darken-4"),
    LIME_ACCENT_1("lime accent-1"),
    LIME_ACCENT_2("lime accent-2"),
    LIME_ACCENT_3("lime accent-3"),
    LIME_ACCENT_4("lime accent-4"),
    // Light Green
    LIGHT_GREEN_LIGHTEN_5("light-green lighten-5"),
    LIGHT_GREEN_LIGHTEN_4("light-green lighten-4"),
    LIGHT_GREEN_LIGHTEN_3("light-green lighten-3"),
    LIGHT_GREEN_LIGHTEN_2("light-green lighten-2"),
    LIGHT_GREEN_LIGHTEN_1("light-green lighten-1"),
    LIGHT_GREEN("light-green"),
    LIGHT_GREEN_DARKEN_1("light-green darken-1"),
    LIGHT_GREEN_DARKEN_2("light-green darken-2"),
    LIGHT_GREEN_DARKEN_3("light-green darken-3"),
    LIGHT_GREEN_DARKEN_4("light-green darken-4"),
    LIGHT_GREEN_ACCENT_1("light-green accent-1"),
    LIGHT_GREEN_ACCENT_2("light-green accent-2"),
    LIGHT_GREEN_ACCENT_3("light-green accent-3"),
    LIGHT_GREEN_ACCENT_4("light-green accent-4"),
    // Green
    GREEN_LIGHTEN_5("green lighten-5"),
    GREEN_LIGHTEN_4("green lighten-4"),
    GREEN_LIGHTEN_3("green lighten-3"),
    GREEN_LIGHTEN_2("green lighten-2"),
    GREEN_LIGHTEN_1("green lighten-1"),
    GREEN("green"),
    GREEN_DARKEN_1("green darken-1"),
    GREEN_DARKEN_2("green darken-2"),
    GREEN_DARKEN_3("green darken-3"),
    GREEN_DARKEN_4("green darken-4"),
    GREEN_ACCENT_1("green accent-1"),
    GREEN_ACCENT_2("green accent-2"),
    GREEN_ACCENT_3("green accent-3"),
    GREEN_ACCENT_4("green accent-4"),
    // Teal
    TEAL_LIGHTEN_5("teal lighten-5"),
    TEAL_LIGHTEN_4("teal lighten-4"),
    TEAL_LIGHTEN_3("teal lighten-3"),
    TEAL_LIGHTEN_2("teal lighten-2"),
    TEAL_LIGHTEN_1("teal lighten-1"),
    TEAL("teal"),
    TEAL_DARKEN_1("teal darken-1"),
    TEAL_DARKEN_2("teal darken-2"),
    TEAL_DARKEN_3("teal darken-3"),
    TEAL_DARKEN_4("teal darken-4"),
    TEAL_ACCENT_1("teal accent-1"),
    TEAL_ACCENT_2("teal accent-2"),
    TEAL_ACCENT_3("teal accent-3"),
    TEAL_ACCENT_4("teal accent-4"),
    // Cyan
    CYAN_LIGHTEN_5("cyan lighten-5"),
    CYAN_LIGHTEN_4("cyan lighten-4"),
    CYAN_LIGHTEN_3("cyan lighten-3"),
    CYAN_LIGHTEN_2("cyan lighten-2"),
    CYAN_LIGHTEN_1("cyan lighten-1"),
    CYAN("cyan"),
    CYAN_DARKEN_1("cyan darken-1"),
    CYAN_DARKEN_2("cyan darken-2"),
    CYAN_DARKEN_3("cyan darken-3"),
    CYAN_DARKEN_4("cyan darken-4"),
    CYAN_ACCENT_1("cyan accent-1"),
    CYAN_ACCENT_2("cyan accent-2"),
    CYAN_ACCENT_3("cyan accent-3"),
    CYAN_ACCENT_4("cyan accent-4"),
    // Light Blue
    LIGHT_BLUE_LIGHTEN_5("light-blue lighten-5"),
    LIGHT_BLUE_LIGHTEN_4("light-blue lighten-4"),
    LIGHT_BLUE_LIGHTEN_3("light-blue lighten-3"),
    LIGHT_BLUE_LIGHTEN_2("light-blue lighten-2"),
    LIGHT_BLUE_LIGHTEN_1("light-blue lighten-1"),
    LIGHT_BLUE("light-blue"),
    LIGHT_BLUE_DARKEN_1("light-blue darken-1"),
    LIGHT_BLUE_DARKEN_2("light-blue darken-2"),
    LIGHT_BLUE_DARKEN_3("light-blue darken-3"),
    LIGHT_BLUE_DARKEN_4("light-blue darken-4"),
    LIGHT_BLUE_ACCENT_1("light-blue accent-1"),
    LIGHT_BLUE_ACCENT_2("light-blue accent-2"),
    LIGHT_BLUE_ACCENT_3("light-blue accent-3"),
    LIGHT_BLUE_ACCENT_4("light-blue accent-4"),
    // Blue
    BLUE_LIGHTEN_5("blue lighten-5"),
    BLUE_LIGHTEN_4("blue lighten-4"),
    BLUE_LIGHTEN_3("blue lighten-3"),
    BLUE_LIGHTEN_2("blue lighten-2"),
    BLUE_LIGHTEN_1("blue lighten-1"),
    BLUE("blue"),
    BLUE_DARKEN_1("blue darken-1"),
    BLUE_DARKEN_2("blue darken-2"),
    BLUE_DARKEN_3("blue darken-3"),
    BLUE_DARKEN_4("blue darken-4"),
    BLUE_ACCENT_1("blue accent-1"),
    BLUE_ACCENT_2("blue accent-2"),
    BLUE_ACCENT_3("blue accent-3"),
    BLUE_ACCENT_4("blue accent-4"),
    // Indigo
    INDIGO_LIGHTEN_5("indigo lighten-5"),
    INDIGO_LIGHTEN_4("indigo lighten-4"),
    INDIGO_LIGHTEN_3("indigo lighten-3"),
    INDIGO_LIGHTEN_2("indigo lighten-2"),
    INDIGO_LIGHTEN_1("indigo lighten-1"),
    INDIGO("indigo"),
    INDIGO_DARKEN_1("indigo darken-1"),
    INDIGO_DARKEN_2("indigo darken-2"),
    INDIGO_DARKEN_3("indigo darken-3"),
    INDIGO_DARKEN_4("indigo darken-4"),
    INDIGO_ACCENT_1("indigo accent-1"),
    INDIGO_ACCENT_2("indigo accent-2"),
    INDIGO_ACCENT_3("indigo accent-3"),
    INDIGO_ACCENT_4("indigo accent-4"),
    // Deep Purple
    DEEP_PURPLE_LIGHTEN_5("deep-purple lighten-5"),
    DEEP_PURPLE_LIGHTEN_4("deep-purple lighten-4"),
    DEEP_PURPLE_LIGHTEN_3("deep-purple lighten-3"),
    DEEP_PURPLE_LIGHTEN_2("deep-purple lighten-2"),
    DEEP_PURPLE_LIGHTEN_1("deep-purple lighten-1"),
    DEEP_PURPLE("deep-purple"),
    DEEP_PURPLE_DARKEN_1("deep-purple darken-1"),
    DEEP_PURPLE_DARKEN_2("deep-purple darken-2"),
    DEEP_PURPLE_DARKEN_3("deep-purple darken-3"),
    DEEP_PURPLE_DARKEN_4("deep-purple darken-4"),
    DEEP_PURPLE_ACCENT_1("deep-purple accent-1"),
    DEEP_PURPLE_ACCENT_2("deep-purple accent-2"),
    DEEP_PURPLE_ACCENT_3("deep-purple accent-3"),
    DEEP_PURPLE_ACCENT_4("deep-purple accent-4"),
    // Purple
    PURPLE_LIGHTEN_5("purple lighten-5"),
    PURPLE_LIGHTEN_4("purple lighten-4"),
    PURPLE_LIGHTEN_3("purple lighten-3"),
    PURPLE_LIGHTEN_2("purple lighten-2"),
    PURPLE_LIGHTEN_1("purple lighten-1"),
    PURPLE("purple"),
    PURPLE_DARKEN_1("purple darken-1"),
    PURPLE_DARKEN_2("purple darken-2"),
    PURPLE_DARKEN_3("purple darken-3"),
    PURPLE_DARKEN_4("purple darken-4"),
    PURPLE_ACCENT_1("purple accent-1"),
    PURPLE_ACCENT_2("purple accent-2"),
    PURPLE_ACCENT_3("purple accent-3"),
    PURPLE_ACCENT_4("purple accent-4"),
    // Pink
    PINK_LIGHTEN_5("pink lighten-5"),
    PINK_LIGHTEN_4("pink lighten-4"),
    PINK_LIGHTEN_3("pink lighten-3"),
    PINK_LIGHTEN_2("pink lighten-2"),
    PINK_LIGHTEN_1("pink lighten-1"),
    PINK("pink"),
    PINK_DARKEN_1("pink darken-1"),
    PINK_DARKEN_2("pink darken-2"),
    PINK_DARKEN_3("pink darken-3"),
    PINK_DARKEN_4("pink darken-4"),
    PINK_ACCENT_1("pink accent-1"),
    PINK_ACCENT_2("pink accent-2"),
    PINK_ACCENT_3("pink accent-3"),
    PINK_ACCENT_4("pink accent-4"),
    // Red
    RED_LIGHTEN_5("red lighten-5"),
    RED_LIGHTEN_4("red lighten-4"),
    RED_LIGHTEN_3("red lighten-3"),
    RED_LIGHTEN_2("red lighten-2"),
    RED_LIGHTEN_1("red lighten-1"),
    RED("red"),
    RED_DARKEN_1("red darken-1"),
    RED_DARKEN_2("red darken-2"),
    RED_DARKEN_3("red darken-3"),
    RED_DARKEN_4("red darken-4"),
    RED_ACCENT_1("red accent-1"),
    RED_ACCENT_2("red accent-2"),
    RED_ACCENT_3("red accent-3"),
    RED_ACCENT_4("red accent-4");

    private final String cssClass;

    Color(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static Color fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, Color.class, Color.DEFAULT);
    }
}
