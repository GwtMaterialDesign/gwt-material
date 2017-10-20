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
package gwt.material.design.client.base.helper;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Replaced with {@link gwt.material.design.client.events.HandlerRegistry}
 */
public final class EventHelper {

    public static void onAttachOnce(HasAttachHandlers has, AttachEvent.Handler handler) {
        HandlerRegistration[] reg = new HandlerRegistration[1];
        reg[0] = has.addAttachHandler(event -> {
            if(event.isAttached()) {
                handler.onAttachOrDetach(event);

                if (reg[0] != null) {
                    reg[0].removeHandler();
                }
            }
        });
    }
}
