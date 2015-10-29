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

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;

//@formatter:off
/**
* Floating action buttons are used for a promoted action. They are distinguished by a circled icon floating above the UI and have motion behaviors that include morphing, launching, and a transferring anchor point.
* 
* <p>
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* 
	<m:MaterialFAB>
		<m:MaterialButton type="FLOATING" backgroundColor="blue" icon="polymer" size="large"/>
		<m:MaterialFABList>
			<m:MaterialButton type="FLOATING" backgroundColor="red" icon="polymer"/>
			<m:MaterialButton type="FLOATING" backgroundColor="orange" icon="polymer"/>
			<m:MaterialButton type="FLOATING" backgroundColor="white" icon="polymer" iconColor="black"/>
		</m:MaterialFABList>
	</m:MaterialFAB>
* }
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#buttons">Material FAB</a>
*/
//@formatter:on
public class MaterialFAB extends ComplexWidget {

	public MaterialFAB() {
		setElement(Document.get().createDivElement());
		setStyleName("fixed-action-btn");
	}

}
