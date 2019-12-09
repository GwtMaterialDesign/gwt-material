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
package gwt.material.design.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ToggleReadOnlyEvent extends GwtEvent<ToggleReadOnlyEvent.ToggleReadOnlyHandler> {

    private boolean readOnly;

    public interface ToggleReadOnlyHandler extends EventHandler {
        void onToggleReadOnly(ToggleReadOnlyEvent event);
    }

    public ToggleReadOnlyEvent(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public static final Type<ToggleReadOnlyHandler> TYPE = new Type<>();

    public static Type<ToggleReadOnlyHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, boolean readOnly) {
        source.fireEvent(new ToggleReadOnlyEvent(readOnly));
    }

    @Override
    public Type<ToggleReadOnlyHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ToggleReadOnlyHandler handler) {
        handler.onToggleReadOnly(this);
    }

    public boolean isReadOnly() {
        return readOnly;
    }
}