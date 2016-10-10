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
package gwt.material.design.client.base;

import com.google.gwt.dom.client.Document;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.ValueBox;
import gwt.material.design.client.ui.MaterialNumberBox;

import java.io.IOException;
import java.text.ParseException;

/**
 * Base class to handle number values on {@code <input type="number">} elements.
 * <p>
 * The parsing and formatting is done by the browser, using the user specific i18n settings.
 *
 * @author gilberto-torrezan
 * @see MaterialNumberBox
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class NumberBox<T> extends ValueBox<T> {

    public NumberBox(NumberHandler handler) {
        // currently there's no way to create a <input type="number"> directly
        super(Document.get().createTextInputElement(), handler, handler);
    }

    public static class NumberHandler<T> implements Renderer<T>, Parser<T> {

        MaterialNumberBox<T> numberBox;

        public NumberHandler(MaterialNumberBox<T> numberBox) {
            this.numberBox = numberBox;
        }

        /*
         * Note: This assumes that getValue() from the custom MaterialNumberBox was overridden to avoid using the parser.
         */
        @Override
        public T parse(CharSequence text) throws ParseException {
            return numberBox.getValue();
        }

        @Override
        public String render(T object) {
            if (object == null) {
                return "";
            }
            return object.toString();
        }

        @Override
        public void render(T object, Appendable appendable) throws IOException {
            appendable.append(render(object));
        }
    }

}
