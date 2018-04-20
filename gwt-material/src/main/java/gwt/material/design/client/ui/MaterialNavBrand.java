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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import gwt.material.design.client.base.HasHref;
import gwt.material.design.client.base.HasNoSideNavSelection;
import gwt.material.design.client.base.HasPosition;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.base.mixin.HrefMixin;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.html.Div;

//@formatter:off

/**
 * <p>Material NavBrand is a child of MaterialNavBar that will contain text or image logo
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code
 * <m:MaterialNavBrand href="#Test" position="LEFT" text="Title"/>
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#navbar">Material NavBrand</a>
 * @see <a href="https://material.io/guidelines/components/toolbars.html#">Material Design Specification</a>
 */
//@formatter:on
public class MaterialNavBrand extends MaterialWidget implements HasHref, HasPosition, HasNoSideNavSelection {

    private Div container = new Div();
    private CssNameMixin<MaterialNavBrand, Position> positionMixin;
    private HrefMixin<MaterialNavBrand> hrefMixin;

    /**
     * Material NavBrand is a component wherein you can pass a text / logo branding of your app
     */
    public MaterialNavBrand() {
        super(Document.get().createAnchorElement(), CssName.BRAND_LOGO);
    }

    public MaterialNavBrand(String text) {
        this();
        setText(text);
    }

    public MaterialNavBrand(String text, Color textColor) {
        this(text);
        setTextColor(textColor);
    }

    public void setText(String text) {
        add(container);
        container.getElement().setInnerText(text);
    }

    public String getText() {
        return container.getElement().getInnerText();
    }

    @Override
    public String getHref() {
        return getHrefMixin().getHref();
    }

    @Override
    public void setHref(String href) {
        getHrefMixin().setHref(href);
    }

    @Override
    public String getTarget() {
        return getHrefMixin().getTarget();
    }

    @Override
    public void setTarget(String target) {
        getHrefMixin().setTarget(target);
    }

    @Override
    public Position getPosition() {
        return getPositionMixin().getCssName();
    }

    @Override
    public void setPosition(Position position) {
        getPositionMixin().setCssName(position);
    }

    public Div getContainer() {
        return container;
    }

    protected CssNameMixin<MaterialNavBrand, Position> getPositionMixin() {
        if (positionMixin == null) {
            positionMixin = new CssNameMixin<>(this);
        }
        return positionMixin;
    }

    protected HrefMixin<MaterialNavBrand> getHrefMixin() {
        if (hrefMixin == null) {
            hrefMixin = new HrefMixin<>(this);
        }
        return hrefMixin;
    }
}
