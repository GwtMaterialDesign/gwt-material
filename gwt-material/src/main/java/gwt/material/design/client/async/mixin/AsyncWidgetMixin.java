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
package gwt.material.design.client.async.mixin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.async.AsyncWidgetCallback;
import gwt.material.design.client.async.IsAsyncWidget;
import gwt.material.design.client.async.loader.AsyncDisplayLoader;
import gwt.material.design.client.async.loader.DefaultDisplayLoader;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CssName;

public class AsyncWidgetMixin<W extends Widget, V> implements IsAsyncWidget<W, V> {

    protected W widget;
    protected boolean loaded;
    protected AsyncDisplayLoader<V> displayLoader;
    protected AsyncWidgetCallback<W, V> asyncCallback;

    private ToggleStyleMixin<MaterialWidget> asyncStyleMixin;
    private ToggleStyleMixin<MaterialWidget> loadingStyleMixin;

    public AsyncWidgetMixin(W widget) {
        this.widget = widget;
        this.displayLoader = new DefaultDisplayLoader();
    }

    @Override
    public void load(AsyncWidgetCallback<W, V> asyncCallback) {
        loading();
        getLoadingStyleMixin().setOn(true);
        asyncCallback.load(new AsyncCallback<V>() {
            @Override
            public void onFailure(Throwable caught) {
                displayLoader.failure(caught.getMessage());
                displayLoader.finalize();
                getLoadingStyleMixin().setOn(false);
            }

            @Override
            public void onSuccess(V result) {
                displayLoader.success(result);
                displayLoader.finalize();
                getLoadingStyleMixin().setOn(false);
            }
        }, widget);
    }

    public void loading() {
        displayLoader.loading();
    }

    public void finalize() {
        displayLoader.finalize();
    }

    @Override
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void setAsynchronous(boolean asynchronous) {
        getAsyncStyleMixin().setOn(asynchronous);
    }

    @Override
    public boolean isAsynchronous() {
        return getAsyncStyleMixin().isOn();
    }

    @Override
    public void setAsyncCallback(AsyncWidgetCallback<W, V> asyncCallback) {
        this.asyncCallback = asyncCallback;
    }

    @Override
    public AsyncWidgetCallback<W, V> getAsyncCallback() {
        return asyncCallback;
    }

    @Override
    public void setAsyncDisplayLoader(AsyncDisplayLoader<V> displayLoader) {
        this.displayLoader = displayLoader;
    }

    @Override
    public AsyncDisplayLoader<V> getAsyncDisplayLoader() {
        return displayLoader;
    }

    public ToggleStyleMixin<MaterialWidget> getAsyncStyleMixin() {
        if (asyncStyleMixin == null) {
            asyncStyleMixin = new ToggleStyleMixin<>(new MaterialWidget(widget.getElement()), CssName.ASYNC);
        }
        return asyncStyleMixin;
    }

    public ToggleStyleMixin<MaterialWidget> getLoadingStyleMixin() {
        if (loadingStyleMixin == null) {
            loadingStyleMixin = new ToggleStyleMixin<>(new MaterialWidget(widget.getElement()), CssName.LOADING);
        }
        return loadingStyleMixin;
    }
}
