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

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.HasAvatar;
import gwt.material.design.client.base.HasDismissable;
import gwt.material.design.client.base.helper.UiHelper;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CollectionType;

//@formatter:off

/**
 * Collection element to define every items
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#collections">Material Collections</a>
 */
//@formatter:on
public class MaterialCollectionItem extends MaterialWidget implements HasClickHandlers, HasDismissable, HasAvatar {

    private final ToggleStyleMixin<MaterialCollectionItem> avatarMixin = new ToggleStyleMixin<>(this, "avatar");
    private final ToggleStyleMixin<MaterialCollectionItem> dismissableMixin = new ToggleStyleMixin<>(this, "dismissable");

    private HandlerRegistration handlerReg;

    public MaterialCollectionItem() {
        super(Document.get().createLIElement());
        setStyleName("collection-item");
        UiHelper.addMousePressedHandlers(this);
    }

    public void setType(CollectionType type) {
        switch (type) {
        case AVATAR:
            addStyleName(type.getCssName());
            break;
        case CHECKBOX:
            if(getWidgetCount() > 0) {
                getWidget(0).getElement().getStyle().setProperty("display" , "inline");
            }
            if(handlerReg != null) {
                handlerReg.removeHandler();
            }
            handlerReg = addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    for(Widget w : MaterialCollectionItem.this) {
                        if(w instanceof MaterialCollectionSecondary) {
                            for(Widget a : (MaterialCollectionSecondary)w) {
                                if(a instanceof HasValue) {
                                    try {
                                        @SuppressWarnings("unchecked")
                                        HasValue<Boolean> cb = (HasValue<Boolean>) a;
                                        if (cb.getValue()) {
                                            cb.setValue(false);
                                        } else {
                                            cb.setValue(true);
                                        }
                                    } catch (ClassCastException ex) {
                                        // Ignore non-boolean has value handlers.
                                    }
                                }
                            }
                        }
                    }
                }
            });
            break;
        default:
            break;
        }
    }

    @Override
    public void setDismissable(boolean dismissable) {
        dismissableMixin.setOn(dismissable);
    }

    @Override
    public boolean isDismissable() {
        return dismissableMixin.isOn();
    }

    @Override
    public void setAvatar(boolean avatar) {
        avatarMixin.setOn(avatar);
    }

    @Override
    public boolean isAvatar() {
        return avatarMixin.isOn();
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }
}
