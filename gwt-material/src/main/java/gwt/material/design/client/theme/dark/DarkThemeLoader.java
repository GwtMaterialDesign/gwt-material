/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2019 GwtMaterialDesign
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
package gwt.material.design.client.theme.dark;

import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;

import java.util.Arrays;

public abstract class DarkThemeLoader {

    private DarkThemeLoader[] children;
    protected StyleElement styleElement = null;
    protected TextResource resource;
    protected boolean injectResource = true;

    public DarkThemeLoader(TextResource resource) {
        this.resource = resource;
    }

    public DarkThemeLoader(TextResource resource, boolean injectResource) {
        this.resource = resource;
        this.injectResource = injectResource;
    }

    public DarkThemeLoader(DarkThemeLoader... children) {
        setChildren(children);
    }

    public void load() {
        if (injectResource) {
            if (children != null) {
                for (DarkThemeLoader child : children) {
                    if (child != null) {
                        child.load();
                    }
                }
            } else {
                styleElement = StyleInjector.injectStylesheet(resource.getText());
            }
        }
    }

    public void unload() {
        if (children != null) {
            for (DarkThemeLoader loader : children) {
                loader.unload();
            }
        } else {
            if (styleElement != null) {
                styleElement.removeFromParent();
            }
        }
    }

    public void reload() {
        unload();
        load();
    }

    public DarkThemeLoader[] getChildren() {
        return children;
    }

    public void setChildren(DarkThemeLoader[] children) {
        this.children = children;
    }

    public DarkThemeLoader getChild(Class<? extends DarkThemeLoader> childClass) {
        if (children != null) {
            return Arrays.stream(children).filter(darkThemeLoader -> darkThemeLoader.getClass() == childClass).findAny().orElse(null);
        }
        return null;
    }

    public boolean isInjectResource() {
        return injectResource;
    }

    public void setInjectResource(boolean injectResource) {
        this.injectResource = injectResource;
    }
}
