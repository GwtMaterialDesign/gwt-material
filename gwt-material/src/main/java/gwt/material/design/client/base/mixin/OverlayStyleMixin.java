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
package gwt.material.design.client.base.mixin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasOverlayStyle;
import gwt.material.design.client.config.DialogConfig;
import gwt.material.design.client.config.HasStyleConfig;
import gwt.material.design.client.constants.Blur;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.OverlayOption;
import gwt.material.design.jquery.client.api.JQueryElement;

/**
 * A mixin that provides styling the overlay elements {i.e {@link gwt.material.design.client.ui.MaterialDialog}, {@link gwt.material.design.client.ui.MaterialSideNav}}
 *
 * @author kevzlou7979
 */
public class OverlayStyleMixin<T extends UIObject & HasOverlayStyle> extends AbstractMixin<T>
        implements HasOverlayStyle {

    private OverlayOption overlayOption = OverlayOption.create();
    private JQueryElement overlayElement;

    public OverlayStyleMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setOverlayOption(OverlayOption overlayOption) {
        this.overlayOption = overlayOption;
    }

    @Override
    public OverlayOption getOverlayOption() {
        return overlayOption;
    }

    @Override
    public void applyOverlayStyle(JQueryElement overlayElement) {
        this.overlayElement = overlayElement;

        applyBackgroundColor(overlayOption.getBackgroundColor());
        applyBlur(overlayOption.getBlur(), false);
        applyOpacity(overlayOption.getOpacity());
        applyVisibility(overlayOption.getVisibility());
    }

    @Override
    public void resetOverlayStyle() {
        applyBlur(overlayOption.getBlur(), true);
        applyBackgroundColor(null);
        applyOpacity(null);
        applyVisibility(Style.Visibility.VISIBLE);
    }

    /**
     * Will apply the blur into the provided list of {@link JQueryElement}.
     * If reset was true then we set the filter property to empty value.
     */
    protected void applyBlur(Blur blur, boolean reset) {
        if (blur != null && blur.getTargets() != null) {
            for (JQueryElement target : blur.getTargets()) {
                String blurValue = "blur(" + blur.getValue() + "px)";
                if (reset) {
                     blurValue = "";
                }
                target.css("filter",  blurValue != null ? blurValue : "");
            }
        }
    }

    /**
     * Will apply the background color into the {@link #overlayElement}
     */
    protected void applyBackgroundColor(Color backgroundColor) {
        // Applying overlay background color
        if (overlayElement != null ) {
            if (backgroundColor != null) {
                overlayElement.addClass(backgroundColor.getCssName());
            } else {
                overlayElement.removeClass(overlayOption.getBackgroundColor().getCssName());
            }
        }
    }

    /**
     * Will apply the opacity into the {@link #overlayElement}
     */
    protected void applyOpacity(Double opacity) {
        if (overlayElement != null) {
            overlayElement.css("opacity", opacity != null ? String.valueOf(opacity) : "0.5");
        }
    }

    /**
     * Will apply the visibility into the {@link #overlayElement}
     */
    protected void applyVisibility(Style.Visibility visibility) {
        if (overlayElement != null && visibility != null) {
            overlayElement.css("visibility", visibility.getCssName());
        }
    }
}
