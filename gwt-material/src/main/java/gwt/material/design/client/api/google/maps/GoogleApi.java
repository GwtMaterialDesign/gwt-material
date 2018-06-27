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
package gwt.material.design.client.api.google.maps;

import gwt.material.design.client.api.ApiFeature;

/**
 * An abstract implementation for loading any {@link GoogleApi}.
 *
 * @author kevzlou7979
 */
public abstract class GoogleApi implements ApiFeature {

    protected final String apiKey;

    public GoogleApi(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String constructApiUrl() {
        return getApiUrl() + "?key=" + getApiKey();
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }
}
