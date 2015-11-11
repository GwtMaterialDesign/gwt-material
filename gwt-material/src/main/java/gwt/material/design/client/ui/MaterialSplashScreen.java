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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.ComplexWidget;
import gwt.material.design.client.ui.html.Div;

//@formatter:off
/**
 * An initial screen that act as a loading screen
 * in order for your apps to load fully.
 *
 * <h3>Java Code Usage:</h3>
 * <pre>
 *{@code
 * MyComposite main = new MyComposite();
 * new MaterialSplashScreen(splashTime, main, logo, appName, appDescription, color, textColor);}
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#media">Material Splashscreen</a>
 */
//@formatter:on
public class MaterialSplashScreen extends ComplexWidget implements HasVisibility{

    private Div div = new Div();
    private MaterialProgress progress = new MaterialProgress();

    public MaterialSplashScreen(){
        super(Document.get().createDivElement());
        setStyleName("splash-screen");
        div.setWidth("100%");
        super.add(div);
        super.add(progress);
    }

    @Override
    public void add(Widget child) {
        div.add(child);
    }

    public void show(){
        getElement().getStyle().setDisplay(Display.BLOCK);
    }

    public void hide(){
        getElement().getStyle().setDisplay(Display.NONE);
    }

}
