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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.MaterialWidget;

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
public class MaterialProgress extends MaterialWidget {

	private static MaterialProgressUiBinder uiBinder = GWT.create(MaterialProgressUiBinder.class);

	interface MaterialProgressUiBinder extends UiBinder<Widget, MaterialProgress> {
	}

	public MaterialProgress() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
