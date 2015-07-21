package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSplashScreen extends Composite {

	private static MaterialSplashScreenUiBinder uiBinder = GWT.create(MaterialSplashScreenUiBinder.class);

	interface MaterialSplashScreenUiBinder extends UiBinder<Widget, MaterialSplashScreen> {
	}
	
	@UiField MaterialImage imgLogo;
	@UiField MaterialTitle title;
	@UiField MaterialPanel panel;

	private ImageResource logo;
	private String appName;
	private String appDescription;
	private String color;
	private String textColor;
	private int splashTime = 5000;
	private Composite main;
	
	public MaterialSplashScreen() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/**
	 * Material Splashcreen implementation on GWT Apps, its good for loading purposes
	 * @param splashTime - The delay time in millisecond to show the splash screen
	 * @param main - The Main COmposite or landing page after the splash screen
	 * @param logo - The logo of your app
	 * @param appName - The Name of your app
	 * @param appDescription - The Description of your app
	 * @param color - The background color of your app
	 * @param textColor - The text color of your app
	 */
	public MaterialSplashScreen(int splashTime,final Composite main, ImageResource logo, String appName, String appDescription, String color, String textColor) {
		initWidget(uiBinder.createAndBindUi(this));
		setLogo(logo);
		setAppName(appName);
		setAppDescription(appDescription);
		setColor(color);
		setTextColor(textColor);
		setMain(main);
	}
	
	/**
	 * Show the splash screen 
	 * @return
	 */
	public void show(){
		RootPanel.get().clear();
		RootPanel.get().add(this);
		Timer timer = new Timer() {
			
			@Override
			public void run() {
				MaterialSplashScreen.this.removeFromParent();
				RootPanel.get().add(getMain());
			}
		};
		timer.schedule(splashTime);
	}

	public ImageResource getLogo() {
		return logo;
	}

	/**
	 * The logo of the Splash screen 
	 * @param logo
	 */
	public void setLogo(ImageResource logo) {
		this.logo = logo;
		imgLogo.setResource(logo);
	}

	public String getAppName() {
		return appName;
	}

	/**
	 * The app name displayed on Splash Screen
	 * @param appName
	 */
	public void setAppName(String appName) {
		this.appName = appName;
		title.setTitle(appName);
	}

	
	public String getAppDescription() {
		return appDescription;
	}

	/**
	 * Optional , you can add your app description on Splash Screen
	 * @return
	 */
	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
		title.setDescription(appDescription);
	}

	public String getColor() {
		return color;
	}

	/**
	 * Background Color of your splash screen
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
		panel.setColor(color);
	}

	/**
	 * @return the textColor
	 */
	public String getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor the textColor to set
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
		title.setColor(textColor);
	}

	/**
	 * @return the imgLogo
	 */
	public MaterialImage getImgLogo() {
		return imgLogo;
	}

	/**
	 * @param imgLogo the imgLogo to set
	 */
	public void setImgLogo(MaterialImage imgLogo) {
		this.imgLogo = imgLogo;
	}
	
	/**
	 * @return the splashtime
	 */
	public int getSplashTime() {
		return splashTime;
	}

	/**
	 * How long it will take to show your splash screen, by default 5 seconds or 5000 ms
	 * @param splashTime
	 */
	public void setSplashTime(int splashTime) {
		this.splashTime = splashTime;
	}

	/**
	 * @return the main
	 */
	public Composite getMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(Composite main) {
		this.main = main;
	}

	
	

}
