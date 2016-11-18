/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.SpinnerColor;
import gwt.material.design.client.ui.html.Div;

//@formatter:off

/**
 * <p>If you have content that will take a long time to load, you should give the user feedback. For this reason we provide a number activity + progress indicators.
 * <h3>Java Usage:</h3>
 * <p>
 * <pre>
 * {@code
 * // FOR CIRCULAR LOADER
 * MaterialLoader.showLoading(true);
 * // FOR PROGRESS LOADER
 * MaterialLoader.showProgress(true);
 *
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#!loader">Material Loaders</a>
 */
//@formatter:on
public class MaterialLoader {
    private static Div div = new Div();
    private static MaterialPreLoader preLoader = new MaterialPreLoader();
    private static MaterialProgress progress = new MaterialProgress();

    static {
        div.setStyleName(CssName.VALIGN_WRAPPER + " " + CssName.LOADER_WRAPPER);
        preLoader.getElement().getStyle().setProperty("margin", "auto");
        preLoader.add(new MaterialSpinner(SpinnerColor.BLUE));
        preLoader.add(new MaterialSpinner(SpinnerColor.RED));
        preLoader.add(new MaterialSpinner(SpinnerColor.YELLOW));
        preLoader.add(new MaterialSpinner(SpinnerColor.GREEN));
    }

    /**
     * Show a circular loader.
     */
    public static void showLoading(boolean isShow) {
        showLoading(isShow, RootPanel.get());
    }

    /**
     * Show a circular loader on specific panel.
     */
    public static void showLoading(boolean isShow, Panel container) {
        if (isShow) {
            if (!(container instanceof RootPanel)) {
                div.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
            }
            div.setStyleName(CssName.VALIGN_WRAPPER + " " + CssName.LOADER_WRAPPER);
            div.add(preLoader);
            container.add(div);
            RootPanel.get().getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        } else {
            div.removeFromParent();
            preLoader.removeFromParent();
            RootPanel.get().getElement().getStyle().setOverflow(Style.Overflow.AUTO);
        }
    }

    /**
     * Show a progress loader.
     */
    public static void showProgress(boolean isShow) {
        showProgress(isShow, RootPanel.get());
    }

    /**
     * Show a progress loader on specific panel
     */
    public static void showProgress(boolean isShow, Panel container) {
        if (isShow) {
            if (!(container instanceof RootPanel)) {
                div.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
            }
            div.setStyleName(CssName.VALIGN_WRAPPER + " " + CssName.PROGRESS_WRAPPER);
            progress.getElement().getStyle().setProperty("margin", "auto");
            div.add(progress);
            container.add(div);
        } else {
            div.removeFromParent();
            progress.removeFromParent();
        }
    }
}
