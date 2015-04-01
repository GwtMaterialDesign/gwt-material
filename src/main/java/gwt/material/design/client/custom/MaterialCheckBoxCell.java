package gwt.material.design.client.custom;

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

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class MaterialCheckBoxCell extends CheckboxCell {

	private static final SafeHtml INPUT_CHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked/><label/>");
	private static final SafeHtml INPUT_UNCHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\"/><label/>");

	public MaterialCheckBoxCell() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterialCheckBoxCell(boolean dependsOnSelection, boolean handlesSelection) {
		super(dependsOnSelection, handlesSelection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, Boolean value, SafeHtmlBuilder sb) {
		Object key = context.getKey();
		Boolean viewData = getViewData(key);
		if (viewData != null && viewData.equals(value)) {
			clearViewData(key);
			viewData = null;
		}

		if (value != null && ((viewData != null) ? viewData : value)) {
			sb.append(INPUT_CHECKED);
		}
		else {
			sb.append(INPUT_UNCHECKED);
		}
	}

}