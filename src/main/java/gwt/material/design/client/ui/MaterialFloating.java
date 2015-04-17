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

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialFloating extends FocusPanel{

	
	private HTMLPanel panel;
	
	public MaterialFloating() {
		this.clear();
		this.getElement().getStyle().setBottom(10, Unit.PX);
		this.getElement().getStyle().setRight(10, Unit.PX);
		this.getElement().getStyle().setPosition(Position.FIXED);
		this.getElement().getStyle().setZIndex(999);
		panel = new  HTMLPanel("<a class='btn-floating btn-large waves-effect waves-light pink darken-4'><i class='mdi-content-add'></i></a>");
		this.add(panel);
	}
	
	
	
}
