package gwt.material.design.client.base.helper;

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


import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

// TODO: Replace with jQuery JSInterop
public class DOMHelper {

    public static Element getChildElementByClass(Element parent, String className) {
        if (parent != null) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                Node childNode = parent.getChild(i);
                if (Element.is(childNode)) {
                    Element child = Element.as(childNode);
                    if (child.getClassName().contains(className)) {
                        return child;
                    }

                    if (child.getChildCount() > 0) {
                        return getChildElementByClass(child, className);
                    }
                }
            }
        }
        return null;
    }

    public static Element getChildElementById(Element parent, String id) {
        if (parent != null) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                Node childNode = parent.getChild(i);
                if (Element.is(childNode)) {
                    Element child = Element.as(childNode);
                    if (child.getId().equals(id)) {
                        return child;
                    }

                    if (child.getChildCount() > 0) {
                        return getChildElementById(child, id);
                    }
                }
            }
        }
        return null;
    }

    public static Widget getChildWidgetById(HasWidgets parent, String id) {
        if (parent != null) {
            for (Widget child : parent) {
                if (child.getElement().getId().equals(id)) {
                    return child;
                }
            }
        }
        return null;
    }

    public static Element getElementByAttribute(String attr, String value) {
        return getElementByAttribute(RootPanel.getBodyElement().getElementsByTagName("*"), attr, value);
    }

    public static Element getElementByAttribute(NodeList<Element> elems, String attr, String value) {
        if (elems != null) {
            for (int i = 0; i < elems.getLength(); i++) {
                Element child = elems.getItem(i);
                if (child.getAttribute(attr).equals(value)) {
                    return child;
                }
            }
        }
        return null;
    }
}
