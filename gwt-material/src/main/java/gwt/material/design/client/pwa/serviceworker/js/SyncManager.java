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
package gwt.material.design.client.pwa.serviceworker.js;

import gwt.material.design.jquery.client.api.Promise;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

/**
 * The SyncManager interface of the the {@link ServiceWorker} API provides an interface for registering and listing sync registrations.
 */
@JsType(isNative = true)
public class SyncManager {

    /**
     * Create a new sync registration and return a Promise.
     */
    @JsMethod
    public native Promise register(String id);

    /**
     * Return a list of developer-defined identifiers for SyncManager registration.
     */
    @JsMethod
    public native Promise getTags();
}
