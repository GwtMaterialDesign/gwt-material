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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTeamItem extends Composite implements HasName {

	private static MaterialTeamItemUiBinder uiBinder = GWT.create(MaterialTeamItemUiBinder.class);

	interface MaterialTeamItemUiBinder extends UiBinder<Widget, MaterialTeamItem> {
	}

	@UiField Image imgProfile;
	@UiField Label lblName;
	@UiField Label lblDescription;

	private ImageResource imageResource;
	
	public MaterialTeamItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ImageResource getImageResource() {
		return imageResource;
	}

	public void setImageResource(ImageResource imageResource) {
		this.imageResource = imageResource;
		imgProfile.setResource(imageResource);
	}

	@Override
	public String getName() {
		return lblName.getText();
	}

	@Override
	public void setName(String name) {
		lblName.setText(name);
	}

	public String getDescription() {
		return lblDescription.getText();
	}

	public void setDescription(String description) {
		lblDescription.setText(description);
	}
}
