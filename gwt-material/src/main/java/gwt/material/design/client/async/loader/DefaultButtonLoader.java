/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.async.loader;

import gwt.material.design.client.base.AbstractIconButton;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;

public class DefaultButtonLoader implements AsyncIconDisplayLoader<String> {

    protected String initialText;
    protected IconType initialIcon;
    protected AbstractIconButton button;

    protected DefaultButtonLoader() {}

    public DefaultButtonLoader(AbstractIconButton button) {
        this.button = button;
        if (!button.isAttached()) {
            button.addAttachHandler(event -> {
                this.initialText = button.getText();
                this.initialIcon = button.getIcon().getIconType();
            });
        }
    }

    @Override
    public void loading() {
        button.setEnabled(false);
        button.setIconType(getLoadingIcon());
        new MaterialAnimation()
            .transition(Transition.ROTATEIN)
            .infinite(true)
            .animate(button.getIcon());
    }

    @Override
    public void success(String result) {
        button.setIconType(getSuccessIcon());
    }

    @Override
    public void failure(String error) {
        button.setIconType(getErrorIcon());
    }

    @Override
    public void finalize() {
        button.setEnabled(true);
        if (initialText != null) {
            button.setText(initialText);
        }

        if (initialIcon != null) {
            button.setIconType(initialIcon);
        }
    }


    @Override
    public IconType getLoadingIcon() {
        return IconType.AUTORENEW;
    }

    @Override
    public IconType getSuccessIcon() {
        return IconType.CHECK_CIRCLE;
    }

    @Override
    public IconType getErrorIcon() {
        return IconType.WARNING;
    }
}
