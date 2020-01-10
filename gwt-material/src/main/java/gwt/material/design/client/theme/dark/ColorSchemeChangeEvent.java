/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2019 GwtMaterialDesign
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
package gwt.material.design.client.theme.dark;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ColorSchemeChangeEvent extends GwtEvent<ColorSchemeChangeEvent.ColorSchemeChangeHandler> {

    public interface ColorSchemeChangeHandler extends EventHandler {
        void onColorSchemeChange(ColorSchemeChangeEvent event);
    }

    private ColorScheme colorScheme;

    public ColorSchemeChangeEvent(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    public static final Type<ColorSchemeChangeEvent.ColorSchemeChangeHandler> TYPE = new Type<>();

    public static void fire(HasHandlers source, ColorScheme colorScheme) {
        source.fireEvent(new ColorSchemeChangeEvent(colorScheme));
    }

    @Override
    public Type<ColorSchemeChangeEvent.ColorSchemeChangeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ColorSchemeChangeEvent.ColorSchemeChangeHandler handler) {
        handler.onColorSchemeChange(this);
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }
}