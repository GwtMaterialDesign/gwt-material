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
 * @author Joshua Godi
 */
public enum InputType implements Type {
    PASSWORD("password"),
    DATETIME("datetime"),
    DATETIME_LOCAL("datetime-local"),
    DATE("date"),
    MONTH("month"),
    TIME("time"),
    WEEK("week"),
    NUMBER("number"),
    EMAIL("email"),
    FILE("file"),
    URL("url"),
    SEARCH("search"),
    TEL("tel"),
    TEXT("text"),
    COLOR("color"),
    CHECKBOX("checkbox"),
    RANGE("range");

    private final String type;

    InputType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}