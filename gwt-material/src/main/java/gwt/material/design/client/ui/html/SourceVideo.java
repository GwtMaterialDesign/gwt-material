/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
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
package gwt.material.design.client.ui.html;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SourceElement;
import gwt.material.design.client.base.MaterialWidget;

public class SourceVideo extends MaterialWidget {

    public enum Type {
        MP4,
        OGV,
        WEBM
    }

    protected SourceElement element;
    protected Type type = Type.WEBM;
    protected String src;
    protected String alt;

    public SourceVideo() {
        super(Document.get().createSourceElement());
    }

    public SourceVideo(String src, Type type) {
        this();

        this.src = src;
        this.type = type;
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        element = getElement().cast();
        element.setType("video/" + type.name().toLowerCase());

        if (src != null && !src.isEmpty()) {
            element.setSrc(src);
        }

        if (alt != null && !alt.isEmpty()) {
            element.setAttribute("alt", alt);
        }
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
