/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasOverlayStyle;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.LoaderType;
import gwt.material.design.client.constants.OverlayOption;
import gwt.material.design.client.constants.SpinnerColor;
import gwt.material.design.client.ui.html.Div;
import gwt.material.design.jquery.client.api.JQueryElement;

import static gwt.material.design.jquery.client.api.JQuery.$;

//@formatter:off

/**
 * <p>If you have content that will take a long time to load, you should give the user feedback. For this reason we provide a number activity + progress indicators.</p>
 * <h3>Java Usage:</h3>
 *
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
 * Timer t = new Timer()
 * { @Override
 * public void run() {
 * loader.hide();
 * }
 * };
 * t.schedule(2000);
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#loader">Material Loaders</a>
 * @see <a href="https://material.io/guidelines/components/progress-activity.html">Material Design Specification</a>
 * }
 */
//@formatter:on
public class MaterialLoader {

    private static MaterialLoader loader = new MaterialLoader(LoaderType.CIRCULAR);

    private boolean scrollDisabled;
    private Div div = new Div();
    private MaterialPreLoader preLoader = new MaterialPreLoader();
    private MaterialProgress progress = new MaterialProgress();
    private Panel container = RootPanel.get();
    private LoaderType type = LoaderType.CIRCULAR;
    private MaterialLabel messageLabel = new MaterialLabel();
    private String message;
    private HasOverlayStyle hasOverlayStyle;
    private OverlayOption overlayOption = OverlayOption.create();

    public MaterialLoader(LoaderType type) {
        this();
        setType(type);
    }

    public MaterialLoader() {
        build();
    }

    protected void build() {
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
        Widget widget = null;
        if (!(container instanceof RootPanel)) {
            if (!(container instanceof MaterialDialog)) {
                container.getElement().getStyle().setPosition(Style.Position.RELATIVE);
            }
            div.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
        }
        if (scrollDisabled) {
            RootPanel.get().getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        }
        if (type == LoaderType.CIRCULAR) {
            div.setStyleName(CssName.VALIGN_WRAPPER + " " + CssName.LOADER_WRAPPER);
            div.add(preLoader);
            widget = preLoader;
        } else if (type == LoaderType.PROGRESS) {
            div.setStyleName(CssName.VALIGN_WRAPPER + " " + CssName.PROGRESS_WRAPPER);
            progress.getElement().getStyle().setProperty("margin", "auto");
            div.add(progress);
            widget = progress;
        }

        if (message != null) {
            div.add(messageLabel);
            messageLabel.setText(message);
        }
        container.add(div);

        if (widget != null && widget instanceof HasOverlayStyle) {
            hasOverlayStyle = (HasOverlayStyle) widget;
            hasOverlayStyle.setOverlayOption(overlayOption);
            hasOverlayStyle.applyOverlayStyle($(div.getElement()));
        }
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

        if (messageLabel.isAttached()) {
            messageLabel.removeFromParent();
        }

        if (hasOverlayStyle != null) {
            hasOverlayStyle.resetOverlayStyle();
        }
    }

    /**
     * Static helper class that shows / hides a circular loader
     */
    public static void loading(boolean visible) {
        loading(visible, RootPanel.get());
    }

    /**
     * Static helper class that shows / hides a circular loader within a container
     */
    public static void loading(boolean visible, Panel container) {
        loader.setType(LoaderType.CIRCULAR);
        loader.setContainer(container);
        if (visible) {
            loader.show();
        } else {
            loader.hide();
        }
    }

    public static void loading(boolean visible, String message) {
        loader.setMessage(message);
        loading(visible);
    }

    public static void loading(boolean visible, Panel container, String message) {
        loader.setMessage(message);
        loading(visible, container);
    }

    /**
     * Static helper class that shows / hides a progress loader
     */
    public static void progress(boolean visible) {
        progress(visible, RootPanel.get());
    }

    /**
     * Static helper class that shows / hides a progress loader within a container
     */
    public static void progress(boolean visible, Panel container) {
        loader.setType(LoaderType.PROGRESS);
        loader.setContainer(container);
        if (visible) {
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
     * @param type There are two types of Loader (CIRCULAR and PROGRESS)
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

    /**
     * Will get the message of the loader
     */
    public String getMessage() {
        return message;
    }

    /**
     * Will set the message of the loader
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public void setOverlayOption(OverlayOption overlayOption) {
        this.overlayOption = overlayOption;
    }

    public OverlayOption getOverlayOption() {
        return overlayOption;
    }

    public MaterialProgress getProgress() {
        return progress;
    }

    public MaterialPreLoader getPreLoader() {
        return preLoader;
    }
}
