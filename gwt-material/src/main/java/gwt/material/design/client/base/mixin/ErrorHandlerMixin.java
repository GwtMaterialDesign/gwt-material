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
package gwt.material.design.client.base.mixin;

import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.error.DefaultErrorHandler;
import gwt.material.design.client.base.error.ErrorHandler;
import gwt.material.design.client.base.error.ErrorHandlerType;
import gwt.material.design.client.base.error.HasErrorHandler;

import java.util.List;

/**
 * Mixin to handle error handler support.
 *
 * @param <V> the type of editor value.
 */
public class ErrorHandlerMixin<V> implements HasEditorErrors<V>, HasErrorHandler {

    private ErrorHandler errorHandler;

    private ErrorHandlerType errorHandlerType = ErrorHandlerType.DEFAULT;

    private Widget inputWidget = null;

    /**
     * Mixin for the {@link ErrorHandler} implementation.
     *
     * @param widget the widget
     */
    public ErrorHandlerMixin(Widget widget) {
        inputWidget = widget;
        errorHandler = new DefaultErrorHandler(inputWidget);
    }

    /**
     * Clear the errors.
     */
    public void clearErrors() {
        if (errorHandler != null) {
            errorHandler.clearErrors();
        }
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    @Override
    public ErrorHandlerType getErrorHandlerType() {
        return errorHandlerType;
    }

    @Override
    public void setErrorHandler(ErrorHandler handler) {
        errorHandlerType = null;
        errorHandler = handler;
    }

    @Override
    public void setErrorHandlerType(ErrorHandlerType type) {
        if (errorHandler != null) {
            errorHandler.cleanup();
        }
        errorHandlerType = type == null ? ErrorHandlerType.DEFAULT : type;
        switch (errorHandlerType) {
            case NONE:
                errorHandler = null;
                break;
            case DEFAULT:
                errorHandler = new DefaultErrorHandler(inputWidget);
        }
    }

    @Override
    public void showErrors(List<EditorError> errors) {
        if (errorHandler != null) {
            if (errors == null || errors.isEmpty()) {
                errorHandler.clearErrors();
                return;
            }
            errorHandler.showErrors(errors);
        }
    }
}
