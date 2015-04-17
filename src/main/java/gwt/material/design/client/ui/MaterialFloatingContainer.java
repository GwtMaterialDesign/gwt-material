package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

public class MaterialFloatingContainer extends MaterialPanel {

	private UnorderedList list = new UnorderedList();
	

	public MaterialFloatingContainer(SafeHtml safeHtml) {
		super(safeHtml);
	}

	public MaterialFloatingContainer(String tag, String html) {
		super(tag, html);
	}

	public MaterialFloatingContainer(String html) {
		super(html);
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("fixed-action-btn");
		this.getElement().getStyle().setBottom(1, Unit.PCT);
		this.getElement().getStyle().setRight(1, Unit.PCT);
		this.addStyleName(MaterialResources.INSTANCE.materialcss().floatingButtonsItem());
		this.add(list);
		
		int ms = list.getWidgetCount() * 100;
		for(Widget w : list){
			if(w instanceof ListItem){
				MaterialButton buttom = (MaterialButton) ((ListItem) w).getWidget(0);
				buttom.getElement().getStyle().setOpacity(0);
				buttom.getElement().setAttribute("style", "transition-delay: "+ms+"ms;");
				ms -= 100;
			}
		}
	}

	@UiChild(tagname = "initial")
	public void addInitialButton(final Widget item) {
		
		this.add(item);
	}

	@UiChild(tagname = "item")
	public void addWidget(final Widget item) {
		
		list.add(new ListItem(item));
		
	}

}
