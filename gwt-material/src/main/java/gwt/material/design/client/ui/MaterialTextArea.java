package gwt.material.design.client.ui;

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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TextArea;
import gwt.material.design.client.constants.InputType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@formatter:off

/**
 * Material Text Area represents a multiple line textbox where users can define comment, detail and etc.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code <m:MaterialTextArea placeholder="Your Comment" />
 * </pre>
 * @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material TextArea</a>
 * @author kevzlou7979
 * @author Ben Dol
 * @author paulux84
 */
//@formatter:on
public class MaterialTextArea extends MaterialValueBox<String> {

    public enum ResizeRule {
        NONE,
        AUTO,
        FOCUS
    }

    private ResizeRule resizeRule = ResizeRule.NONE;
    private Set<HandlerRegistration> resizeHandlers;
    private HandlerRegistration attachHandler;
    private Integer originalHeight;

    public MaterialTextArea() {
        super(new TextArea());
        setType(InputType.TEXT);
        valueBoxBase.setStyleName("materialize-textarea");
    }

    @Override
    public void setText(String text) {
        super.setText(text);

        if(resizeRule.equals(ResizeRule.AUTO)) {
            triggerAutoResize();
        }
    }

    public void triggerAutoResize() {
        if(!valueBoxBase.isAttached()) {
            if (attachHandler == null) {
                attachHandler = valueBoxBase.addAttachHandler(new Handler() {
                    @Override
                    public void onAttachOrDetach(AttachEvent event) {
                        if(event.isAttached()) {
                            triggerAutoResize(valueBoxBase.getElement());
                        }
                    }
                });
            }
        } else {
            triggerAutoResize(valueBoxBase.getElement());
        }
    }

    private native void triggerAutoResize(Element element) /*-{
        $wnd.jQuery(document).ready(function() {
            $wnd.jQuery(element).trigger('autoresize');
        });
    }-*/;

    public ResizeRule getResizeRule() {
        return resizeRule;
    }

    public void setResizeRule(ResizeRule resizeRule) {
        this.resizeRule = resizeRule;
        if(resizeHandlers == null) {
            resizeHandlers = new HashSet<>();
        }
        removeResizeHandlers();

        switch(resizeRule) {
            case AUTO:
                resizeHandlers.add(valueBoxBase.addValueChangeHandler(new ValueChangeHandler<String>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<String> event) {
                        triggerAutoResize();
                    }
                }));
                break;
            case FOCUS:
                resizeHandlers.add(addFocusHandler(new FocusHandler() {
                    @Override
                    public void onFocus(FocusEvent event) {
                        if(originalHeight == null) {
                            originalHeight = valueBoxBase.getElement().getClientHeight();
                        }
                        triggerAutoResize();
                    }
                }));

                resizeHandlers.add(addBlurHandler(new BlurHandler() {
                    @Override
                    public void onBlur(BlurEvent event) {
                        if(originalHeight != null) {
                            valueBoxBase.setHeight(originalHeight + "px");
                        }
                    }
                }));
                break;
        }
    }

    private void removeResizeHandlers() {
        if(resizeHandlers != null) {
            for (HandlerRegistration handlerReg : resizeHandlers) {
                handlerReg.removeHandler();
            }
        }
    }
}