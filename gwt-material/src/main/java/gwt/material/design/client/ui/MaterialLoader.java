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

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.ui.html.Div;

//@formatter:off

/**
 *
 * <p>If you have content that will take a long time to load, you should give the user feedback. For this reason we provide a number activity + progress indicators.
 * <h3>Java Usage:</h3>
 *
 * <pre>
 * {@code
// FOR CIRCULAR LOADER
MaterialLoader.showLoading(true);
// FOR PROGRESS LOADER
MaterialLoader.showProgress(true);

</pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#loaders">Material Loaders</a>
 */
//@formatter:on
public class MaterialLoader {
    private static Div div = new Div();
    private static MaterialPreLoader preLoader = new MaterialPreLoader();
    private static MaterialProgress progress = new MaterialProgress();

    static {
        div.setStyleName("valign-wrapper loader-wrapper");
        preLoader.getElement().getStyle().setProperty("margin", "auto");
        preLoader.add(new MaterialSpinner("blue"));
        preLoader.add(new MaterialSpinner("red"));
        preLoader.add(new MaterialSpinner("yellow"));
        preLoader.add(new MaterialSpinner("green"));
    }

    /**
     * Show a circular loader.
     */
    public static void showLoading(boolean isShow) {
        showLoading(isShow, RootPanel.get());
    }

    public static void showLoading(boolean isShow, Panel con) {
        if (isShow) {
            if(!(con instanceof RootPanel)) {
                div.getElement().getStyle().setProperty("position", "absolute");
            }
            div.add(preLoader);
            con.add(div);
        } else {
            div.removeFromParent();
            preLoader.removeFromParent();
        }
    }

    /**
     * Show a progress loader.
     */
    public static void showProgress(boolean isShow) {
        if (isShow) {
            div.setStyleName("valign-wrapper  progress-wrapper");
            progress.getElement().getStyle().setProperty("margin", "auto");
            div.add(progress);
            RootPanel.get().add(div);
        } else {
            div.removeFromParent();
            progress.removeFromParent();
        }
    }
}
