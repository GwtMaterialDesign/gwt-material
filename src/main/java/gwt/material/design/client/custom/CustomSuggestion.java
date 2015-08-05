package gwt.material.design.client.custom;

import com.google.gwt.user.client.ui.MultiWordSuggestOracle.MultiWordSuggestion;

public class CustomSuggestion extends MultiWordSuggestion{
	private String suggestion;
	private String display;

    public CustomSuggestion(){}

    public CustomSuggestion(String display, String suggestion){
    	suggestion.replace("", "");
        this.display = new String(display);
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
}