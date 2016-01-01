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

import com.google.gwt.dom.client.Document;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.HasHref;

//@formatter:off
/**
* Collection element to define secondary content such as MaterialIcons
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#collections">Material Collections</a>
*///@formatter:on
public class MaterialCollectionSecondary extends MaterialWidget implements HasHref {

    public MaterialCollectionSecondary() {
        super(Document.get().createAnchorElement());
        setStyleName("secondary-content");
    }

    @Override
    public String getHref() {
        return getElement().getAttribute("href");
    }

    @Override
    public void setHref(String href) {
        getElement().setAttribute("href", href);
    }

    @Override
    public void setTarget(String target) {
        getElement().setAttribute("target", target);
    }

    @Override
    public String getTarget() {
        return getElement().getAttribute("target");
    }
}
