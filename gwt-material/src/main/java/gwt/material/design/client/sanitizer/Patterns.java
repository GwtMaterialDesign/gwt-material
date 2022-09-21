/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2022 GwtMaterialDesign
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
package gwt.material.design.client.sanitizer;

public class Patterns {

    public static final String CHINESE = "[\u4e00-\u9fa5]";
    public static final String SPECIAL = "(?=.*[ -\\/:-@\\[-\\`{-~]{1,})";
    public static final String UNICODE = "[\uE700-\uE72E\uE730\uE731\uE734\uE735\uE737-\uE756]";

}
