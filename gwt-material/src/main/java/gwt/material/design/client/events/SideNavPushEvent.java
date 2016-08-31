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

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class SideNavPushEvent extends GwtEvent<SideNavPushEvent.SideNavPushHandler> {

    public interface SideNavPushHandler extends EventHandler {
        void onPush(SideNavPushEvent event);
    }

    public static final Type<SideNavPushHandler> TYPE = new Type<>();

    private final int width, duration;
    private final Element element, activator;
    private final boolean toggle;

    public SideNavPushEvent(Element element, Element activator, boolean toggle, int width, int duration) {
        this.element = element;
        this.activator = activator;
        this.toggle = toggle;
        this.width = width;
        this.duration = duration;
    }

    public static void fire(HasHandlers source, Element element, Element activator,
                            boolean toggle, int width, int duration) {
        source.fireEvent(new SideNavPushEvent(element, activator, toggle, width, duration));
    }

    public int getWidth() {
        return width;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isToggle() {
        return toggle;
    }

    public Element getElement() {
        return element;
    }

    public Element getActivator() {
        return activator;
    }

    @Override
    public Type<SideNavPushHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SideNavPushHandler handler) {
        handler.onPush(this);
    }
}