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
package gwt.material.design.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ObservedEvent extends GwtEvent<ObservedEvent.ObservedHandler> {

    public interface ObservedHandler extends EventHandler {
        void onObserved(ObservedEvent event);
    }

    public static final Type<ObservedHandler> TYPE = new Type<>();

    private final String old;
    private final String value;

    public ObservedEvent(String old, String value) {
        this.old = old;
        this.value = value;
    }

    public static void fire(HasHandlers source, String old, String value) {
        source.fireEvent(new ObservedEvent(old, value));
    }

    public String getOld() {
        return old;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Type<ObservedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ObservedHandler handler) {
        handler.onObserved(this);
    }
}