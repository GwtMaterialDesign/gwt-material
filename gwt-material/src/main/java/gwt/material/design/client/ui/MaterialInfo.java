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

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.constants.CssName;

public class MaterialInfo {

    public MaterialInfo() {
    }

    public void showInfo(HTMLPanel panel, ImageResource resource, String message) {
        panel.clear();

        HTMLPanel container = new HTMLPanel("");
        container.addStyleName(CssName.MATERIAL_INFO);
        container.add(new Image(resource));
        container.add(new Label(message));
        panel.add(container);
    }
}
