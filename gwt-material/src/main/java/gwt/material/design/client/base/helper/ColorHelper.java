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


import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.base.MaterialWidget;

/**
 * Created by Mark Kevin on 12/2/2015.
 */
public class ColorHelper {

    public static String setupComputedBackgroundColor(String color){
        MaterialWidget temp = new MaterialWidget(Document.get().createDivElement());
        temp.setBackgroundColor(color);
        RootPanel.get().add(temp);
        String computed = getComputedBackgroundColor(temp.getElement()).toLowerCase();
        temp.removeFromParent();
        return computed;
    }

    /**
     * Native call to getComputedStyle.
     */
    private static native String getComputedBackgroundColor(Element e)/*-{
        var cs = $wnd.document.defaultView.getComputedStyle(e, null);
        return cs.getPropertyValue('background-color');
    }-*/;

}
