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

import com.google.gwt.core.client.Scheduler;
import gwt.material.design.client.async.renderer.AsyncRenderer;
import gwt.material.design.client.constants.LoaderType;
import gwt.material.design.client.ui.MaterialListValueBox;
import gwt.material.design.client.ui.MaterialLoader;

import java.util.List;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class DefaultListValueBoxLoader<T> implements AsyncDisplayLoader<List<T>> {

    private MaterialLoader loader;
    private MaterialListValueBox<T> listValueBox;

    protected DefaultListValueBoxLoader() {}

    public DefaultListValueBoxLoader(MaterialListValueBox<T> listValueBox) {
        this.listValueBox = listValueBox;

        setupLoader();
    }

    protected void setupLoader() {
        loader = new MaterialLoader();
        loader.setContainer(listValueBox);
        loader.setType(LoaderType.PROGRESS);
    }

    @Override
    public void loading() {
        loader.show();
    }

    @Override
    public void success(List<T> result) {
        result.forEach(object -> listValueBox.addItem(object, listValueBox.getAsyncRenderer() != null ? listValueBox.getAsyncRenderer().render(object) : object.toString(), false));
        listValueBox.reload();
        $(listValueBox.getElement()).find("input").focus();
        listValueBox.setLoaded(true);
    }

    @Override
    public void failure(String error) {

    }

    @Override
    public void finalize() {
        loader.hide();
    }
}
