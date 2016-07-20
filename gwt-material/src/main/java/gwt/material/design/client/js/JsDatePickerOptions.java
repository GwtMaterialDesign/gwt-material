package gwt.material.design.client.js;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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


import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Created by Mark Kevin on 6/24/2016.
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JsDatePickerOptions {

    @JsProperty
    public String container;

    @JsProperty
    public boolean selectYears;

    @JsProperty
    public boolean selectMonths;

    @JsProperty
    public String format;

}