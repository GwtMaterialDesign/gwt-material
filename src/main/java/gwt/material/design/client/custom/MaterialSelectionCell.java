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
 * @author stefanwasserbauer TODO: I18N of display names (key / value pairs).
 *         Currently solved via indices of "options" list which contain human
 *         readable strings but their index corresponds to selected index for
 *         value updater.
 */
public class MaterialSelectionCell extends AbstractInputCell<Integer, String> {

	private static final String CLICKID_SELECTED = "clickid-selected";
	private MaterialDropDown template;
	private ArrayList<String> options;
	private HashMap<String, Integer> indexForOption = new HashMap<String, Integer>();
	private boolean isExpanded;
	private HandlerRegistration nativePreviewHandler;
	private UListElement lastExpandedList;

	/**
	 * 
	 * @param options - human readable strings (i18n). Indices of list are used for field updater (key).
	 */
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
				if (CLICK.equals(type) && isExpanded) {
					closeContent();
				}
			}
		});
	}

	public void clearHandler() {
		if (this.nativePreviewHandler != null) {
			this.nativePreviewHandler.removeHandler();
		}
	}

	@Override
	public void onBrowserEvent(Context context, Element parent, Integer value, NativeEvent event,
			ValueUpdater<Integer> valueUpdater) {
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		if (CLICK.equals(event.getType())) {
			doClick(context, parent, value, event, valueUpdater);
		}
	}

	private void doClick(Context context, Element parent, Integer value, NativeEvent event,
			ValueUpdater<Integer> valueUpdater) {
		EventTarget eventTarget = event.getEventTarget();
		if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {

			// This is necessary to show content of dropdown as an overlay over
			// the next row
			parent.getParentElement().getStyle().setOpacity(1.0);

			if (isListEntry(eventTarget)) {
				onEntryClicked(Element.as(eventTarget), parent, valueUpdater, context);
			} else {
				openContent(parent);
			}

		} else {
			closeContent();
		}
	}

	private boolean isListEntry(EventTarget eventTarget) {
		return Element.as(eventTarget).hasTagName("LI") || Element.as(eventTarget).hasAttribute(CLICKID_SELECTED);
	}

	private void onEntryClicked(Element element, Element parent, ValueUpdater<Integer> valueUpdater,
			com.google.gwt.cell.client.Cell.Context context) {
		String innerText = element.getInnerText();
		Integer selectedIndex = indexForOption.get(innerText);
		template.setText(innerText);
		closeContent();
		if (valueUpdater != null) {
			valueUpdater.update(selectedIndex);
		}
		setValue(context, parent, selectedIndex);
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
		if (list != null && Element.is(list)) {
			list.getStyle().setOpacity(0.0);
			list.getStyle().setProperty("height", "200px !important");
			list.getStyle().setDisplay(Display.NONE);
			this.isExpanded = false;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, Integer value, SafeHtmlBuilder sb) {

		int index = 0;
		template.clearEntryWidgets();
		for (String option : options) {
			if (index++ == value) {
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

}
