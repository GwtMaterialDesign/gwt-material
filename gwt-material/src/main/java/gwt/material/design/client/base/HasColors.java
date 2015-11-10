package gwt.material.design.client.base;

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


public interface HasColors {

    /**
     * Sets the background color of material components, for example:<br/><br/>
     * <pre>panel.setBackgroundColor("blue");</pre><br/>
     * Refer to - http://gwt-material-demo.herokuapp.com/#colors for the color pallete.
     */
    void setBackgroundColor(String bgColor);

    String getBackgroundColor();

    /**
     * Set the text color of material components, for example:<br/><br/>
     * <pre>panel.setTextColor("blue darken-2");</pre><br/>
     * Refer to - http://gwt-material-demo.herokuapp.com/#colors for the color pallete.
     */
    void setTextColor(String textColor);

    String getTextColor();
}
