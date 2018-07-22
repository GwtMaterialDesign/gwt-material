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
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.events.ExpandEvent.ExpandHandler;

public class ExpandEvent<T> extends GwtEvent<ExpandEvent.ExpandHandler<T>> {

    private static Type<ExpandEvent.ExpandHandler<?>> TYPE;

    public interface ExpandHandler<T> extends EventHandler {
        void onExpand(ExpandEvent<T> event);
    }

    private final T target;

    public ExpandEvent(T target) {
        this.target = target;
    }

    public static <T> void fire(HasHandlers source, T target) {
        if (TYPE != null) {
            ExpandEvent<T> event = new ExpandEvent(target);
            source.fireEvent(event);
        }
    }

    public static Type<ExpandEvent.ExpandHandler<?>> getType() {
        return TYPE != null ? TYPE : (TYPE = new Type<>());
    }

    @Override
    public final Type<ExpandEvent.ExpandHandler<T>> getAssociatedType() {
        return (Type) TYPE;
    }

    public T getTarget() {
        return target;
    }

    @Override
    protected void dispatch(ExpandEvent.ExpandHandler<T> handler) {
        handler.onExpand(this);
    }
}