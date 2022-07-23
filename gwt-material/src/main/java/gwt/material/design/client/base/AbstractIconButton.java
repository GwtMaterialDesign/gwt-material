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
package gwt.material.design.client.base;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.async.AsyncWidgetCallback;
import gwt.material.design.client.async.IsAsyncWidget;
import gwt.material.design.client.async.loader.AsyncDisplayLoader;
import gwt.material.design.client.async.loader.DefaultButtonLoader;
import gwt.material.design.client.async.mixin.AsyncWidgetMixin;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.MaterialIcon;

/**
 * @author Ben Dol
 */
public abstract class AbstractIconButton extends AbstractButton implements HasIcon, IsAsyncWidget<AbstractIconButton, String> {

    protected AsyncWidgetMixin<AbstractIconButton, String> asyncWidgetMixin;
    protected MaterialIcon icon = new MaterialIcon();

    public AbstractIconButton() {
        super();
        setAsyncDisplayLoader(new DefaultButtonLoader(this));
        setIconPosition(IconPosition.LEFT);
    }

    public AbstractIconButton(ButtonType type, String text, MaterialIcon icon) {
        this(type, text);
        this.icon = icon;
        ensureIconAttached();
    }

    public AbstractIconButton(ButtonType type, String text) {
        this();
        setType(type);
        setText(text);
        setIconPosition(IconPosition.LEFT);
    }

    public AbstractIconButton(IconType iconType) {
        this();
        setIconType(iconType);
        setIconPosition(IconPosition.LEFT);
    }

    public AbstractIconButton(ButtonType type) {
        this();
        setType(type);
        setIconPosition(IconPosition.LEFT);
    }

    public AbstractIconButton(String... initialClass) {
        this();
        setInitialClasses(initialClass);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        registerHandler(addClickHandler(event -> {
            if (isAsynchronous()) {
                load(getAsyncCallback());
            }
        }));
    }

    @Override
    public MaterialIcon getIcon() {
        return icon;
    }

    @Override
    public void setIconType(IconType iconType) {
        icon.setIconType(iconType);
        ensureIconAttached();
    }

    @Override
    public void setIconPosition(IconPosition position) {
        icon.setIconPosition(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        icon.setIconSize(size);
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        icon.setIconFontSize(size, unit);
    }

    @Override
    public void setIconColor(Color iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public Color getIconColor() {
        return icon.getIconColor();
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        icon.setIconPrefix(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return icon.isIconPrefix();
    }

    @Override
    public void setCustomIconType(String iconType) {
        icon.setCustomIconType(iconType);
        ensureIconAttached();
    }

    @Override
    public String getCustomIconType() {
        return icon.getCustomIconType();
    }

    @Override
    public void setIconDisplay(IconDisplay iconDisplay) {
        icon.setIconDisplay(iconDisplay);
    }

    @Override
    public IconDisplay getIconDisplay() {
        return icon.getIconDisplay();
    }

    /**
     * Ensure the icon is attached in slot 0.
     */
    protected void ensureIconAttached() {
        if (icon != null && !icon.isAttached()) {
            insert(icon, 0);
        }
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
    public void load(AsyncWidgetCallback<AbstractIconButton, String> asyncCallback) {
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
    public void setAsyncCallback(AsyncWidgetCallback<AbstractIconButton, String> asyncCallback) {
        getAsyncWidgetMixin().setAsyncCallback(asyncCallback);
    }

    @Override
    public AsyncWidgetCallback<AbstractIconButton, String> getAsyncCallback() {
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

    protected AsyncWidgetMixin<AbstractIconButton, String> getAsyncWidgetMixin() {
        if (asyncWidgetMixin == null) {
            asyncWidgetMixin = new AsyncWidgetMixin<>(this);
        }
        return asyncWidgetMixin;
    }
}
