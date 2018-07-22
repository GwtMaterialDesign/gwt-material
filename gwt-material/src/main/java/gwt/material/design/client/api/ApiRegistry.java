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
package gwt.material.design.client.api;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * A class to register a {@link ApiFeature} that contains apiKey and apiUrl to load your custom API the provided a callback
 * upon injecting the resource api url as a script element into the DOM. You can also unload the api resources by calling
 * {@link ApiRegistry#unregister(ApiFeature)}.
 *
 * @author kevzlou7979
 */
public class ApiRegistry {

    protected static Map<ApiFeature, JavaScriptObject> features = new LinkedHashMap<>();

    /**
     * Will register the {@link ApiFeature} to the list of features providing also the Javascript Script element object
     * for later removal / update.
     */
    public static void register(ApiFeature apiFeature, Callback<Void, Exception> callback) {
        if (apiFeature != null && apiFeature.getApiKey() != null && !apiFeature.getApiKey().isEmpty()) {
            JavaScriptObject scriptObject = ScriptInjector.fromUrl(apiFeature.constructApiUrl())
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .setCallback(new Callback<Void, Exception>() {
                        @Override
                        public void onFailure(Exception e) {
                            callback.onFailure(e);
                        }

                        @Override
                        public void onSuccess(Void aVoid) {
                            callback.onSuccess(aVoid);
                        }
                    }).inject();
            features.put(apiFeature, scriptObject);
        }
    }

    /**
     * Will unregister the provided {@link ApiFeature} in to map of features.
     */
    public static void unregister(ApiFeature apiFeature) {
        JavaScriptObject scriptObject = features.get(apiFeature);
        if (scriptObject != null) {
            if (scriptObject.cast() instanceof Element) {
                Element scriptElement = scriptObject.cast();
                scriptElement.removeFromParent();
            }
        }
        features.remove(apiFeature);
    }

    /**
     * Will unregister all {@link ApiFeature}
     */
    public static void unregisterAllFeature() {
        for (ApiFeature apiFeature : features.keySet()) {
            unregister(apiFeature);
        }
    }

    /**
     * Get all {@link ApiFeature} lists
     */
    public static Set<ApiFeature> getAllFeatures() {
        return features.keySet();
    }
}
