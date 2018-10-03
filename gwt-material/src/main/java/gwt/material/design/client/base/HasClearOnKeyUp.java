/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.base;

/**
 * See {@link gwt.material.design.client.base.mixin.ClearOnKeyUpMixin}
 */
public interface HasClearOnKeyUp {

    /**
     * Will set if we enable clearing the field values after {@link com.google.gwt.event.dom.client.KeyUpEvent}
     * was fired. Enabled By default to all {@link AbstractValueWidget}
     */
    void setClearOnKeyUp(boolean value);

    /**
     * Check if clear on key up was enabled.
     */
    boolean isClearOnKeyUp();

    /**
     * Will set the keycode to be checked during the {@link com.google.gwt.event.dom.client.KeyUpEvent}
     * {@link com.google.gwt.event.dom.client.KeyCodes#KEY_ESCAPE} by Default.
     */
    void setClearKeyCode(int keyCode);

    /**
     * Will get the clear key code {@link com.google.gwt.event.dom.client.KeyCodes#KEY_ESCAPE} by default.
     */
    int getClearKeyCode();
}
