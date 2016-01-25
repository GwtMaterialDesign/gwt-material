/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package gwt.material.design.client.base;

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

import java.text.ParseException;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.shared.Parser;

/**
 * A localized parser based on {@link NumberFormat#getDecimalFormat}.
 */
public class FloatParser implements Parser<Float> {

    private static FloatParser INSTANCE;

    /**
     * @return the instance of the no-op renderer.
     */
    public static Parser<Float> instance() {
        if (INSTANCE == null) {
            INSTANCE = new FloatParser();
        }
        return INSTANCE;
    }

    protected FloatParser() {
    }

    /*
     * Note: this method is NOT called by MaterialFloatBox, the parsing is done by the browser
     */
    public Float parse(CharSequence object) throws ParseException {
        if (object == null || object.length() == 0) {
            return null;
        }

        try {
            return new Float(NumberFormat.getDecimalFormat().parse(object.toString()));
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
