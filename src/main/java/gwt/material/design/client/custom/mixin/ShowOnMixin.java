package gwt.material.design.client.custom.mixin;

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


import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.constants.HideOn;
import gwt.material.design.client.constants.ShowOn;
import gwt.material.design.client.custom.HasHideOn;
import gwt.material.design.client.custom.HasShowOn;

/**
 * @author Ben Dol
 */
public class ShowOnMixin<H extends UIObject & HasShowOn> extends AbstractMixin<H> implements HasShowOn {

    private ShowOn showOn;

    public ShowOnMixin(final H widget) {
        super(widget);
    }

    @Override
    public void setShowOn(ShowOn showOn) {
        if(this.showOn != null) {
            uiObject.removeStyleName(this.showOn.getCssName());
        }
        this.showOn = showOn;

        if(showOn != null) {
            uiObject.addStyleName(showOn.getCssName());
        }
    }

    @Override
    public ShowOn getShowOn() {
        return showOn;
    }
}
