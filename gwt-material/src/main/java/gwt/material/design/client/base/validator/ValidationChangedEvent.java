/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2015 GwtBootstrap3
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
package gwt.material.design.client.base.validator;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Event fired when validation state changes.
 *
 * @author Steven Jardine
 */
public class ValidationChangedEvent extends GwtEvent<ValidationChangedEvent.ValidationChangedHandler> {

    /**
     * HasValidationChangedHandlers.
     */
    public interface HasValidationChangedHandlers extends HasHandlers {

        /**
         * Adds a validation changed handler.
         *
         * @param handler the handler
         * @return the handler registration
         */
        HandlerRegistration addValidationChangedHandler(ValidationChangedHandler handler);
    }

    /**
     * ValidationChangedHandler.
     */
    public interface ValidationChangedHandler extends EventHandler {

        /**
         * On validation changed.
         *
         * @param event the event
         */
        void onValidationChanged(ValidationChangedEvent event);
    }

    protected static final Type<ValidationChangedHandler> TYPE = new Type<>();

    /**
     * Fire the event.
     *
     * @param source the source
     * @param valid  the valid
     */
    public static void fire(HasHandlers source, boolean valid) {
        source.fireEvent(new ValidationChangedEvent(valid));
    }

    /**
     * Fire.
     *
     * @param source        the source
     * @param eventInstance the event instance
     */
    public static void fire(HasHandlers source, ValidationChangedEvent eventInstance) {
        source.fireEvent(eventInstance);
    }

    /**
     * Gets the event type.
     *
     * @return the type
     */
    public static Type<ValidationChangedHandler> getType() {
        return TYPE;
    }

    private boolean valid;

    /**
     * Constructor.
     */
    protected ValidationChangedEvent() {
    }

    /**
     * Constructor.
     *
     * @param valid the validation state.
     */
    public ValidationChangedEvent(boolean valid) {
        this.valid = valid;
    }

    @Override
    protected void dispatch(ValidationChangedHandler handler) {
        handler.onValidationChanged(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ValidationChangedEvent other = (ValidationChangedEvent) obj;
        return valid == other.valid;
    }

    @Override
    public Type<ValidationChangedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    public int hashCode() {
        int hashCode = 23;
        hashCode = (hashCode * 37) + Boolean.valueOf(valid).hashCode();
        return hashCode;
    }

    /**
     * Checks if is valid.
     *
     * @return true, if is valid
     */
    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString() {
        return "ValidationChangedEvent[" + valid + "]";
    }

}
