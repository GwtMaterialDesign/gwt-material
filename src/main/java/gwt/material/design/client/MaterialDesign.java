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

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.TextResource;

import com.google.gwt.core.client.EntryPoint;
import gwt.material.design.client.resources.MaterialResources;

public class MaterialDesign implements EntryPoint {
	
	public void onModuleLoad() {
		inject(MaterialResources.INSTANCE.materializeJs());
		inject(MaterialResources.INSTANCE.timepickerJs());
		inject(MaterialResources.INSTANCE.animationJs());
	}

	protected void inject(TextResource resource) {
		inject(resource, true, false);
	}

	protected void inject(TextResource resource, boolean removeTag, boolean sourceUrl) {
		String text = resource.getText() +
			(sourceUrl ? "//# sourceURL="+resource.getName()+".js" : "");

		// Inject the script resource
		ScriptInjector.fromString(text)
			.setWindow(ScriptInjector.TOP_WINDOW)
			.setRemoveTag(removeTag)
			.inject();
	}
}
