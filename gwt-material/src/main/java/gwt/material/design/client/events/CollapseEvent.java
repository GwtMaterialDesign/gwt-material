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

public class CollapseEvent<T> extends GwtEvent<CollapseEvent.CollapseHandler<T>> {

    private static Type<CollapseHandler<?>> TYPE;

    public interface CollapseHandler<T> extends EventHandler {
        void onCollapse(CollapseEvent<T> event);
    }

    private final T target;

    public CollapseEvent(T target) {
        this.target = target;
    }

    public static <T> void fire(HasHandlers source, T target) {
        if (TYPE != null) {
            CollapseEvent<T> event = new CollapseEvent(target);
            source.fireEvent(event);
        }
    }

    public static Type<CollapseHandler<?>> getType() {
        return TYPE != null ? TYPE : (TYPE = new Type<>());
    }

    @Override
    public final Type<CollapseHandler<T>> getAssociatedType() {
        return (Type) TYPE;
    }

    public T getTarget() {
        return target;
    }

    @Override
    protected void dispatch(CollapseHandler<T> handler) {
        handler.onCollapse(this);
    }
}