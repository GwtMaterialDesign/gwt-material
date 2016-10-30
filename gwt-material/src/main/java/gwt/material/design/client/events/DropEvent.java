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
import gwt.material.design.jquery.client.api.JQueryElement;

public class DropEvent extends GwtEvent<DropEvent.DropHandler> {

    public interface DropHandler extends EventHandler {
        void onDrop(DropEvent event);
    }

    private final JQueryElement relatedTarget;

    public static final Type<DropHandler> TYPE = new Type<>();

    public static Type<DropHandler> getType() {
        return TYPE;
    }

    public DropEvent(JQueryElement relatedTarget) {
        this.relatedTarget = relatedTarget;
    }

    public static void fire(HasHandlers source, JQueryElement relatedTarget) {
        source.fireEvent(new DropEvent(relatedTarget));
    }

    @Override
    public Type<DropHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DropHandler handler) {
        handler.onDrop(this);
    }

    public JQueryElement getRelatedTarget() {
        return relatedTarget;
    }
}