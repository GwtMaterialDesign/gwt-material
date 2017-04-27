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

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasDurationTransition;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.js.JsSliderOptions;
import gwt.material.design.client.ui.html.UnorderedList;

import static gwt.material.design.client.js.JsMaterialElement.$;
//@formatter:off

/**
 * Our slider is a simple and elegant image carousel.
 * You can also have captions that will be transitioned on their own depending on their alignment.
 * You can also have indicators that show up on the bottom of the slider.
 * Note: This is also Hammer.js compatible! Try swiping with your finger to scroll through the slider.
 *
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code<m:MaterialSection>
 * <m:MaterialSlider fullScreen="false">
 *     <m:MaterialSlideItem>
 *         <m:MaterialImage url="http://lorempixel.com/580/250/nature/1"/>
 *         <m:MaterialSlideCaption align="LEFT">
 *             <m:MaterialTitle tile="This is our big Tagline" description="Here's our small slogan."/>
 *         </m:MaterialSlideCaption>
 *     </m:MaterialSlideItem>
 *
 *     <m:MaterialSlideItem>
 *         <m:MaterialImage url="http://lorempixel.com/580/250/nature/2"/>
 *         <m:MaterialSlideCaption align="CENTER">
 *             <m:MaterialTitle tile="This is our big Tagline" description="Here's our small slogan."/>
 *         </m:MaterialSlideCaption>
 *     </m:MaterialSlideItem>
 *
 *     <m:MaterialSlideItem>
 *         <m:MaterialImage url="http://lorempixel.com/580/250/nature/3"/>
 *         <m:MaterialSlideCaption align="RIGHT">
 *             <m:MaterialTitle tile="This is our big Tagline" description="Here's our small slogan."/>
 *         </m:MaterialSlideCaption>
 *     </m:MaterialSlideItem>
 * </m:MaterialSlider>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#media">Material Slider</a>
 */
//@formatter:on
public class MaterialSlider extends MaterialWidget implements HasDurationTransition {

    private UnorderedList listContainer = new UnorderedList();

    private boolean fullWidth = true;
    private boolean indicators = true;
    private int duration = 500;
    private int interval = 6000;

    private final ToggleStyleMixin<MaterialSlider> fullScreenMixin = new ToggleStyleMixin<>(this, CssName.FULLSCREEN);

    public MaterialSlider() {
        super(Document.get().createDivElement(), CssName.SLIDER);
        build();
    }

    @Override
    protected void build() {
        listContainer.setStyleName(CssName.SLIDES);
        super.add(listContainer);
    }

    @Override
    protected void initialize() {
        JsSliderOptions options = new JsSliderOptions();
        options.full_width = fullWidth;
        options.indicators = indicators;
        options.transition = duration;
        options.interval = interval;
        $(getElement()).slider(options);
    }

    @Override
    public void add(Widget child) {
        listContainer.add(child);
    }

    @Override
    public void setHeight(String height) {
        super.setHeight(height);
        listContainer.setHeight(height);
    }

    /**
     * Set the image slider to fullscreen view.
     */
    public void setFullscreen(boolean fullscreen) {
        fullScreenMixin.setOn(fullscreen);
    }

    public boolean isFullscreen() {
        return fullScreenMixin.isOn();
    }

    public boolean isFullWidth() {
        return fullWidth;
    }

    public void setFullWidth(boolean fullWidth) {
        this.fullWidth = fullWidth;
    }

    public void pause() {
        $(getElement()).slider("pause");
    }

    public void start() {
        $(getElement()).slider("start");
    }

    public UnorderedList getListContainer() {
        return listContainer;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    public int getInterval() {
        return interval;
    }

    /**
     * Set the duration between transitions in ms. (Default: 6000)
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }
}
