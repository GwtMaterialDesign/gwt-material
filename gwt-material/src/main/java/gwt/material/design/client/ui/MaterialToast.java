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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.jquery.client.api.Functions;

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
 */
//@formatter:on
public class MaterialToast {

    public static final int DEFAULT_LIFE = 4000;

    private Functions.Func callback;
    private Widget[] widgets;

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
        fireToast(msg, DEFAULT_LIFE, null);
    }

    /**
     * Quick fire your toast.
     *
     * @param msg        Message text for your toast.
     * @param lifeMillis how long it should present itself before being removed.
     */
    public static void fireToast(String msg, int lifeMillis) {
        fireToast(msg, lifeMillis, null);
    }

    /**
     * Quick fire your toast.
     *
     * @param msg        Message text for your toast.
     * @param lifeMillis how long it should present itself before being removed.
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
        new MaterialToast().toast(msg, DEFAULT_LIFE, className);
    }

    /**
     * @param msg Message text for your toast.
     */
    public void toast(String msg) {
        toast(msg, DEFAULT_LIFE, null);
    }

    /**
     * @param msg        Message text for your toast.
     * @param lifeMillis how long it should present itself before being removed.
     */
    public void toast(String msg, int lifeMillis) {
        toast(msg, lifeMillis, null);
    }

    /**
     * @param msg       Message text for your toast.
     * @param className class name to custom style your toast.
     */
    public void toast(String msg, String className) {
        toast(msg, DEFAULT_LIFE, className);
    }

    /**
     * @param msg        Message text for your toast.
     * @param lifeMillis how long it should present itself before being removed.
     * @param className  class name to custom style your toast.
     */
    public void toast(String msg, int lifeMillis, String className) {
        String genId = DOM.createUniqueId();
        if (className == null) {
            className = genId;
        }
        toast(msg, lifeMillis, genId, className, callback);

        if (widgets != null) {
            for (Widget widget : widgets) {
                widget.getElement().getStyle().setPaddingLeft(30, Unit.PX);
                RootPanel.get(genId).add(widget);
            }
        }
    }

    protected void toast(String msg, int lifeMillis, String id, String className, Functions.Func callback) {
        JsMaterialElement.toast(msg, lifeMillis, className, () -> {
            if (callback != null) {
                callback.call();
            }
        });
        $(".toast." + className).attr("id", id);
    }
}
