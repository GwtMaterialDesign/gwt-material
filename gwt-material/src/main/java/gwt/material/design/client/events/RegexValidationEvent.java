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
import gwt.material.design.client.events.RegexValidationEvent.RegexValidationHandler;

public class RegexValidationEvent extends GwtEvent<RegexValidationHandler> {

    protected String value;
    protected String regex;
    protected boolean match;
    protected GwtEvent eventSource;

    public interface RegexValidationHandler extends EventHandler {
        void onRegexValidation(RegexValidationEvent event);
    }

    public RegexValidationEvent(GwtEvent eventSource, String value, String regex, boolean match) {
        this.eventSource = eventSource;
        this.value = value;
        this.regex = regex;
        this.match = match;
    }

    public static final Type<RegexValidationHandler> TYPE = new Type<>();

    public static void fire(HasHandlers source, GwtEvent eventSource, String value, String regex, boolean match) {
        source.fireEvent(new RegexValidationEvent(eventSource, value, regex, match));
    }

    @Override
    public Type<RegexValidationHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RegexValidationHandler handler) {
        handler.onRegexValidation(this);
    }

    public static Type<RegexValidationHandler> getType() {
        return TYPE;
    }

    public GwtEvent getEventSource() {
        return eventSource;
    }

    public String getValue() {
        return value;
    }

    public String getRegex() {
        return regex;
    }

    public boolean isMatch() {
        return match;
    }
}
