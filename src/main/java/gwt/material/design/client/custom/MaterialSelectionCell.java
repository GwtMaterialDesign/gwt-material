/**
 * 
 */
package gwt.material.design.client.custom;

import static com.google.gwt.dom.client.BrowserEvents.CLICK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.cell.client.AbstractInputCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.Label;

import gwt.material.design.client.ui.MaterialDropDown;
/**
 * 
 * @author stefanwasserbauer
 * TODO: I18N of display names (key / value pairs)
 */
public class MaterialSelectionCell extends AbstractInputCell<String, String> {

	private static final String CLICKID_SELECTED = "clickid-selected";
	private MaterialDropDown template;
	private ArrayList<String> options;
	private HashMap<String, Integer> indexForOption = new HashMap<String, Integer>();
	private boolean isExpanded;
	private HandlerRegistration nativePreviewHandler;
	private UListElement lastExpandedList;

	public MaterialSelectionCell(List<String> options) {
		super(BrowserEvents.CHANGE, BrowserEvents.KEYDOWN, BrowserEvents.CLICK);
		if (template == null) {
			template = new MaterialDropDown();
			template.setType("flat");
		}
		this.options = new ArrayList<String>(options);
		int index = 0;
		for (String option : options) {
			indexForOption.put(option, index);
			setViewData(index, option);
			index++;
		}
		template.setText(options.get(0));
		
		nativePreviewHandler = Event.addNativePreviewHandler(new NativePreviewHandler() {
			
			@Override
			public void onPreviewNativeEvent(NativePreviewEvent event) {
				String type = event.getNativeEvent().getType();
				if(CLICK.equals(type) && isExpanded) {
					closeContent();
				}
			}
		});
	}
	
	public void clearHandler() {
		if(this.nativePreviewHandler != null) {
			this.nativePreviewHandler.removeHandler();
		}
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event,
			ValueUpdater<String> valueUpdater) {
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		if (CLICK.equals(event.getType())) {
			doClick(context, parent, value, event, valueUpdater);
		} 
	}
	
	private void doClick(Context context, Element parent, String value, NativeEvent event,
			ValueUpdater<String> valueUpdater) {
		EventTarget eventTarget = event.getEventTarget();
		if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {
			
			//This is necessary to show content of dropdown as an overlay over the next row
			parent.getParentElement().getStyle().setOpacity(1.0);
			
			if (isListEntry(eventTarget)) {
				onEntryClicked(Element.as(eventTarget), parent, valueUpdater, context);
			} else {
				
				if(value == null || value.isEmpty()) {
					value = this.template.getText();
				}
				Integer key = getSelectedIndex(value);
				
				openContent(parent);

				String newValue = options.get(key);
				finishEditing(parent, newValue, key, valueUpdater);
			}

		} else {
			closeContent();
		}
	}

	private boolean isListEntry(EventTarget eventTarget) {
		return Element.as(eventTarget).hasTagName("LI") || Element.as(eventTarget).hasAttribute(CLICKID_SELECTED);
	}

	private void onEntryClicked(Element element, Element parent, ValueUpdater<String> valueUpdater, com.google.gwt.cell.client.Cell.Context context) {
		String innerText = element.getInnerText();
		template.setText(innerText);
		closeContent();
		if (valueUpdater != null) {
			valueUpdater.update(innerText);
		}
		setValue(context, parent, innerText);
	}

	private void openContent(Element parent) {
		UListElement list = parent.getFirstChild().getChild(2).cast();
		list.getStyle().setOpacity(1.0);
		list.getStyle().setProperty("height", "200px !important");
		list.getStyle().setDisplay(Display.BLOCK);
		isExpanded = true;
		lastExpandedList = list;
	}
	
	private void closeContent() {
		UListElement list = this.lastExpandedList;
		if(list != null && Element.is(list)) {
			list.getStyle().setOpacity(0.0);
			list.getStyle().setProperty("height", "200px !important");
			list.getStyle().setDisplay(Display.NONE);
			this.isExpanded = false;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, String value, SafeHtmlBuilder sb) {
		Integer key = getSelectedIndex(value);

		int index = 0;
		template.clearEntryWidgets();
		for (String option : options) {
			if (index++ == key) {
				// selected
				template.setText(option);
				Label label = new Label(option);
				label.getElement().setAttribute(CLICKID_SELECTED, "1");
				template.addWidget(label);
			} else {
				// deselected
				Label label = new Label(option);
				label.getElement().setAttribute(CLICKID_SELECTED, "0");
				template.addWidget(label);
			}
		}
		sb.appendHtmlConstant(DOM.toString(template.getElement()));
	}

	private int getSelectedIndex(String value) {
		Integer index = indexForOption.get(value);
		if (index == null) {
			return -1;
		}
		return index.intValue();
	}

}
