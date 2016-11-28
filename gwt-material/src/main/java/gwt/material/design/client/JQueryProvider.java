/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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
package gwt.material.design.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.TextResource;
import gwt.material.design.client.resources.WithJQueryDebugResources;
import gwt.material.design.client.resources.WithJQueryResources;

public class JQueryProvider {

    public TextResource jQuery() {
        return null;
    }

    public static class JQueryDebug extends JQueryProvider {
        private WithJQueryDebugResources resources;

        @Override
        public TextResource jQuery() {
            if(resources == null) {
                resources = GWT.create(WithJQueryDebugResources.class);
            }
            return resources.jQueryDebug();
        }
    }

    public static class JQueryCompressed extends JQueryProvider {
        private WithJQueryResources resources;

        @Override
        public TextResource jQuery() {
            if(resources == null) {
                resources = GWT.create(WithJQueryResources.class);
            }
            return resources.jQuery();
        }
    }
}
