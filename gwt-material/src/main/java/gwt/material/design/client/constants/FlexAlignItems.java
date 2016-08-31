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
package gwt.material.design.client.constants;

/**
 * @author chriswjones
 */
public enum FlexAlignItems implements BrowserPrefixCssType {
    START("start", "flex-start"),
    CENTER("center", "center"),
    END("end", "flex-end");

    private final String ieValue;
    private final String value;

    FlexAlignItems(final String ieValue, final String value) {
        this.ieValue = ieValue;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getIeValue() {
        return ieValue;
    }
}
