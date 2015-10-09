package gwt.material.design.client.custom;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

public class CustomSpan extends ComplexWidget implements HasClickHandlers, HasText{
	private String text;

	public CustomSpan() {
		setElement(Document.get().createElement("span"));
	}

	public CustomSpan(String string) {
		this();
		getElement().setInnerHTML(string);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
		getElement().setInnerHTML(text);
	}
}
