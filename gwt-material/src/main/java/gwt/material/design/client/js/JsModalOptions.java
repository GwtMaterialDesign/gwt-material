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


import com.workingflows.js.jquery.client.api.Functions;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Created by Mark Kevin on 6/26/2016.
 */
@JsType(isNative = true, name="Object",  namespace = JsPackage.GLOBAL)
public class JsModalOptions {

    /*opacity: opacity,
    dismissible: dismissable,
    in_duration: inDuration,
    out_duration: outDuration,
    complete: function () { obj.@gwt.material.design.client.ui.MaterialModal::onNativeClose(Z)(true);*/

    @JsProperty
    public double opacity;

    @JsProperty
    public boolean dismissable;

    @JsProperty
    public int in_duration;

    @JsProperty
    public int out_duration;

    @JsProperty
    public Functions.Func complete;

}
