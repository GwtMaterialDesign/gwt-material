package gwt.material.design.client.base.mixin;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasRegex;
import gwt.material.design.client.base.helper.EventHelper;
import gwt.material.design.client.events.PasteEvent;
import gwt.material.design.client.ui.MaterialValueBox;

public class RegexMixin<T extends MaterialValueBox> extends AbstractMixin<T> implements HasRegex {

    protected String regex;
    protected HandlerRegistration keyPressHandler, pasteHandler;

    public RegexMixin(final T uiObject) {
        super(uiObject);

        EventHelper.onAttachOnce(uiObject, attachEvent -> setup());
    }

    protected void setup() {
        if (regex != null && !regex.isEmpty()) {
            keyPressHandler = uiObject.addKeyPressHandler(this::matchRegexOnKeyPress);
            pasteHandler = uiObject.addPasteHandler(this::matchRegexOnPaste);
        } else {
            if (keyPressHandler != null) keyPressHandler.removeHandler();
            if (pasteHandler != null) pasteHandler.removeHandler();
        }
    }

    protected void matchRegexOnKeyPress(KeyPressEvent event) {
        if (!String.valueOf(event.getCharCode()).matches(regex)) {
            uiObject.getValueBoxBase().cancelKey();
        }
    }

    protected void matchRegexOnPaste(PasteEvent event) {
        if (!event.getValue().matches(regex)) {
            Scheduler.get().scheduleDeferred(() -> uiObject.clear());
        }
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
