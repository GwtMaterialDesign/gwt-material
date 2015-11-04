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
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
* Mateiral Parallax is an effect where the background content or image in this case, is moved at a different speed than the foreground content while scrolling. Check out the demo to get a better idea of it.
*
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code

<m:MaterialParallax url="http://mayastepien.nl/googlecalendar/google-drinks.jpg" height="400">
	<m:content>
		<m:MaterialTitle title="Parallax" description="Parallax is an effect where the background content orimage in this case, is moved at a different speed than the foreground content while scrolling."/>
	</m:content>
</m:MaterialParallax>

* }
*<pre>
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#showcase">Material Parallax</a>
*/
//@formatter:on
public class MaterialParallax extends ComplexWidget {

	private Div div = new Div();
	
	public MaterialParallax() {
		setElement(Document.get().createDivElement());
		setStyleName("parallax-container");
		super.add(div);
		div.setStyleName("parallax");
	}
	
	@Override
	public void add(Widget child) {
		div.add(child);
	}

	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		initParallax();
	}
	
	public native void initParallax()/*-{
		$wnd.jQuery(document).ready(function(){
	      $wnd.jQuery('.parallax').parallax();
	    });
	}-*/;
	
}
