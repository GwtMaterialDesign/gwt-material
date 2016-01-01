package gwt.material.design.client.ui;

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


import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.constants.FooterType;
import gwt.material.design.client.ui.html.Div;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;


//@formatter:off
/**
* Footers are a great way to organize a lot of site navigation and information at the end of a page. This is where the user will look once hes finished scrolling through the current page or is looking for additional information about your website.
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
<m:MaterialFooter backgroundColor="blue">
    <m:MaterialRow>
        <m:MaterialColumn grid="s12 m6 l6">
            <m:MaterialTitle fontSize="0.7em" color="white" title="Join The Discussion" description="We provide Gitter Chat rooms in order for GWT Developers discussed and collaborate about GWT Material Design and Phonegap Integration."/>
            <m:MaterialButton ui:field="btnChat" text="CHAT" backgroundColor="blue lighten-2" waves="LIGHT"/>
        </m:MaterialColumn>
        <m:MaterialColumn grid="s12 m6 l6">
            <m:MaterialTitle fontSize="0.7em" color="white" title="GWT Phonegap" description="We provide Gitter Chat rooms in order for GWT Developers discussed and collaborate about GWT Material Design and Phonegap Integration."/>
            <m:MaterialButton ui:field="btnDownloadPhonegap" text="GWT Material APK" backgroundColor="blue lighten-2" waves="LIGHT"/>
        </m:MaterialColumn>
    </m:MaterialRow>

    <m:MaterialFooterCopyright backgroundColor="blue darken-1">
        <m:MaterialLabel text=" © 2014 Copyright Text"/>
    </m:MaterialFooterCopyright>
</m:MaterialFooter> }
* </pre>
* </p>
* 
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#footer">Material Footer</a>
*/
//@formatter:on
public class MaterialFooter extends MaterialWidget implements HasType<FooterType> {

    private Div container = new Div();
    private final CssTypeMixin<FooterType, MaterialFooter> typeMixin = new CssTypeMixin<>(this);

    public MaterialFooter() {
        super(Document.get().createElement("footer"));
        setStyleName("page-footer");
        container.setStyleName("container");
        super.add(container);
    }

    @Override
    public void add(Widget child) {
        if(child.getElement().getClassName().contains("footer-copyright")){
            super.add(child);
        } else {
            container.add(child);
        }
    }

    @Override
    public void setType(FooterType type) {
        typeMixin.setType(type);
    }

    @Override
    public FooterType getType() {
        return typeMixin.getType();
    }
}
