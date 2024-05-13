/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2022 GwtMaterialDesign
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
package gwt.material.design.client.sanitizer.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ValueSanitizerErrorEvent extends GwtEvent<ValueSanitizerErrorEvent.ValueSanitizerErrorHandler> {

    protected String localizedMessage;

    public ValueSanitizerErrorEvent(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public interface ValueSanitizerErrorHandler extends EventHandler {
        void onSideNavOpened(ValueSanitizerErrorEvent event);
    }

    public static final Type<ValueSanitizerErrorHandler> TYPE = new Type<>();

    public static void fire(HasHandlers source, String localizedMessage) {
        source.fireEvent(new ValueSanitizerErrorEvent(localizedMessage));
    }

    @Override
    public Type<ValueSanitizerErrorHandler> getAssociatedType() {
        return TYPE;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    @Override
    protected void dispatch(ValueSanitizerErrorHandler handler) {
        handler.onSideNavOpened(this);
    }
}