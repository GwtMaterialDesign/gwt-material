package gwt.material.design.client.events;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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


import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import gwt.material.design.client.base.HasActive;


public class ActivateEvent extends GwtEvent<ActivateEvent.ActivateEventHandler> {

    private int indexToActivate;

    public interface ActivateEventHandler extends EventHandler {
        void onActivate(ActivateEvent event);
    }

    public static final Type<ActivateEventHandler> TYPE = new Type<>();

    public ActivateEvent(int indexToActivate) {
        this.indexToActivate = indexToActivate;
    }

    @Override
    public Type<ActivateEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ActivateEventHandler handler) {
        handler.onActivate(this);
    }

    public int getIndexToActivate() {
        return indexToActivate;
    }

    public void setIndexToActivate(int indexToActivate) {
        this.indexToActivate = indexToActivate;
    }
}
