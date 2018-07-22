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
package gwt.material.design.client.pwa.manifest.constants;

/**
 * Defines the developer's preferred display mode for the web application. This constants can be used
 * to {@link gwt.material.design.client.pwa.manifest.js.AppInstaller#isLaunched(String)} to detect
 * if the app is already launched thru web app manifest. <br/><br/>
 * <p>
 * {@link DisplayMode#FULLSCREEN} - All of the available display area is used and no user agent chrome is shown. <br/>
 * <p>
 * {@link DisplayMode#STANDALONE} - The application will look and feel like a standalone application. This can include <br/>
 * the application having a different window, its own icon in the application launcher, etc. In this mode, the user<br/>
 * agent will exclude UI elements for controlling navigation, but can include other UI elements such as a status bar.<br/>
 * <p>
 * {@link DisplayMode#MINIMAL_UI} - The application will look and feel like a standalone application, but will have a minimal set of UI elements for <br/>
 * controlling navigation. The elements will vary by browser.<br/>
 * <p>
 * {@link DisplayMode#BROWSER} - The application opens in a conventional browser tab or new window, depending on the browser and platform. This is the default.
 */
public interface DisplayMode {

    String FULLSCREEN = "fullscreen";
    String STANDALONE = "standalone";
    String MINIMAL_UI = "minimal-ui";
    String BROWSER = "browser";
}
