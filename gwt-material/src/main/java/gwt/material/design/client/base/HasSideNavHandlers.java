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

import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.events.SideNavClosedEvent;
import gwt.material.design.client.events.SideNavClosingEvent;
import gwt.material.design.client.events.SideNavOpenedEvent;
import gwt.material.design.client.events.SideNavOpeningEvent;

public interface HasSideNavHandlers {

    /**
     * This handler will be triggered when the side nav is closed.
     */
    HandlerRegistration addClosedHandler(SideNavClosedEvent.SideNavClosedHandler handler);

    /**
     * This handler will be triggered when the side nav starts closing.
     */
    HandlerRegistration addClosingHandler(SideNavClosingEvent.SideNavClosingHandler handler);

    /**
     * This handler will be triggered when the side nav is opened.
     */
    HandlerRegistration addOpenedHandler(SideNavOpenedEvent.SideNavOpenedHandler handler);

    /**
     * This handler will be triggered when the side nav starts opening.
     */
    HandlerRegistration addOpeningHandler(SideNavOpeningEvent.SideNavOpeningHandler handler);
}
