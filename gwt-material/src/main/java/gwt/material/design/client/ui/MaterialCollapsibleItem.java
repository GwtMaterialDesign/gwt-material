package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialCollapsible.HasCollapsibleParent;
import gwt.material.design.client.ui.html.ListItem;

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

//@formatter:off

/**
* Collapsible element to define every items
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#collapsibles">Material Collapsibles</a>
*/
//@formatter:on
public class MaterialCollapsibleItem extends ListItem implements HasWidgets, HasCollapsibleParent {

    private MaterialCollapsible parent;
    private MaterialCollapsibleBody body;
    private MaterialCollapsibleHeader header;

    /**
     * Creates an empty collapsible item.
     */
    public MaterialCollapsibleItem() {
        super();
    }

    /**
     * Adds MaterialCollapsible contents.
     */
    public MaterialCollapsibleItem(final Widget... widgets) {
        this();
        for(Widget w : widgets){
            add(w);
        }
    }

    @Override
    public void add(Widget child) {
        if(child instanceof MaterialCollapsibleBody) {
            body = (MaterialCollapsibleBody)child;
        }
        else if(child instanceof MaterialCollapsibleHeader) {
            header = (MaterialCollapsibleHeader)child;
        }
        super.add(child);
    }

    @Override
    public boolean remove(Widget w) {
        if(w instanceof HasCollapsibleParent) {
            ((HasCollapsibleParent)w).setParent(null);
        }

        if(w.equals(body)) {
            body = null;
        } else if(w.equals(header)) {
            header = null;
        }
        return super.remove(w);
    }

    public void setParent(MaterialCollapsible parent) {
        this.parent = parent;

        for(Widget child : this) {
            if(child instanceof HasCollapsibleParent) {
                ((HasCollapsibleParent) child).setParent(parent);
            }
        }
    }

    @Override
    public void setWaves(WavesType waves) {
        super.setWaves(waves);

        // Waves change to inline block
        setDisplay(Display.BLOCK);
    }

    /**
     * Expand the body panel.
     */
    public void expand() {
        if(body != null) {
            setActive(true);
            body.setDisplay(Display.BLOCK);
        }
    }

    public void collapse() {
        if(body != null) {
            setActive(false);
            body.setDisplay(Display.NONE);
        }
    }

    public void setActive(boolean active) {
        removeStyleName("active");
        if(active) {
            parent.clearActive();
            addStyleName("active");

            if(header != null) {
                header.removeStyleName("active");
                header.addStyleName("active");
            }
        }
    }
}
