package gwt.material.design.client.base.mixin;

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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasFlexbox;
import gwt.material.design.client.base.helper.BrowserPrefixHelper;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.Flex;
import gwt.material.design.client.constants.FlexAlignContent;
import gwt.material.design.client.constants.FlexAlignItems;
import gwt.material.design.client.constants.FlexAlignSelf;
import gwt.material.design.client.constants.FlexDirection;
import gwt.material.design.client.constants.FlexJustifyContent;
import gwt.material.design.client.constants.FlexWrap;

/**
 * Mixin for Flexbox layout
 *
 * @author chriswjones
 */
public class FlexboxMixin<T extends Widget & HasFlexbox> extends AbstractMixin<T> implements HasFlexbox {

    public FlexboxMixin(T uiObject) {
        super(uiObject);
    }

    private Display displayValueBeforeHidden;

    public void setDisplay(Style.Display display) {
        setDisplay(Display.parse(display));
    }

    public void setDisplay(Display display) {
        if (display == null) {
            displayValueBeforeHidden = null;
            uiObject.getElement().getStyle().clearDisplay();
            return;
        }

        if (display != Display.NONE) {
            displayValueBeforeHidden = display;
        }

        if (display.equals(Display.FLEX)) {
            String[] displayValues = {"-webkit-box", "-moz-box", "-ms-box", "-webkit-flex", "-moz-flex", "flex"};
            for (String d : displayValues) {
                uiObject.getElement().getStyle().setProperty("display", d);
            }
            return;
        }

        if (display.getGwtDisplay() != null) {
            uiObject.getElement().getStyle().setDisplay(display.getGwtDisplay());
        } else {
            uiObject.getElement().getStyle().clearDisplay();
        }
    }

    public void setVisible(boolean visible) {
        uiObject.setVisible(visible);

        // setVisible(false) sets display:none, if the control is flex before hidden
        // we need to reset to display:flex when the control is made visible again
        if (displayValueBeforeHidden != null && visible) {
            setDisplay(displayValueBeforeHidden);
        }
    }

    public void setFlexDirection(FlexDirection flexDirection) {
        boolean isCurrentlyVisible = uiObject.isVisible();

        if (flexDirection != null) {
            setDisplay(Display.FLEX);
        }
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), new String[]{"MsFlexDirection", "WebkitFlexDirection", "MozFlexDirection", "flexDirection"}, flexDirection != null ? flexDirection.getValue() : null);

        // Updating the display to Flex will set display:flex and override the visibility of the control
        // this ensures that if you setVisible(false) it will not become visible unless calling setVisible(true)
        if (!isCurrentlyVisible) {
            setVisible(false);
        }
    }

    public void setFlex(Flex flex) {
        if (flex == null) {
            setFlexGrow(null);
            setFlexShrink(null);
            setFlexBasis(null);
            return;
        }

        setFlexGrow(flex.getGrow());
        setFlexShrink(flex.getShrink());
        setFlexBasis(flex.getBasis());
    }

    public void setFlexGrow(Integer flexGrow) {
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), new String[]{"MsFlexGrow", "WebkitFlexGrow", "MozFlexGrow", "flexGrow"}, flexGrow != null ? flexGrow.toString() : null);
    }

    public void setFlexShrink(Integer flexShrink) {
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), new String[]{"MsFlexShrink", "WebkitFlexShrink", "MozFlexShrink", "flexShrink"}, flexShrink != null ? flexShrink.toString() : null);
    }

    public void setFlexBasis(String flexBasis) {
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), new String[]{"MsFlexBasis", "WebkitFlexBasis", "MozFlexBasis", "flexBasis"}, flexBasis);
    }

    public void setFlexOrder(Integer flexOrder) {
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), new String[]{"MsFlexOrder", "WebkitOrder", "MozFlexOrder", "order"}, flexOrder != null ? flexOrder.toString() : null);
    }

    public void setFlexWrap(FlexWrap flexWrap) {
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), new String[]{"MsFlexWrap", "WebkitFlexWrap", "MozFlexWrap", "flexWrap"}, flexWrap != null ? flexWrap.getValue() : null);
    }

    public void setFlexAlignContent(FlexAlignContent flexAlignContent) {
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), "MsFlexLinePack", new String[]{"WebkitAlignContent", "MozFlexAlignContent", "alignContent"}, flexAlignContent);
    }

    public void setFlexAlignSelf(FlexAlignSelf flexAlignSelf) {
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), "MsFlexItemAlign", new String[]{"WebkitAlignSelf", "MozFlexItemAlign", "alignSelf"}, flexAlignSelf);
    }

    public void setFlexAlignItems(FlexAlignItems flexAlignItems) {
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), "MsFlexAlign", new String[]{"WebkitAlignItems", "MozFlexAlign", "alignItems"}, flexAlignItems);
    }

    public void setFlexJustifyContent(FlexJustifyContent flexJustifyContent) {
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(), "MsFlexPack", new String[]{"WebkitJustifyContent", "MozJustifyContent", "justifyContent"}, flexJustifyContent);
    }
}
