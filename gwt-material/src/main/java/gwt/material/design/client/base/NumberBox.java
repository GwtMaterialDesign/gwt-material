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

import gwt.material.design.client.ui.MaterialNumberBox;

import java.io.IOException;
import java.text.ParseException;

import com.google.gwt.dom.client.Document;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.ValueBox;

/**
 * Base class to handle number values on {@code <input type="number">} elements.
 * 
 * The parsing and formatting is done by the browser, using the user specific i18n settings.
 * 
 * @author gilberto-torrezan
 * 
 * @see MaterialNumberBox
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class NumberBox<T> extends ValueBox<T> {
    
    private static final NumberHandler HANDLER = new NumberHandler();

    public NumberBox() {
        //currently there's no way to create a <input type="number"> directly
        super(Document.get().createTextInputElement(), HANDLER, HANDLER);
    }
    
    protected static class NumberHandler<T> implements Renderer<T>, Parser<T> {
        /*
         * Note: this method is NOT called by MaterialIntegerBox, MaterialFloatBox or MaterialDoubleBox, the parsing is done by the browser
         */
        @Override
        public T parse(CharSequence text) throws ParseException {
            return null;
        }

        @Override
        public String render(T object) {
            if (object == null){
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
