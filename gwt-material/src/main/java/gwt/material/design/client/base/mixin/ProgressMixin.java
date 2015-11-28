package gwt.material.design.client.base.mixin;

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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasProgress;
import gwt.material.design.client.constants.ProgressType;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialProgress;

/**
 * @author kevzlou7979
 */

public class ProgressMixin<T extends UIObject & HasProgress>
        extends AbstractMixin<T> implements HasProgress {

    private MaterialProgress progress = new MaterialProgress();

    public ProgressMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void showProgress(ProgressType type) {
        if(uiObject instanceof MaterialNavBar){
            ((MaterialNavBar) uiObject).add(progress);
        }else if(uiObject instanceof MaterialCollapsibleItem){
            applyCollapsibleProgress(true);
        }
    }

    @Override
    public void setPercent(double percent) {
        progress.setPercent(percent);
    }

    @Override
    public void hideProgress() {
        if(uiObject instanceof MaterialNavBar) {
            progress.removeFromParent();
        }else if(uiObject instanceof MaterialCollapsibleItem){
            applyCollapsibleProgress(false);
        }

    }

    private void applyCollapsibleProgress(boolean isShow) {
        MaterialCollapsibleItem item = (MaterialCollapsibleItem) uiObject;
        MaterialCollapsibleBody body = (MaterialCollapsibleBody) item.getWidget(1);
        if(!uiObject.getElement().getClassName().contains("active")) {
            return;
        }else {
            if (isShow) {
                body.setDisplay(Style.Display.NONE);
                item.add(progress);
            } else {
                body.setDisplay(Style.Display.BLOCK);
                progress.removeFromParent();
            }
        }
    }
}
