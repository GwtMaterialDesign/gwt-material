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
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;

//@formatter:off

/**
 * MaterialNavContent is a child of {@link MaterialNavBar} that provides an area wherein
 * we can attach any widget to be placed in this extension panel.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialNavBar ui:field="navBar" layoutPosition="FIXED" height="auto" activates="sidenav" backgroundColor="PURPLE">
 *      <m:MaterialNavBrand fontSize="2.2em" paddingLeft="60" text="My Files" />
 *      <m:MaterialNavSection float="RIGHT">
 *          <m:MaterialLink iconType="APPS" />
 *          <m:MaterialLink iconType="REFRESH" />
 *      </m:MaterialNavSection>
 *      <m:MaterialNavContent paddingLeft="120" paddingTop="40" paddingBottom="20" >
 *          <m:MaterialChip text="Extension" letter="E" backgroundColor="PURPLE_DARKEN_3" letterBackgroundColor="PURPLE_LIGHTEN_3" textColor="WHITE" />
 *      </m:MaterialNavContent>
 * </m:MaterialNavBar>
 * }
 * <pre>
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#navbar">Material Nav Bar</a>
 * @see <a href="https://material.io/guidelines/components/toolbars.html#">Material Design Specification</a>
 */
//@formatter:on
public class MaterialNavContent extends MaterialWidget {

    public MaterialNavContent() {
        super(Document.get().createDivElement(), CssName.NAV_CONTENT);
    }
}
