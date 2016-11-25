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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import gwt.material.design.client.base.AbstractIconButton;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;

//@formatter:off

/**
 * Using Material Link you can easily add href functionality into your app for navigation
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * Links
 * <m:MaterialLink href="#normal" text="Normal Link" textColor="RED" iconType="POLYMER" iconPosition="LEFT"/>
 *
 * <m:MaterialLink href="#material" text="Link with Href" textColor="RED" iconType="POLYMER" iconPosition="LEFT"/>
 *
 * <m:MaterialLink href="#design" text="Link with Different Icon color" textColor="BLACK" iconType="POLYMER" iconPosition="LEFT" iconColor="RED"/>}
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#buttons">Material Link</a>
 */
//@formatter:on
public class MaterialLink extends AbstractIconButton {

    public MaterialLink(ButtonType type, String text, MaterialIcon icon) {
        super(type, text, icon);
    }

    public MaterialLink(String text, MaterialIcon icon) {
        super(ButtonType.LINK, text, icon);
    }

    public MaterialLink(IconType iconType) {
        super(iconType);
    }

    public MaterialLink(String text) {
        super(ButtonType.LINK, text);
    }

    public MaterialLink(String text, String href) {
        this(text);

        setTargetHistoryToken(href);
    }

    public MaterialLink(String text, String href, IconType icon) {
        this(text, href);

        setIconType(icon);
    }

    public MaterialLink() {
        super(ButtonType.LINK);
    }

    @Override
    protected Element createElement() {
        return DOM.createAnchor();
    }
}
