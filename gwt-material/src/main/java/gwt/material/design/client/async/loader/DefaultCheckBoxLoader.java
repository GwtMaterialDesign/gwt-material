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

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialPanel;

public class DefaultCheckBoxLoader implements AsyncDisplayLoader<Boolean> {

    private MaterialCheckBox checkBox;
    private MaterialPanel panel;
    private MaterialLoader loader;

    protected DefaultCheckBoxLoader() {}

    public DefaultCheckBoxLoader(MaterialCheckBox checkBox) {
        this.checkBox = checkBox;

        setupLoader();
    }

    protected void setupLoader() {
        loader = new MaterialLoader();
        loader.setContainer(checkBox);
    }

    @Override
    public void loading() {
        checkBox.setEnabled(false);
        loader.show();
    }

    @Override
    public void success(Boolean result) {
        checkBox.setValue(result, true);
    }

    @Override
    public void failure(String error) {

    }

    @Override
    public void finalize() {
        checkBox.setEnabled(true);
        loader.hide();
    }
}
