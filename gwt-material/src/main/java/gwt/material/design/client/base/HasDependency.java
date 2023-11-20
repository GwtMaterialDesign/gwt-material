/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2023 GwtMaterialDesign
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

import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.mixin.DependencyCallback;

public interface HasDependency {

    void install(TextResource minifiedJs, TextResource debugJs, TextResource minifiedCss, TextResource debugCss);

    void install(TextResource minifiedJs, TextResource debugJs);

    void setDebug(boolean debug);

    DependencyCallback getCallback();

    boolean isDependencyLoaded(Class<?> widgetClass);

    void setDependencyLoaded(Class<?> widgetClass, boolean loaded);
}
