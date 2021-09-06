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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.async.AsyncWidgetCallback;
import gwt.material.design.client.async.HasAsyncRenderer;
import gwt.material.design.client.async.IsAsyncWidget;
import gwt.material.design.client.async.loader.AsyncDisplayLoader;
import gwt.material.design.client.async.loader.DefaultCollapsibleItemLoader;
import gwt.material.design.client.async.mixin.AsyncWidgetMixin;
import gwt.material.design.client.async.renderer.AsyncRenderer;
import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.base.HasActive;
import gwt.material.design.client.base.HasProgress;
import gwt.material.design.client.base.mixin.ProgressMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.ProgressType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.events.CollapseEvent;
import gwt.material.design.client.events.ExpandEvent;
import gwt.material.design.client.ui.MaterialCollapsible.HasCollapsibleParent;

//@formatter:off

/**
 * Collapsible element to define every items
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#collapsible">Material Collapsibles</a>
 * @see <a href="https://material.io/guidelines/components/expansion-panels.html#expansion-panels-behavior">Material Design Specification</a>
 */
//@formatter:on
public class MaterialCollapsibleItem<T> extends AbstractButton implements HasWidgets, HasCollapsibleParent,
        HasProgress, HasActive, IsAsyncWidget<MaterialCollapsibleItem, T>, HasAsyncRenderer<MaterialCollapsibleBody, T> {

    private boolean active;
    private MaterialCollapsible parent;
    private MaterialCollapsibleBody body;
    private MaterialCollapsibleHeader header;
    private AsyncRenderer<MaterialCollapsibleBody,T> asyncRenderer;

    private ProgressMixin<MaterialCollapsibleItem> progressMixin;
    private AsyncWidgetMixin<MaterialCollapsibleItem, T> asyncWidgetMixin;

    /**
     * Creates an empty collapsible item.
     */
    public MaterialCollapsibleItem() {
        super();

        setAsyncDisplayLoader(new DefaultCollapsibleItemLoader(this));
    }

    /**
     * Adds MaterialCollapsible contents.
     */
    public MaterialCollapsibleItem(final Widget... widgets) {
        this();
        for (Widget w : widgets) {
            add(w);
        }
    }

    @Override
    protected Element createElement() {
        return Document.get().createLIElement();
    }

    @Override
    public void add(Widget child) {
        if (child instanceof MaterialCollapsibleBody) {
            body = (MaterialCollapsibleBody) child;
        } else if (child instanceof MaterialCollapsibleHeader) {
            header = (MaterialCollapsibleHeader) child;
            header.addClickHandler(clickEvent -> {
                if (isAsynchronous() && !isLoaded()) {
                    load(getAsyncCallback());
                    clickEvent.preventDefault();
                    clickEvent.stopPropagation();
                } else {
                    fireCollapsibleHandler();
                }
            });
        }
        super.add(child);
    }

    @Override
    public boolean remove(Widget w) {
        if (w instanceof HasCollapsibleParent) {
            ((HasCollapsibleParent) w).setParent(null);
        }

        if (w.equals(body)) {
            body = null;
        } else if (w.equals(header)) {
            header = null;
        }
        return super.remove(w);
    }

    /**
     * Expand the body panel.
     */
    public void expand() {
        if (body != null) {
            setActive(true);
        }
    }

    /**
     * Collapse the cody panel.
     */
    public void collapse() {
        if (body != null) {
            setActive(false);
        }
    }

    @Override
    public void showProgress(ProgressType type) {
        getProgressMixin().showProgress(type);
    }

    @Override
    public void setPercent(double percent) {
        getProgressMixin().setPercent(percent);
    }

    @Override
    public void hideProgress() {
        getProgressMixin().hideProgress();
    }

    @Override
    public MaterialProgress getProgress() {
        return getProgressMixin().getProgress();
    }

    /**
     * Make this item active.
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;

        if (parent != null) {
            fireCollapsibleHandler();
            removeStyleName(CssName.ACTIVE);
            if (header != null) {
                header.removeStyleName(CssName.ACTIVE);
            }
            if (active) {
                if (parent.isAccordion()) {
                    parent.clearActive();
                }
                addStyleName(CssName.ACTIVE);

                if (header != null) {
                    header.addStyleName(CssName.ACTIVE);
                }
            }

            if (body != null) {
                body.setDisplay(active ? Display.BLOCK : Display.NONE);
            }
        } else {
            GWT.log("Please make sure that the Collapsible parent is attached or existed.", new IllegalStateException());
        }
    }

    protected void fireCollapsibleHandler() {
        if (parent != null) {
            if (getElement().hasClassName(CssName.ACTIVE)) {
                parent.fireEvent(new CollapseEvent<>(this));
            } else {
                parent.fireEvent(new ExpandEvent<>(this));
            }
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setParent(MaterialCollapsible parent) {
        this.parent = parent;

        for (Widget child : this) {
            if (child instanceof HasCollapsibleParent) {
                ((HasCollapsibleParent) child).setParent(parent);
            }
        }
    }

    @Override
    public void setWaves(WavesType waves) {
        super.setWaves(waves);

        setDisplay(Display.BLOCK);
    }

    @Override
    public void setAsyncRenderer(AsyncRenderer<MaterialCollapsibleBody, T> asyncRenderer) {
        this.asyncRenderer = asyncRenderer;
    }

    @Override
    public AsyncRenderer<MaterialCollapsibleBody, T> getAsyncRenderer() {
        return asyncRenderer;
    }

    @Override
    public void setAsynchronous(boolean asynchronous) {
        getAsyncWidgetMixin().setAsynchronous(asynchronous);
    }

    @Override
    public boolean isAsynchronous() {
        return getAsyncWidgetMixin().isAsynchronous();
    }

    @Override
    public void load(AsyncWidgetCallback<MaterialCollapsibleItem, T> asyncCallback) {
        getAsyncWidgetMixin().load(asyncCallback);
    }

    @Override
    public void setLoaded(boolean loaded) {
        getAsyncWidgetMixin().setLoaded(loaded);
    }

    @Override
    public boolean isLoaded() {
        return getAsyncWidgetMixin().isLoaded();
    }

    @Override
    public void setAsyncCallback(AsyncWidgetCallback<MaterialCollapsibleItem, T> asyncCallback) {
        getAsyncWidgetMixin().setAsyncCallback(asyncCallback);
    }

    @Override
    public AsyncWidgetCallback<MaterialCollapsibleItem, T> getAsyncCallback() {
        return getAsyncWidgetMixin().getAsyncCallback();
    }

    @Override
    public void setAsyncDisplayLoader(AsyncDisplayLoader displayLoader) {
        getAsyncWidgetMixin().setAsyncDisplayLoader(displayLoader);
    }

    @Override
    public AsyncDisplayLoader getAsyncDisplayLoader() {
        return getAsyncWidgetMixin().getAsyncDisplayLoader();
    }

    public MaterialCollapsibleBody getBody() {
        return body;
    }

    public MaterialCollapsibleHeader getHeader() {
        return header;
    }

    protected ProgressMixin<MaterialCollapsibleItem> getProgressMixin() {
        if (progressMixin == null) {
            progressMixin = new ProgressMixin<>(this);
        }
        return progressMixin;
    }

    protected AsyncWidgetMixin<MaterialCollapsibleItem, T> getAsyncWidgetMixin() {
        if (asyncWidgetMixin == null) {
            asyncWidgetMixin = new AsyncWidgetMixin<>(this);
        }
        return asyncWidgetMixin;
    }
}
