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

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.events.DragEndEvent;
import gwt.material.design.client.events.DragMoveEvent;
import gwt.material.design.client.events.DragOverEvent;
import gwt.material.design.client.events.DragStartEvent;

public interface HasDraggableHandlers {

    /**
     * Add a drag start handler to target element
     */
    HandlerRegistration addDragStartHandler(DragStartEvent.DragStartHandler handler);

    /**
     * Add a drag move handler to target element
     */
    HandlerRegistration addDragMoveHandler(DragMoveEvent.DragMoveHandler handler);

    /**
     * Add a drag end handler to target element
     */
    HandlerRegistration addDragEndHandler(DragEndEvent.DragEndHandler handler);

    /**
     * Add a drag over handler to the target element
     */
    HandlerRegistration addDragOverHandler(final DragOverEvent.DragOverHandler handler);
}
