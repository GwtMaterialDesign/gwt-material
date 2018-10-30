/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.async.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
//@formatter:off

/**
 * @author kevzlou7979
 */
public class SuccessEvent extends GwtEvent<SuccessEvent.SuccessHandler> {

    public static final Type<SuccessHandler> TYPE = new Type<>();

    public SuccessEvent() {}

    public static Type<SuccessHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new SuccessEvent());
    }

    @Override
    public Type<SuccessHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SuccessHandler handler) {
        handler.onSuccess(this);
    }

    public interface SuccessHandler extends EventHandler {
        void onSuccess(SuccessEvent event);
    }
}
