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
package gwt.material.design.client.constants;

import com.google.gwt.dom.client.Style.HasCssName;
import gwt.material.design.client.base.helper.EnumHelper;

/**
 * @author kevzlou7979
 */
public enum WordBreak implements HasCssName {
    NORMAL("normal"),
    BREAK_ALL("break-all"),
    KEEP_ALL("keep-all"),
    BREAK_WORD("break-word"),
    INITIAL("initial"),
    INHERIT("inherit");

    private final String cssName;

    WordBreak(HasCssName gwtDisplay) {
        this.cssName = gwtDisplay.getCssName();
    }

    WordBreak(String cssName) {
        this.cssName = cssName;
    }

    @Override
    public String getCssName() {
        return cssName;
    }

    public static WordBreak fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, WordBreak.class, INITIAL);
    }
}
