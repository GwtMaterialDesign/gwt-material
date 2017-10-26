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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
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
 * @see <a href="https://material.io/guidelines/components/lists-controls.html#lists-controls-types-of-menu-controls">Material Design Specification</a>
 */
//@formatter:on
public class MaterialCollectionItem extends MaterialWidget implements HasDismissible, HasAvatar, HasType<CollectionType>, HasActive {

    private ToggleStyleMixin<MaterialCollectionItem> avatarMixin;
    private ToggleStyleMixin<MaterialCollectionItem> dismissableMixin;
    private CssTypeMixin<CollectionType, MaterialCollectionItem> typeMixin;
    private ActiveMixin<MaterialCollectionItem> activeMixin;

    public MaterialCollectionItem() {
        super(Document.get().createLIElement(), CssName.COLLECTION_ITEM);
        UiHelper.addMousePressedHandlers(this);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        HandlerRegistration handlerRegistration = addClickHandler(event -> {
            // Stop propagation of event when checkbox / other elements has
            // been clicked to avoid duplicate events.
            if (Element.as(event.getNativeEvent().getEventTarget()) != getElement()) {
                if (getType() == CollectionType.CHECKBOX) {
                    event.stopPropagation();
                    event.preventDefault();
                }
            }
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
        registerHandler(handlerRegistration);

        JsMaterialElement.initDismissableCollection();
    }

    @Override
    public void setType(CollectionType type) {
        getTypeMixin().setType(type);
        if (type == CollectionType.CHECKBOX) {
            applyCheckBoxType();
        }
    }

    protected void applyCheckBoxType() {
        if (getWidgetCount() > 0) {
            getWidget(0).getElement().getStyle().setDisplay(Style.Display.INLINE);
        }
    }

    @Override
    public CollectionType getType() {
        return getTypeMixin().getType();
    }

    @Override
    public void setDismissible(boolean dismissible) {
        getDismissableMixin().setOn(dismissible);
    }

    @Override
    public boolean isDismissible() {
        return getDismissableMixin().isOn();
    }

    @Override
    public void setAvatar(boolean avatar) {
        getAvatarMixin().setOn(avatar);
    }

    @Override
    public boolean isAvatar() {
        return getAvatarMixin().isOn();
    }

    @Override
    public void setActive(boolean active) {
        getActiveMixin().setActive(active);
    }

    @Override
    public boolean isActive() {
        return getActiveMixin().isActive();
    }

    protected CssTypeMixin<CollectionType, MaterialCollectionItem> getTypeMixin() {
        if (typeMixin == null) {
            typeMixin = new CssTypeMixin<>(this);
        }
        return typeMixin;
    }

    protected ToggleStyleMixin<MaterialCollectionItem> getDismissableMixin() {
        if (dismissableMixin == null) {
            dismissableMixin = new ToggleStyleMixin<>(this, CssName.DISMISSABLE);
        }
        return dismissableMixin;
    }

    protected ToggleStyleMixin<MaterialCollectionItem> getAvatarMixin() {
        if (avatarMixin == null) {
            avatarMixin = new ToggleStyleMixin<>(this, CssName.AVATAR);
        }
        return avatarMixin;
    }

    protected ActiveMixin<MaterialCollectionItem> getActiveMixin() {
        if (activeMixin == null) {
            activeMixin = new ActiveMixin<>(this);
        }
        return activeMixin;
    }
}
