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
package gwt.material.design.client.async;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.async.loader.AsyncDisplayLoader;
import gwt.material.design.client.async.loader.DefaultDisplayLoader;
import gwt.material.design.client.base.AbstractValueWidget;

public abstract class AbstractAsyncWidget<V extends Object> extends AbstractValueWidget<V> implements IsAsyncWidget<V> {

    protected boolean asynchronous;
    protected AsyncDisplayLoader<V> displayLoader;
    protected AsyncWidgetCallback asyncCallback;

    public AbstractAsyncWidget(Element element) {
        super(element);
    }

    public AbstractAsyncWidget(Element element, String... initialClass) {
        super(element, initialClass);
    }

    @Override
    public void load(AsyncWidgetCallback asyncWidgetCallback) {
        if (displayLoader == null) {
            displayLoader = new DefaultDisplayLoader();
            GWT.log("Will be using the default display loader for asynchronous feature.");
        }

        displayLoader.loading();
        asyncWidgetCallback.load(new AsyncCallback<V>() {
            @Override
            public void onFailure(Throwable caught) {
                displayLoader.failure(caught.getMessage());
                displayLoader.finalize();
            }

            @Override
            public void onSuccess(V result) {
                displayLoader.success(result);
                displayLoader.finalize();
                setValue(result, true);
            }
        }, this);
    }

    @Override
    public void setAsynchronous(boolean asynchronous) {
        this.asynchronous = asynchronous;
    }

    @Override
    public boolean isAsynchronous() {
        return asynchronous;
    }

    @Override
    public AsyncWidgetCallback getAsyncCallback() {
        return asyncCallback;
    }

    @Override
    public void setAsyncCallback(AsyncWidgetCallback asyncCallback) {
        this.asyncCallback = asyncCallback;
    }

    @Override
    public AsyncDisplayLoader getAsyncDisplayLoader() {
        return displayLoader;
    }

    @Override
    public void setAsyncDisplayLoader(AsyncDisplayLoader displayLoader) {
        this.displayLoader = displayLoader;
    }
}