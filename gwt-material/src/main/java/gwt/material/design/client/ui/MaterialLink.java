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

import gwt.material.design.client.base.AbstractIconAnchor;
import gwt.material.design.client.constants.IconType;

//@formatter:off
/**
* 
* <p>Using Material Link you can easily add href functionality into your app for navigation
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* Links
* <m:MaterialLink href="#normal" text="Normal Link" textColor="red" icon="POLYMER" iconPosition="LEFT"/>
* 
* <m:MaterialLink href="#material" text="Link with Href" textColor="red" icon="POLYMER" iconPosition="LEFT"/>
* 
* <m:MaterialLink href="#design" text="Link with Different Icon color" textColor="black" icon="POLYMER" iconPosition="LEFT" iconColor="red"/>}
* </pre>
* </p>
* 
* @author kevzlou7979
* @author Ben Dol
* @see <a href="http://gwt-material-demo.herokuapp.com/#buttons">Material Link</a>
*/
//@formatter:on
public class MaterialLink extends AbstractIconAnchor {

	public MaterialLink(String text, String href, IconType iconType) {
		super(text, href, iconType);
	}

	public MaterialLink(String text, IconType iconType) {
		super(text, iconType);
	}

	public MaterialLink(String text) {
		super(text);
	}

	public MaterialLink(IconType iconType) {
		super(iconType);
	}

	public MaterialLink() {
		super();
	}
}
