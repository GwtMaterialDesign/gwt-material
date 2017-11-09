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

import gwt.material.design.client.base.AbstractSideNav;
import gwt.material.design.client.constants.Edge;
import gwt.material.design.client.constants.SideNavType;

//@formatter:off

/**
 * SideNav (Card) is an extension to {@link MaterialSideNav} that provides
 * a card container to it's sidenav links. Good for few sidenav link items.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialSideNavCard ui:field="sideNav" width="280" m:id="mysidebar" closeOnClick="false">
 *     <m:MaterialLink href="#about" iconPosition="LEFT" iconType="OUTLINE" text="About" textColor="BLUE"  />
 *     <m:MaterialLink href="#gettingStarted" iconPosition="LEFT" iconType="DOWNLOAD" text="Getting Started" textColor="BLUE"  >
 * </m:MaterialSideNav>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#sidenavs">Material SideNav</a>
 * @see <a href="https://material.io/guidelines/patterns/navigation-drawer.html">Material Design Specification</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#sidenav_card">Pattern</a>
 */
//@formatter:on
public class MaterialSideNavCard extends AbstractSideNav {

    public MaterialSideNavCard() {
        super(SideNavType.CARD);
    }

    @Override
    protected void setup() {
        registerHandler(addOpeningHandler(event -> pushElement(getMain(), getWidth() + 20)));
        registerHandler(addOpenedHandler(event -> {
            if (getEdge() == Edge.LEFT) {
                setLeft(0);
            } else {
                setRight(0);
            }
        }));
        registerHandler(addClosingHandler(event -> pushElement(getMain(), 0)));
        registerHandler(addClosedHandler(event -> {
            if (getEdge() == Edge.LEFT) {
                setLeft(-(getWidth() + 20));
            } else {
                setRight(-(getWidth() + 20));
            }
        }));
    }
}
