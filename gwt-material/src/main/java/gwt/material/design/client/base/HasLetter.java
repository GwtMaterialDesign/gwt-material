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
package gwt.material.design.client.base;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.html.Span;

/**
 * @author kevzlou7979
 */
public interface HasLetter {

    /**
     * Set the letter as character.
     */
    void setLetter(String letter);

    /**
     * Get the letter as character.
     */
    String getLetter();

    /**
     * Get the span / label widget of the letter
     */
    Span getLetterLabel();

    /**
     * Set the letter color.
     */
    void setLetterColor(Color letterColor);

    /**
     * Set the letter background color.
     */
    void setLetterBackgroundColor(Color letterBackgroundColor);
}
