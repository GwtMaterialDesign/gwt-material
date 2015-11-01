package gwt.material.design.client;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
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

import gwt.material.design.client.resources.WithJQueryDebugResources;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MaterialWithJQueryDebug extends MaterialDesignDebug {

	@Override
	public void onModuleLoad() {
		if(isjQueryLoaded()) {
			injectDebug(WithJQueryDebugResources.INSTANCE.jQueryDebug());
		}

		super.onModuleLoad();
	}

	/**
	 * Check to see if jQuery is loaded already
	 *
	 * @return true is jQuery is loaded, false otherwise
	 */
	private native boolean isjQueryLoaded() /*-{
        return (typeof $wnd['jQuery'] !== 'undefined');
    }-*/;
}
