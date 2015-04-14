package gwt.material.design.client.ui;

import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.ListItem;
import gwt.material.design.client.custom.UnorderedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class MaterialNavBar extends Composite {

	private static MaterialNavBarUiBinder uiBinder = GWT
			.create(MaterialNavBarUiBinder.class);

	interface MaterialNavBarUiBinder extends UiBinder<Widget, MaterialNavBar> {
	}

	@UiField
	HTMLPanel navBar;
	@UiField
	CustomAnchor anchor, navMenu;

	private String name = "sample";
	private String color = "";
	private String type = "";
	private String align = "";
	private String wave = "";
	private String sideBar = "";

	public MaterialNavBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	UnorderedList navigation, mobileNav;
	@UiField
	HTMLPanel wrapper;
	@UiField
	Image imgLogo;
	
	private String logoUrl;
	private ImageResource logoResource;

	@Override
	protected void onAttach() {
		super.onAttach();
		initNavBar();
	}

	@UiChild(tagname = "nav")
	public void addWidget(final Widget item) {
		navigation.add(new ListItem(item));
	}
	
	@UiChild(tagname = "sidebaritem")
	public void addWidgetSideNav(final Widget item) {
		mobileNav.add(new ListItem(item));
	}

	
	public static native void initNavBar()/*-{
		$wnd.jQuery(".button-collapse").sideNav();
	}-*/;

	public static native void hideNav()/*-{
		$wnd.jQuery(".button-collapse").sideNav('hide');
	}-*/;
	
	public void showSideBar(){
		onShowSideBar();
	}
	
	public static native void onShowSideBar()/*-{
		$wnd.jQuery(".button-collapse").sideNav('show');
	}-*/;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		wrapper.addStyleName(color);
	}

	public ImageResource getLogoResource() {
		return logoResource;
	}

	public void setLogoResource(ImageResource logoResource) {
		this.logoResource = logoResource;
		imgLogo.setResource(logoResource);
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
		imgLogo.setUrl(logoUrl);
	}

	public void hide() {
		hideNav();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		switch (type) {
		case "fixed":
			navBar.addStyleName("navbar-" + type);
			break;

		default:
			navBar.addStyleName("navbar-fixed");
			anchor.addStyleName("left");
			navigation.addStyleName("right");
			break;
		}

	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
		switch (align) {

		case "right":
			anchor.addStyleName("left");
			navigation.addStyleName("right");
			break;
		case "left":
			anchor.addStyleName("right");
			navigation.addStyleName("left");
			break;
		default:
			navBar.addStyleName("navbar-fixed");
			anchor.addStyleName("left");
			navigation.addStyleName("right");
			break;
		}
	}

	public String getWave() {
		return wave;
	}

	public void setWave(String wave) {
		this.wave = wave;
		for(Widget w : navigation){
			if(w instanceof ListItem){
				w.addStyleName("waves-effect waves-" + wave);
			}
		}
		for(Widget w : mobileNav){
			if(w instanceof ListItem){
				w.addStyleName("waves-effect waves-" + wave);
				w.getElement().getStyle().setDisplay(Display.BLOCK);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		navMenu.getElement().setAttribute("data-activates", name);
		mobileNav.getElement().setId(name);
	}

	public String getSideBar() {
		return sideBar;
	}

	public void setSideBar(String sideBar) {
		this.sideBar = sideBar;
		mobileNav.addStyleName(sideBar);
	}

	
}
