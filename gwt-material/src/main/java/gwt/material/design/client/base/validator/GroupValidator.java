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

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;
import gwt.material.design.client.base.validator.ValidationChangedEvent.ValidationChangedHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Useful for validating a group of fields that implement the {@link HasValidators} interface.
 *
 * @author Steven Jardine
 */
public class GroupValidator implements ValidationChangedEvent.HasValidationChangedHandlers {

    private final SimpleEventBus eventBus;

    private boolean fireEvents = false;

    private final Map<HasValidators<?>, Boolean> fields;

    private final Map<HasValidators<?>, HandlerRegistration> registrations;

    private Boolean groupValid = null;

    /**
     * Constructor.
     */
    public GroupValidator() {
        fields = new LinkedHashMap<>();
        registrations = new LinkedHashMap<>();
        eventBus = new SimpleEventBus();
    }

    /**
     * Adds a field to the group.
     *
     * @param <T>   the generic type
     * @param field the field
     */
    public <T extends Widget & HasValidators<?>> void add(final T field) {
        fields.put(field, field.validate(false));
        if (field.isAttached()) {
            updateStateAndNotify();
        }
        registrations.put(field, field.addValidationChangedHandler(event -> {
            fields.put(field, event.isValid());
            if (fireEvents) {
                updateStateAndNotify();
            }
        }));
    }

    @Override
    public HandlerRegistration addValidationChangedHandler(ValidationChangedHandler handler) {
        return eventBus.addHandler(ValidationChangedEvent.getType(), handler);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

    /**
     * Removes a field from the validation group.
     *
     * @param <T>   the generic type
     * @param field the field
     * @return true, if successful
     */
    public <T extends Widget & HasValidators<?>> boolean remove(final T field) {
        fields.remove(field);
        HandlerRegistration reg = registrations.remove(field);
        if (reg != null) {
            reg.removeHandler();
            return true;
        }
        return false;
    }

    /**
     * Update the state of the validator and notify via {@link EventBus} any changed handlers.
     */
    protected void updateStateAndNotify() {
        Boolean oldGroupValid = groupValid;
        groupValid = true;
        for (Boolean valid : fields.values()) {
            groupValid &= valid;
        }
        if (groupValid != oldGroupValid) {
            eventBus.fireEvent(new ValidationChangedEvent(groupValid));
        }
    }

    /**
     * Validate the group. This calls {@link Validator #validate(Editor, Object)} on each field in the group.
     *
     * @return true, if successful
     */
    public boolean validate() {
        return validate(true);
    }

    /**
     * Validate the group. This calls {@link Validator #validate(Editor, Object)} on each field in the group.
     *
     * @param show do we want to show the user the result of the validate via ui marks?
     * @return true, if successful
     */
    public boolean validate(boolean show) {
        fireEvents = false;
        for (HasValidators<?> field : fields.keySet()) {
            field.validate(show);
        }
        fireEvents = true;
        updateStateAndNotify();
        return groupValid;
    }
}
