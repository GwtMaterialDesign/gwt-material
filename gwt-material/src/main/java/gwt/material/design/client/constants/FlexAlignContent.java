package gwt.material.design.client.constants;

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

/**
 * @author chriswjones
 */
public enum FlexAlignContent implements BrowserPrefixCssPropertyType {
    STRETCH("stretch", "stretch"),
    START("start", "flex-start"),
    CENTER("center", "center"),
    END("end", "flex-end"),
    SPACE_AROUND("distribute", "space-around"),
    SPACE_BETWEEN("justify", "space-between");

    private final String ieValue;
    private final String value;

    FlexAlignContent(final String ieValue, final String value) {
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
