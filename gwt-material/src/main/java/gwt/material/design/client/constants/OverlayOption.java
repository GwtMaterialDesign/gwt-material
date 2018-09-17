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
package gwt.material.design.client.constants;

/**
 * Overlay Configuration to be used by {@link gwt.material.design.client.base.mixin.OverlayStyleMixin#setOverlayOption(OverlayOption)}
 */
public class OverlayOption {

    private Blur blur;
    private Color backgroundColor;
    private Double opacity;

    private OverlayOption() {}

    /**
     * Will create a default overlay style configuration, by default no blurring was applied and
     * background color should be {@link Color#GREY}
     */
    public static OverlayOption create() {
        OverlayOption overlayOption = new OverlayOption();
        overlayOption.setBlur(null);
        overlayOption.setBackgroundColor(Color.BLACK);
        overlayOption.setOpacity(0.5);
        return overlayOption;
    }

    public OverlayOption(Blur blur, Color backgroundColor) {
        this.blur = blur;
        this.backgroundColor = backgroundColor;
    }

    public OverlayOption(Blur blur, Color backgroundColor, Double opacity) {
        this.blur = blur;
        this.backgroundColor = backgroundColor;
        this.opacity = opacity;
    }

    /**
     * Will get the blur object, see {@link Blur}
     */
    public Blur getBlur() {
        return blur;
    }

    /**
     * Will set the {@link Blur} configrations
     */
    public void setBlur(Blur blur) {
        this.blur = blur;
    }

    /**
     * Will get the background color of the overlay element
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Will set the background color of the overlay element
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Will get the opacity of the overlay element
     */
    public Double getOpacity() {
        return opacity;
    }

    /**
     * Will set the opacity of the overlay element
     */
    public void setOpacity(Double opacity) {
        this.opacity = opacity;
    }
}
