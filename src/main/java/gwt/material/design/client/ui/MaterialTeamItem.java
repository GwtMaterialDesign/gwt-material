package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTeamItem extends Composite {

	private static MaterialTeamItemUiBinder uiBinder = GWT.create(MaterialTeamItemUiBinder.class);

	interface MaterialTeamItemUiBinder extends UiBinder<Widget, MaterialTeamItem> {
	}

	@UiField Image imgProfile;
	@UiField Label lblName;
	@UiField Label lblDescription;
	
	
	private String name="";
	private String description = "";
	private ImageResource profile;
	
	public MaterialTeamItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ImageResource getProfile() {
		return profile;
	}

	public void setProfile(ImageResource profile) {
		this.profile = profile;
		imgProfile.setResource(profile);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		lblName.setText(name);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		lblDescription.setText(description);
	}
	
	

}
