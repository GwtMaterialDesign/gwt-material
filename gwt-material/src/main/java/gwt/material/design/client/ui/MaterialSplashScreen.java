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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.ComplexWidget;

//@formatter:off
/**
 * An initial screen that act as a loading screen
 * in order for your apps to load fully.
 *
 * <h3>Java Code Usage:</h3>
 * <pre>
 *{@code
 * MyComposite main = new MyComposite();
 * new MaterialSplashScreen(splashTime, main, logo, appName, appDescription, color, textColor);}
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#media">Material Splashscreen</a>
 */
//@formatter:on
public class MaterialSplashScreen extends ComplexWidget {

	interface MaterialSplashScreenUiBinder extends UiBinder<Widget, MaterialSplashScreen> {
	}
	
	MaterialImage imgLogo;
	MaterialTitle title;
	MaterialPanel panel;

	private ImageResource logo;
	private String appName;
	private String appDescription;
	private int splashTime = 5000;
	private Widget main;
	
	public MaterialSplashScreen() {
		super(DOM.createDiv());
		imgLogo = new MaterialImage();
		title = new MaterialTitle();
		panel = new MaterialPanel();
	}
	
	/**
	 * Material Splashscreen implementation on GWT Apps, its good for loading purposes
	 * @param splashTime - The delay time in millisecond to show the splash screen
	 * @param main - The Main Composite or landing page after the splash screen
	 * @param logo - The logo of your app
	 * @param appName - The Name of your app
	 * @param appDescription - The Description of your app
	 * @param bgColor - The background color of your app
	 * @param textColor - The text color of your app
	 */
	public MaterialSplashScreen(int splashTime,
								Widget main,
								ImageResource logo,
								String appName,
								String appDescription,
								String bgColor,
								String textColor) {
		this();
		setSplashTime(splashTime);
		setLogo(logo);
		setAppName(appName);
		setAppDescription(appDescription);
		setBackgroundColor(bgColor);
		setTextColor(textColor);
		setMain(main);
	}
	
	/**
	 * Show the splash screen 
	 */
	public void show() {
		RootPanel.get().add(this);
		RootPanel.get().add(main);
		main.getElement().getStyle().setDisplay(Display.NONE);

		new Timer() {
			@Override
			public void run() {
				MaterialSplashScreen.this.removeFromParent();
				main.getElement().getStyle().setDisplay(Display.BLOCK);
			}
			@Override
			public void cancel() {
				super.cancel();
			}
		}
		.schedule(splashTime);
	}

	public ImageResource getLogo() {
		return logo;
	}

	/**
	 * The logo of the Splash screen .
	 * @param logo ImageResource object
	 */
	public void setLogo(ImageResource logo) {
		this.logo = logo;
		imgLogo.setResource(logo);
	}

	public String getAppName() {
		return appName;
	}

	/**
	 * The app name displayed on Splash Screen.
	 * @param appName Application name string
	 */
	public void setAppName(String appName) {
		this.appName = appName;
		title.setTitle(appName);
	}
	
	public String getAppDescription() {
		return appDescription;
	}

	/**
	 * Optional , you can add your app description on Splash Screen.
	 * @param appDescription Description string
	 */
	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
		title.setDescription(appDescription);
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		panel.setBackgroundColor(bgColor);
	}

	@Override
	public String getBackgroundColor() {
		return panel.getBackgroundColor();
	}

	@Override
	public String getTextColor() {
		return title.getTextColor();
	}

	@Override
	public void setTextColor(String textColor) {
		title.setTextColor(textColor);
	}

	/**
	 * @return the logo
	 */
	public MaterialImage getImgLogo() {
		return imgLogo;
	}
	
	/**
	 * @return the splashtime
	 */
	public int getSplashTime() {
		return splashTime;
	}

	/**
	 * How long it will take to show your splash screen, by default 5 seconds or 5000 ms
	 * @param splashTime Splash time in milliseconds
	 */
	public void setSplashTime(int splashTime) {
		this.splashTime = splashTime;
	}

	/**
	 * @return the main
	 */
	public Widget getMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(Widget main) {
		this.main = main;
	}

}
