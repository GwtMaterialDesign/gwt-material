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

import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.base.HasNoSideNavSelection;
import gwt.material.design.client.base.mixin.ActivatesMixin;
import gwt.material.design.client.constants.CssName;

//@formatter:off

/**
 * <p>Nav Menu is another navbar component which acts as an
 * activator to open it's sidenav
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code
 * <m:MaterialNavMenu iconType="MENU" activates="mySideNav"/>
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#navbar">Material NavMenu</a>
 * @see <a href="https://material.io/guidelines/components/toolbars.html#">Material Design Specification</a>
 */
//@formatter:on
public class MaterialNavMenu extends MaterialLink implements HasNoSideNavSelection {

    private ActivatesMixin<AbstractButton> activatesMixin;

    public MaterialNavMenu() {
        setInitialClasses(CssName.BUTTON_COLLAPSE);
    }

    @Override
    public void setActivates(String activates) {
        getActivatesMixin().setActivates(activates);
    }

    protected ActivatesMixin<AbstractButton> getActivatesMixin() {
        if (activatesMixin == null) {
            activatesMixin = new ActivatesMixin<>(this);
        }
        return activatesMixin;
    }
}
