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
package gwt.material.design.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TextArea;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.InputType;

import java.util.HashSet;
import java.util.Set;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * Material Text Area represents a multiple line text box where users can define comment, detail and etc.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code <m:MaterialTextArea placeholder="Your Comment" />
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @author paulux84
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#forms">Material TextArea</a>
 */
//@formatter:on
public class MaterialTextArea extends MaterialValueBox<String> {

    public enum ResizeRule {
        NONE, AUTO, FOCUS
    }

    private ResizeRule resizeRule = ResizeRule.NONE;
    private Set<HandlerRegistration> resizeHandlers;
    private HandlerRegistration attachHandler;
    private Integer originalHeight;

    public MaterialTextArea() {
        super(new TextArea());
        setType(InputType.TEXT);
        valueBoxBase.setStyleName(CssName.MATERIALIZE_TEXTAREA);
    }

    public MaterialTextArea(String placeholder) {
        this();
        setPlaceholder(placeholder);
    }

    public MaterialTextArea(String placeholder, int length) {
        this(placeholder);
        setLength(length);
    }

    @Override
    public void setText(String text) {
        super.setText(text);

        if (resizeRule.equals(ResizeRule.AUTO)) {
            triggerAutoResize();
        }
    }

    public void triggerAutoResize() {
        if (!valueBoxBase.isAttached()) {
            if (attachHandler == null) {
                attachHandler = valueBoxBase.addAttachHandler(event -> {
                    if (event.isAttached()) {
                        triggerAutoResize(valueBoxBase.getElement());
                    }
                });
            }
        } else {
            triggerAutoResize(valueBoxBase.getElement());
        }
    }

    protected void triggerAutoResize(Element element) {
        Scheduler.get().scheduleDeferred(() -> {
            $(element).trigger("autoresize", null);
        });
    }

    public ResizeRule getResizeRule() {
        return resizeRule;
    }

    public void setResizeRule(ResizeRule resizeRule) {
        this.resizeRule = resizeRule;
        if (resizeHandlers == null) {
            resizeHandlers = new HashSet<>();
        }
        removeResizeHandlers();

        switch (resizeRule) {
            case AUTO:
                resizeHandlers.add(valueBoxBase.addValueChangeHandler(event -> triggerAutoResize()));
                break;
            case FOCUS:
                resizeHandlers.add(addFocusHandler(event -> {
                    if (originalHeight == null) {
                        originalHeight = valueBoxBase.getElement().getClientHeight();
                    }
                    triggerAutoResize();
                }));

                resizeHandlers.add(addBlurHandler(event -> {
                    if (originalHeight != null) {
                        valueBoxBase.setHeight(originalHeight + "px");
                    }
                }));
                break;
        }
    }

    protected void removeResizeHandlers() {
        if (resizeHandlers != null) {
            resizeHandlers.forEach(HandlerRegistration::removeHandler);
        }
    }
}