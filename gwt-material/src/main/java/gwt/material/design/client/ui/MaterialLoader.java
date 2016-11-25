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
import gwt.material.design.client.constants.LoaderType;
import gwt.material.design.client.constants.SpinnerColor;
import gwt.material.design.client.ui.html.Div;

//@formatter:off

/**
 * <p>If you have content that will take a long time to load, you should give the user feedback. For this reason we provide a number activity + progress indicators.
 * <h3>Java Usage:</h3>
 * <p>
 * <pre>
 * {@code
 *
 * // FOR CIRCULAR LOADER
 * MaterialLoader.showLoading(true);
 * // FOR PROGRESS LOADER
 * MaterialLoader.showProgress(true);
 *
 * // INSTANTIATE THE LOADER COMPONENT
 * MaterialLoader loader = new MaterialLoader();
 * loader.setContainer(target);
 * loader.setType(LoaderType.CIRCULAR);
 * loader.setScrollDisabled(true);
 * loader.show();
 * Timer t = new Timer() {
 * @Override
 * public void run() {
 * loader.hide();
 * }
 * };
 * t.schedule(2000);
 *
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#loader">Material Loaders</a>
 */
//@formatter:on
public class MaterialLoader {

    private Div div = new Div();
    private static MaterialLoader loader = new MaterialLoader(LoaderType.CIRCULAR);
    private MaterialPreLoader preLoader = new MaterialPreLoader();
    private MaterialProgress progress = new MaterialProgress();
    private Panel container = RootPanel.get();
    private boolean scrollDisabled;
    private LoaderType type = LoaderType.CIRCULAR;

    public MaterialLoader(LoaderType type) {
        this();
        setType(type);
    }

    public MaterialLoader() {
        div.setStyleName(CssName.VALIGN_WRAPPER + " " + CssName.LOADER_WRAPPER);
        preLoader.getElement().getStyle().setProperty("margin", "auto");
        preLoader.add(new MaterialSpinner(SpinnerColor.BLUE));
        preLoader.add(new MaterialSpinner(SpinnerColor.RED));
        preLoader.add(new MaterialSpinner(SpinnerColor.YELLOW));
        preLoader.add(new MaterialSpinner(SpinnerColor.GREEN));
    }

    /**
     * Shows the Loader component
     */
    public void show() {
        if (!(container instanceof RootPanel)) {
            container.getElement().getStyle().setPosition(Style.Position.RELATIVE);
            div.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
        }
        if (scrollDisabled) {
            RootPanel.get().getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        }
        if (type == LoaderType.CIRCULAR) {
            div.setStyleName(CssName.VALIGN_WRAPPER + " " + CssName.LOADER_WRAPPER);
            div.add(preLoader);
        } else if (type == LoaderType.PROGRESS) {
            div.setStyleName(CssName.VALIGN_WRAPPER + " " + CssName.PROGRESS_WRAPPER);
            progress.getElement().getStyle().setProperty("margin", "auto");
            div.add(progress);
        }
        container.add(div);
    }

    /**
     * Hides the Loader component
     */
    public void hide() {
        div.removeFromParent();
        if (scrollDisabled) {
            RootPanel.get().getElement().getStyle().setOverflow(Style.Overflow.AUTO);
        }
        if (type == LoaderType.CIRCULAR) {
            preLoader.removeFromParent();
        } else if (type == LoaderType.PROGRESS) {
            progress.removeFromParent();
        }
    }

    /**
     * Static helper class that shows / hides a circular loader
     */
    public static void showLoading(boolean isShow) {
        showLoading(isShow, RootPanel.get());
    }

    /**
     * Static helper class that shows / hides a circular loader within a container
     */
    public static void showLoading(boolean isShow, Panel container) {
        loader.setType(LoaderType.CIRCULAR);
        loader.setContainer(container);
        if (isShow) {
            loader.show();
        } else {
            loader.hide();
        }
    }

    /**
     * Static helper class that shows / hides a progress loader
     */
    public static void showProgress(boolean isShow) {
        showProgress(isShow, RootPanel.get());
    }

    /**
     * Static helper class that shows / hides a progress loader within a container
     */
    public static void showProgress(boolean isShow, Panel container) {
        loader.setType(LoaderType.PROGRESS);
        loader.setContainer(container);
        if (isShow) {
            loader.show();
        } else {
            loader.hide();
        }
    }

    /**
     * Get the type of the MaterialLoader (Default CIRCULAR)
     */
    public LoaderType getType() {
        return type;
    }

    /**
     * Set the type of the MaterialLoader
     *
     * @type There are two types of Loader (CIRCULAR and PROGRESS Loaders)
     */
    public void setType(LoaderType type) {
        this.type = type;
    }

    /**
     * Get the Container that wraps the MaterialLoader (Default RootPanel)
     */
    public Panel getContainer() {
        return container;
    }

    /**
     * Set the Container of the MaterialLoader
     */
    public void setContainer(Panel container) {
        this.container = container;
    }

    /**
     * Get the value whether the scroll is enabled or disabled (Default false)
     */
    public boolean isScrollDisabled() {
        return scrollDisabled;
    }

    /**
     * Set whether the loader will allow a body scroll when it is shown
     */
    public void setScrollDisabled(boolean scrollDisabled) {
        this.scrollDisabled = scrollDisabled;
    }
}
