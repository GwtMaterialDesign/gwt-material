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

public class MaterialColumn extends MaterialPanel {

	private String grid = "";
	private String offset = "";
	
	public MaterialColumn(SafeHtml safeHtml) {
		super(safeHtml);
		// TODO Auto-generated constructor stub
	}

	public MaterialColumn(String tag, String html) {
		super(tag, html);
		// TODO Auto-generated constructor stub
	}

	public MaterialColumn(String html) {
		super(html);
		// TODO Auto-generated constructor stub
	}
	
	public MaterialColumn(int small, int medium, int large){
		super("");
		this.addStyleName("s"+small+" m"+medium + " l" + large);
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("col");
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
		this.addStyleName(grid);
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
		this.addStyleName("offset-" + offset);
	}

}
