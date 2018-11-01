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
package gwt.material.design.client.async;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.async.events.FailureEvent;
import gwt.material.design.client.async.events.HasAsyncHandlers;
import gwt.material.design.client.async.events.LoadingEvent;
import gwt.material.design.client.async.events.SuccessEvent;
import gwt.material.design.client.base.MaterialWidget;

public class AsyncWidgetMixin<T extends MaterialWidget> implements HasAsyncHandlers {

    private T widget;

    public AsyncWidgetMixin(T widget) {
        this.widget = widget;
    }

    @Override
    public HandlerRegistration addLoadingHandler(LoadingEvent.LoadingHandler handler) {
        return widget.addHandler(handler, LoadingEvent.getType());
    }

    @Override
    public HandlerRegistration addErrorHandler(FailureEvent.ErrorHandler handler) {
        return widget.addHandler(handler, FailureEvent.getType());
    }

    @Override
    public HandlerRegistration addSuccessHandler(SuccessEvent.SuccessHandler handler) {
        return widget.addHandler(handler, SuccessEvent.getType());
    }
}
