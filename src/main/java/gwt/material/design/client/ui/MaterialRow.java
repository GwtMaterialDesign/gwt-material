package gwt.material.design.client.ui;

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

import com.google.gwt.safehtml.shared.SafeHtml;

public class MaterialRow extends MaterialPanel {

	
	public MaterialRow() {
		super("");
	}

	public MaterialRow(SafeHtml safeHtml) {
		super(safeHtml);
		// TODO Auto-generated constructor stub
	}

	public MaterialRow(String tag, String html) {
		super(tag, html);
		// TODO Auto-generated constructor stub
	}

	public MaterialRow(String html) {
		super(html);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("row");
	}

}
