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
package gwt.material.design.client.base;

import gwt.material.design.client.constants.OverlayOption;
import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialSideNav;
import gwt.material.design.jquery.client.api.JQueryElement;

/**
 * Ability to set the overlay style inside a {@link MaterialDialog#getOverlayElement()},
 * {@link MaterialSideNav#getOverlayElement()}
 */
public interface HasOverlayStyle {

    /**
     * Set the overlay configuration containing the blur and background color
     */
    void setOverlayOption(OverlayOption overlayOption);

    /**
     * Get the overlay configuration
     */
    OverlayOption getOverlayOption();

    /**
     * Will apply the overlay style provided by the target overlay element.
     */
    void applyOverlayStyle(JQueryElement overlayElement);

    /**
     * Will reset the overlay style
     */
    void resetOverlayStyle();
}
