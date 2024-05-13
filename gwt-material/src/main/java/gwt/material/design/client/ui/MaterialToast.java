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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.ToastPosition;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.jquery.client.api.JQueryElement;

import static gwt.material.design.jquery.client.api.JQuery.$;

//@formatter:off

/**
 * GWT Material provides an easy way for you to send unobtrusive alerts to your users through toasts.
 * These toasts are also placed and sized responsively, try it out by clicking the button below on
 * different device sizes.
 *
 * <p>
 * <h3>Java Usage:</h3>
 * <pre>
 * {@code
 * MaterialToast.fireToast("I love Material Design");
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 *
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#dialogs">Material Toast</a>
 * @see <a href="https://material.io/guidelines/components/snackbars-toasts.html">Material Design Specification</a>
 */
//@formatter:on
public class MaterialToast {

    public static final int DURATION = 4000;

    private Functions.Func callback;
    private Widget[] widgets;

    private JQueryElement element;

    public MaterialToast(Widget... widgets) {
        this.widgets = widgets;
    }

    public MaterialToast(Functions.Func callback, Widget... widgets) {
        this.callback = callback;
        this.widgets = widgets;
    }

    /**
     * Quick fire your toast.
     *
     * @param msg Message text for your toast.
     */
    public static void fireToast(String msg) {
        fireToast(msg, DURATION, null);
    }

    /**
     * Quick fire your toast.
     *
     * @param msg        Message text for your toast.
     * @param lifeMillis how long it should present itself before being removed.
     *                   If value is less than 0 - then it will be treated as unlimited duration
     */
    public static void fireToast(String msg, int lifeMillis) {
        fireToast(msg, lifeMillis, null);
    }

    /**
     * Quick fire your toast.
     *
     * @param msg        Message text for your toast.
     * @param lifeMillis how long it should present itself before being removed.
     *                   If value is less than 0 - then it will be treated as unlimited duration
     * @param className  class name to custom style your toast.
     */
    public static void fireToast(String msg, int lifeMillis, String className) {
        new MaterialToast().toast(msg, lifeMillis, className);
    }

    /**
     * Quick fire your toast.
     *
     * @param msg       Message text for your toast.
     * @param className class name to custom style your toast.
     */
    public static void fireToast(String msg, String className) {
        new MaterialToast().toast(msg, DURATION, className);
    }

    /**
     * @param msg Message text for your toast.
     */
    public void toast(String msg) {
        toast(msg, DURATION, null);
    }

    /**
     * @param msg        Message text for your toast.
     * @param lifeMillis how long it should present itself before being removed.
     *                   If value is less than 0 - then it will be treated as unlimited duration
     */
    public void toast(String msg, int lifeMillis) {
        toast(msg, lifeMillis, null);
    }

    /**
     * @param msg       Message text for your toast.
     * @param className class name to custom style your toast.
     */
    public void toast(String msg, String className) {
        toast(msg, DURATION, className);
    }

    public void toast(String msg, int lifeMillis, String className) {
        toast(msg, lifeMillis, className, ToastPosition.DEFAULT);
    }

    /**
     * @param msg        Message text for your toast.
     * @param lifeMillis how long it should present itself before being removed.
     *                   If value is less than 0 - then it will be treated as unlimited duration
     * @param className  class name to custom style your toast.
     */
    public void toast(String msg, int lifeMillis, String className, ToastPosition position) {
        String id;
        if (element != null) {
            id = (String) element.attr("id");
        } else {
            id = DOM.createUniqueId();
        }

        if (className == null) {
            className = id;
        } else {
            className += ' ' + id;
        }

        Object duration = lifeMillis;
        if (lifeMillis <= 0) {
            duration = "unlimited";
        }

        JsMaterialElement.toast(msg, duration, className, () -> {
            if (callback != null) {
                callback.call();
            }
        });

        element = $(".toast." + id);
        element.attr("id", id);
        //element.toggleClass(className, true);
        element.addClass(position.getName());
        if (widgets != null) {
            RootPanel toast = RootPanel.get(id);
            for (Widget widget : widgets) {
                widget.getElement().getStyle().setPaddingLeft(20, Unit.PX);
                toast.add(widget);
            }
        }
    }

    public void close() {
        String id = getId();
        if (id != null && !id.isEmpty()) {
            Widget toast = RootPanel.get(id);
            if (toast != null && toast.isAttached()) {
                element.remove();
            }
        }
    }

    public boolean isOpen() {
        String id = getId();
        if (id != null && !id.isEmpty()) {
            Widget toast = RootPanel.get(id);
            if(toast == null){
                return false;
            }
            return toast.isAttached() && toast.isVisible();
        }
        return false;
    }

    public String getId() {
        return element != null ? (String) element.attr("id") : null;
    }

    public void addWidget(Widget... widgets) {
        this.widgets = widgets;
    }

    public JQueryElement getElement() {
        return element;
    }
}
