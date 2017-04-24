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
import gwt.material.design.client.base.HasNoSideNavSelection;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;

//@formatter:off

/**
 * If your sidenav requires just a plain contents like input fields (for filter forms), pictures, labels & description , MaterialSideNavContent
 * is a right widget because it implements {@link HasNoSideNavSelection} meaning it will not behave like MaterialSideNav links {@link MaterialLink}
 * that navigates and it is selectable inside the {@link MaterialSideNav}.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 *  <m:MaterialSideNavContent padding="0">
 *      <m:MaterialImage url="https://photo.elsoar.com/wp-content/images/tasty-fast-food-recipes-2.jpg" />
 *      <m:MaterialRow>
 *          <m:MaterialLabel text="File Name" fontSize="0.9em" grid="s6"/>
 *          <m:MaterialLabel text="food-recipe.jpg" grid="s6"/>
 *      </m:MaterialRow>
 *      <m:MaterialRow>
 *          <m:MaterialLabel text="Type" fontSize="0.9em" grid="s6"/>
 *          <m:MaterialLabel text="Image" grid="s6"/>
 *      </m:MaterialRow>
 *      <m:MaterialRow>
 *          <m:MaterialLabel text="File Size" fontSize="0.9em" grid="s6"/>
 *          <m:MaterialLabel text="400 kb" grid="s6"/>
 *      </m:MaterialRow>
 *      <m:MaterialRow>
 *          <m:MaterialLabel text="Owner" fontSize="0.9em" grid="s6"/>
 *          <m:MaterialLabel text="John Doe" grid="s6"/>
 *      </m:MaterialRow>
 *      <m:MaterialRow>
 *          <m:MaterialLabel text="Created" fontSize="0.9em" grid="s6"/>
 *          <m:MaterialLabel text="April 21, 2017" grid="s6"/>
 *      </m:MaterialRow>
 *  </m:MaterialSideNavContent>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#sidenavs">Material SideNav</a>
 * @see <a href="https://material.io/guidelines/patterns/navigation-drawer.html">Material Design Specification</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#sidenav_content">Pattern</a>
 */
//@formatter:on
public class MaterialSideNavContent extends MaterialWidget implements HasNoSideNavSelection {

    public MaterialSideNavContent() {
        super(Document.get().createDivElement(), CssName.SIDE_NAV_CONTENT);
    }
}
