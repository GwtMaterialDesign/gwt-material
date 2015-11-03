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

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.base.ComplexWidget;
import gwt.material.design.client.base.HasAvatar;
import gwt.material.design.client.base.HasDismissable;
import gwt.material.design.client.base.HasWaves;
import gwt.material.design.client.constants.CollectionType;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.helper.StyleHelper;
import gwt.material.design.client.base.mixin.WavesMixin;

//@formatter:off
/**
* Collection element to define every items
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#collections">Material Collections</a>
*///@formatter:on
public class MaterialCollectionItem extends ComplexWidget implements HasClickHandlers, HasDismissable,
		HasWaves, HasAvatar {

	private final WavesMixin<MaterialCollectionItem> wavesMixin = new WavesMixin<>(this);

	public MaterialCollectionItem() {
		super(Document.get().createLIElement());
		setStyleName("collection-item");
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
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					for(Widget w : MaterialCollectionItem.this) {
						if(w instanceof MaterialCollectionSecondary) {
							for(Widget a : (MaterialCollectionSecondary)w) {
								if(a instanceof MaterialCheckBox) {
									MaterialCheckBox  cb = (MaterialCheckBox)a;
									if(cb.getValue()) {
										cb.setValue(false);
									} else {
										cb.setValue(true);
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
		removeStyleName("dismissable");
		if(dismissable) {
			addStyleName("dismissable");
		}
	}

	@Override
	public void setWaves(WavesType waves) {
		wavesMixin.setWaves(waves);
	}

	@Override
	public WavesType getWaves() {
		return wavesMixin.getWaves();
	}

	@Override
	public void setAvatar(boolean avatar) {
		removeStyleName("avatar");
		if(avatar) {
			addStyleName("avatar");
		}
	}

	@Override
	public boolean isAvatar() {
		return StyleHelper.containsStyle(getStyleName(), "avatar");
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
}
