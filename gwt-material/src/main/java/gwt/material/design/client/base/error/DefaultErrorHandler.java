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
package gwt.material.design.client.base.error;

import com.google.gwt.editor.client.EditorError;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.ui.MaterialHelpBlock;

import java.util.List;

/**
 * This is the default {@link ErrorHandler} implementation.
 * If there is a {@link MaterialHelpBlock} that is a child then error messages
 * will be displayed in the {@link MaterialHelpBlock}.
 * <p>
 * Example:
 * <p>
 * <pre>{@code
 * <m:MaterialRow>
 *     <m:TextBox m:id="username" ui:field="username" />
 *     <m:MaterialHelpBlock iconType="EXCLAMATION" />
 * </m:MaterialRow>
 * }</pre>
 *
 * @author Steven Jardine
 * @author Ben Dol
 */
public class DefaultErrorHandler implements ErrorHandler {

    private boolean initialized = false;

    private final Widget inputWidget;

    private MaterialHelpBlock helpBlock = null;

    /**
     * Default error handler.
     *
     * @param widget the parent of this error handler.
     */
    public DefaultErrorHandler(Widget widget) {
        super();
        assert widget != null;
        this.inputWidget = widget;
        this.inputWidget.addAttachHandler(event -> {
            if (event.isAttached()) {
                init();
            }
        });
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void clearErrors() {
        if (inputWidget instanceof HasError) {
            ((HasError) inputWidget).clearErrorOrSuccess();
        }
        if (helpBlock != null) {
            helpBlock.removeStyleName("field-error-label");
            helpBlock.removeStyleName("field-success-label");
            helpBlock.clear();
        }
    }

    /**
     * Find the sibling {@link MaterialHelpBlock}.
     *
     * @param widget the {@link Widget} to search.
     * @return the found {@link MaterialHelpBlock} of null if not found.
     */
    protected MaterialHelpBlock findHelpBlock(Widget widget) {
        if (widget != null) {
            if (widget instanceof MaterialHelpBlock) {
                return (MaterialHelpBlock) widget;
            }
            // Try and find the MaterialHelpBlock in the children of the given widget.
            if (widget instanceof HasWidgets) {
                for (Widget w : (HasWidgets) widget) {
                    if (w instanceof MaterialHelpBlock) {
                        return (MaterialHelpBlock) w;
                    }
                }
            }
            // Try and find the MaterialHelpBlock in the parent of widget.
            return findHelpBlock(widget.getParent());
        }
        return null;
    }

    /**
     * Initialize the instance.
     */
    public void init() {
        if (initialized) {
            return;
        }
        Widget parent = inputWidget.getParent();
        while (parent != null && !parent.getClass().getName().equals("com.google.gwt.user.client.ui.Widget")) {
            helpBlock = findHelpBlock(inputWidget);
            if (helpBlock != null) {
                break;
            } else {
                parent = parent.getParent();
            }
        }
        initialized = true;
    }

    @Override
    public void showErrors(List<EditorError> errors) {
        init();
        String errorMsg = "";
        for (int index = 0; index < errors.size(); index++) {
            errorMsg = errors.get(0).getMessage();
            if (index + 1 < errors.size()) {
                errorMsg += "; ";
            }
        }

        if (inputWidget instanceof HasError) {
            ((HasError) inputWidget).setError(errorMsg);
        }

        if (helpBlock != null) {
            helpBlock.addStyleName("field-error-label");
            helpBlock.removeStyleName("field-success-label");
            helpBlock.setText(errorMsg);
        }
    }
}
