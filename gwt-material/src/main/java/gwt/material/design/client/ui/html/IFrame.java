/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.AttributeMixin;

public class IFrame extends MaterialWidget {

    enum Sandbox {
        ALL_RESTRICTIONS(""),
        ALLOW_FORMS("allow-forms"),
        ALLOW_POINTER_LOCK("allow-pointer-lock"),
        ALLOW_POPUPS("allow-popups"),
        ALLOW_SAME_ORIGIN("allow-same-origin"),
        ALLOW_SCRIPTS("allow-scripts"),
        ALLOW_TOP_NAVIGATION("allow-top-navigation");

        private String restriction;

        Sandbox(String restriction) {
            this.restriction = restriction;
        }

        public String getRestriction() {
            return restriction;
        }
    }

    private AttributeMixin<IFrame> attributeMixin;

    public IFrame() {
        super(Document.get().createIFrameElement());
        setSandBoxRestriction(Sandbox.ALL_RESTRICTIONS);
    }

    public void setSandBoxRestriction(Sandbox restriction) {
        getAttributeMixin().setAttribute(restriction.getRestriction());
    }

    public String getSandBoxRestriction() {
        return getAttributeMixin().getAttribute();
    }

    public AttributeMixin<IFrame> getAttributeMixin() {
        if (attributeMixin == null) {
            attributeMixin = new AttributeMixin<>(this, "sandbox");
        }
        return attributeMixin;
    }
}
