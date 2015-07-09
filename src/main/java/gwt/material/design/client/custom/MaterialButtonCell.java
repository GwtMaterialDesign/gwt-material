package gwt.material.design.client.custom;

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

import static com.google.gwt.dom.client.BrowserEvents.CLICK;
import static com.google.gwt.dom.client.BrowserEvents.KEYDOWN;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialLabel;

import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;

/**
 * A {@link Cell} used to render a button.
 */
public class MaterialButtonCell extends AbstractSafeHtmlCell<String> {

    private MaterialButton mb;

    /**
     * Construct a new ButtonCell that will use a {@link SimpleSafeHtmlRenderer}.
     */
    public MaterialButtonCell() {
        this(SimpleSafeHtmlRenderer.getInstance());
        mb = new MaterialButton();
    }

    /**
     * Construct a new ButtonCell that will use a given {@link SafeHtmlRenderer}.
     * 
     * @param renderer
     *            a {@link SafeHtmlRenderer SafeHtmlRenderer<String>} instance
     */
    public MaterialButtonCell(SafeHtmlRenderer<String> renderer) {
        super(renderer, CLICK, KEYDOWN);
    }

    public MaterialButtonCell(MaterialButton mb, MaterialLabel ml) {
        this();
        this.mb = mb;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if (CLICK.equals(event.getType())) {
            EventTarget eventTarget = event.getEventTarget();
            if (!Element.is(eventTarget)) {
                return;
            }
            if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {
                // Ignore clicks that occur outside of the main element.
                onEnterKeyDown(context, parent, value, event, valueUpdater);
            }
        }
    }

    @Override
    public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
        sb.appendHtmlConstant("<button type=\"button\" tabindex=\"-1\" class=\"");
        sb.append(SafeHtmlUtils.fromSafeConstant(mb.getWidget().getStyleName()));
        sb.appendHtmlConstant("\" style=\"display:inline-flex\">");
        sb.append(SafeHtmlUtils.fromSafeConstant("<i class=\"" + mb.getIcon() + " " + mb.getIconPosition() + "\"></i>"));
        if (data != null) {
            MaterialLabel ml = new MaterialLabel(data.asString());
            sb.appendHtmlConstant(ml.getElement().toString());
        }
        sb.appendHtmlConstant("</button>");
    }

    @Override
    protected void onEnterKeyDown(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
        if (valueUpdater != null) {
            valueUpdater.update(value);
        }
    }

}
