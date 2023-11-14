/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2023 GwtMaterialDesign
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

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.SymbolType;

public interface HasSymbols {

    void setType(SymbolType type);

    void setSymbol(String symbol);

    void setColor(Color color);

    void setSymbolSize(String size);

    void setFilled(boolean filled);

    void setWeight(int weight);

    void setGrade(int grade);

    void setOpticalSize(int size);
}