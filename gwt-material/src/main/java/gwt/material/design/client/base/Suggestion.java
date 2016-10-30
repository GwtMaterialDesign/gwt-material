package gwt.material.design.client.base;

/*
 * #%L GwtMaterial %% Copyright (C) 2015 GwtMaterialDesign %% Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. #L%
 */

import com.google.gwt.user.client.ui.MultiWordSuggestOracle.MultiWordSuggestion;

public class Suggestion extends MultiWordSuggestion {
    private String suggestion;
    private String display;

    public Suggestion() {
    }

    public Suggestion(String display, String suggestion) {
        this.display = display;
        this.setSuggestion(suggestion);
    }

    @Override
    public String getDisplayString() {
        return (display);
    }

    @Override
    public String getReplacementString() {
        return display;
    }

    /**
     * @return the display
     */
    public String getDisplay() {
        return display;
    }

    /**
     * @param display the display to set
     */
    public void setDisplay(String display) {
        this.display = display;
    }

    /**
     * @return the suggestion
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * @param suggestion the suggestion to set
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Suggestion) {
            Suggestion that = (Suggestion) obj;
            if (this.display != null && this.suggestion != null) {
                return this.display.equals(that.display) && this.suggestion.equals(that.suggestion);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return String.valueOf(display).hashCode() + String.valueOf(suggestion).hashCode();
    }
}