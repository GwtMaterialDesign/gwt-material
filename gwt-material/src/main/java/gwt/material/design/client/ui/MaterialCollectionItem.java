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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.helper.UiHelper;
import gwt.material.design.client.base.mixin.ActiveMixin;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CollectionType;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.js.JsMaterialElement;

//@formatter:off

/**
 * Collection element to define every items
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#collections">Material Collections</a>
 */
//@formatter:on
public class MaterialCollectionItem extends MaterialWidget implements HasDismissible, HasAvatar, HasType<CollectionType>, HasActive {

    private final ToggleStyleMixin<MaterialCollectionItem> avatarMixin = new ToggleStyleMixin<>(this, CssName.AVATAR);
    private final ToggleStyleMixin<MaterialCollectionItem> dismissableMixin = new ToggleStyleMixin<>(this, CssName.DISMISSABLE);
    private final CssTypeMixin<CollectionType, MaterialCollectionItem> typeMixin = new CssTypeMixin<>(this);
    private final ActiveMixin<MaterialCollectionItem> activeMixin = new ActiveMixin<>(this);

    private HandlerRegistration handlerReg;

    public MaterialCollectionItem() {
        super(Document.get().createLIElement(), CssName.COLLECTION_ITEM);
        UiHelper.addMousePressedHandlers(this);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        initDismissableCollection();
    }

    protected void initDismissableCollection() {
        JsMaterialElement.initDismissableCollection();
    }

    @Override
    public void setType(CollectionType type) {
        typeMixin.setType(type);
        if (type == CollectionType.CHECKBOX) {
            applyCheckBoxType();
        }
    }

    private void applyCheckBoxType() {
        if (getWidgetCount() > 0) {
            getWidget(0).getElement().getStyle().setDisplay(Style.Display.INLINE);
        }
        if (handlerReg != null) {
            handlerReg.removeHandler();
            handlerReg = null;
        }
        handlerReg = addClickHandler(event -> {
            for (Widget w : MaterialCollectionItem.this) {
                if (w instanceof MaterialCollectionSecondary) {
                    for (Widget a : (MaterialCollectionSecondary) w) {
                        if (a instanceof HasValue) {
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
        });
    }

    @Override
    public CollectionType getType() {
        return typeMixin.getType();
    }

    @Override
    public void setDismissible(boolean dismissible) {
        dismissableMixin.setOn(dismissible);
    }

    @Override
    public boolean isDismissible() {
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
    public void setActive(boolean active) {
        activeMixin.setActive(active);
    }

    @Override
    public boolean isActive() {
        return activeMixin.isActive();
    }
}
