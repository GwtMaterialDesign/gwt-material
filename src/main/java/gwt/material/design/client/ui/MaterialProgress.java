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

import gwt.material.design.client.base.ComplexWidget;
import gwt.material.design.client.ui.html.Div;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;

//@formatter:off
/**
* Material Progress indicator to define intermediate and determinate progress bars
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* <m:MaterialProgress />
}
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#loaders">Material Progress</a>
* @author kevzlou7979
*/
public class MaterialProgress extends ComplexWidget{

	private Div div = new Div();

	public MaterialProgress() {
		super(Document.get().createDivElement());
		setStyleName("progress");
		getElement().getStyle().setMargin(0, Unit.PX);
		add(div);
		div.setStyleName("indeterminate");
	}
	
}
