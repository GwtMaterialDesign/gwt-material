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

import gwt.material.design.client.constants.LoaderType;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialLoader;

public class DefaultCollapsibleItemLoader<T> implements AsyncDisplayLoader<T> {

    private MaterialCollapsibleItem item;
    private MaterialLoader loader;

    public DefaultCollapsibleItemLoader(MaterialCollapsibleItem item) {
        this.item = item;
        setupLoader();
    }

    protected void setupLoader() {
        loader = new MaterialLoader();
        loader.setContainer(item);
        loader.setType(LoaderType.PROGRESS);
    }

    @Override
    public void loading() {
        loader.show();
    }

    @Override
    public void success(T result) {
        MaterialCollapsibleBody body = (MaterialCollapsibleBody) item.getAsyncRenderer().render(result);
        item.add(body);
        item.expand();
        item.setLoaded(true);
    }

    @Override
    public void failure(String error) {

    }

    @Override
    public void finalize() {
        loader.hide();
    }
}
