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
package gwt.material.design.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.base.mixin.ReadOnlyMixin;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.js.JsDatePickerOptions;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.js.Window;
import gwt.material.design.client.ui.html.DateInput;
import gwt.material.design.client.ui.html.Label;

import java.util.Date;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * Material Date Picker will provide a visual calendar to your apps.
 * <p/>
 * <h3>UiBinder Usage:</h3>
 * {@code
 * <m:MaterialDatePicker ui:field="datePicker">
 * }
 * <h3>Java Usage:</h3>
 * {@code
 * datePicker.setDate(new Date());
 * }
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#pickers">Material Date Picker</a>
 * @see <a href="https://material.io/guidelines/components/pickers.html#pickers-date-pickers">Material Design Specification</a>
 */
//@formatter:on
public class MaterialDatePicker extends AbstractValueWidget<Date> implements HasOrientation, HasPlaceholder,
        HasOpenHandlers<MaterialDatePicker>, HasCloseHandlers<MaterialDatePicker>, HasIcon, HasReadOnly {

    /**
     * Enum for identifying various selection types for the picker.
     */
    public enum MaterialDatePickerType {
        DAY,
        MONTH_DAY,
        YEAR_MONTH_DAY,
        YEAR
    }

    private String placeholder;
    private Date date;
    private Date dateMin;
    private Date dateMax;
    private Date dateTemp;
    private String format = "dd mmmm yyyy";
    private DateInput dateInput;
    private Label label = new Label();
    private MaterialLabel placeholderLabel = new MaterialLabel();
    protected Element pickatizedDateInput;
    private MaterialLabel errorLabel = new MaterialLabel();
    private DatePickerLanguage language;
    private JsDatePickerOptions options;
    private Orientation orientation;

    private MaterialDatePickerType selectionType = MaterialDatePickerType.DAY;

    private boolean detectOrientation;
    private boolean suppressChangeEvent;

    protected HandlerRegistration autoCloseHandler;
    protected HandlerRegistration orientationHandler;
    private MaterialIcon icon = new MaterialIcon();

    private ErrorMixin<AbstractValueWidget, MaterialLabel> errorMixin = new ErrorMixin<>(this, errorLabel, dateInput, placeholderLabel);
    private ReadOnlyMixin<MaterialDatePicker, DateInput> readOnlyMixin;

    private int yearsToDisplay = 10;
    private DatePickerContainer container = DatePickerContainer.SELF;
    private String tabIndex = "0";

    public MaterialDatePicker() {
        super(Document.get().createDivElement(), CssName.INPUT_FIELD);

        build();
    }

    public MaterialDatePicker(String placeholder) {
        this();
        setPlaceholder(placeholder);
    }

    public MaterialDatePicker(String placeholder, Date value) {
        this(placeholder);
        setDate(value);
    }

    public MaterialDatePicker(String placeholder, Date value, MaterialDatePickerType selectionType) {
        this(placeholder, value);
        setSelectionType(selectionType);
    }

    @Override
    protected void build() {
        dateInput = new DateInput();
        add(dateInput);

        label.add(placeholderLabel);
        add(label);
        add(errorLabel);
    }

    @Override
    public void onUnload() {
        super.onUnload();

        dateTemp = getValue();
    }

    @Override
    protected void initialize() {
        if (options == null) {
            options = new JsDatePickerOptions();
        }

        options.format = getFormat();
        switch (getSelectionType()) {
            case MONTH_DAY:
                options.selectMonths = true;
                break;
            case YEAR_MONTH_DAY:
                options.selectYears = yearsToDisplay;
                options.selectMonths = true;
                break;
            case YEAR:
                options.selectYears = yearsToDisplay;
                break;
        }

        if (container == DatePickerContainer.BODY) {
            options.container = "body";
        }

        pickatizedDateInput = $(dateInput.getElement()).pickadate(options).asElement();
        label.getElement().setAttribute("for", getPickerId());

        if (options.set == null) {
            options.set = thing -> {
                if (thing.hasOwnProperty("clear")) {
                    clear();
                } else if (thing.hasOwnProperty("select")) {
                    select();
                }
            };
        }

        getPicker()
                .off("open").off("close").off(options)
                .on(options).on("open", (e, param1) -> {
            onOpen();
            return true;
        }).on("close", (e, param1) -> {
            onClose();
            $(pickatizedDateInput).blur();
            return true;
        });

        setPopupEnabled(isEnabled());

        setInitialize(true);

        // Set up date specific settings.
        // These values require initialization.
        setDate(date);
        setDateMin(dateMin);
        setDateMax(dateMax);
        setOrientation(orientation);
    }

    private void setPopupEnabled(boolean enabled) {
        if (getPicker() != null) {
            if (!enabled) {
                $(getPickerRootElement()).attr("tabindex", "-1");
            } else {
                $(getPickerRootElement()).attr("tabindex", tabIndex);
            }
        }
    }

    /**
     * Reinitialize the datepicker.
     */
    @Override
    public void reinitialize() {
        Scheduler.get().scheduleDeferred(() -> {
            initialize();
            if (pickatizedDateInput != null && dateTemp != null) {
                getPicker().set("select", dateTemp, () -> {
                    DOM.createFieldSet().setPropertyObject("muted", true);
                });
            }
        });
    }

    @Override
    public void clear() {
        clearErrorOrSuccess();
        label.removeStyleName(CssName.ACTIVE);
        dateInput.removeStyleName(CssName.VALID);
        dateInput.clear();
    }

    /**
     * Sets the type of selection options (date, month, year,...).
     *
     * @param type if <code>null</code>, {@link MaterialDatePickerType#DAY} will be used as fallback.
     */
    public void setDateSelectionType(MaterialDatePickerType type) {
        if (type != null) {
            this.selectionType = type;
        }
    }

    protected void onClose() {
        CloseEvent.fire(this, this);
        fireEvent(new BlurEvent() {
        });
    }

    protected void onOpen() {
        label.addStyleName(CssName.ACTIVE);
        dateInput.setFocus(true);
        OpenEvent.fire(this, this);
        fireEvent(new FocusEvent() {
        });
    }

    /**
     * Programmatically close the date picker component
     */
    public void close() {
        Scheduler.get().scheduleDeferred(() -> {
            getPicker().close();
        });
    }

    /**
     * Programmatically open the date picker component
     */
    public void open() {
        Scheduler.get().scheduleDeferred(() -> {
            getPicker().open();
        });
    }

    public boolean isOpen() {
        return Boolean.parseBoolean(getPicker().get("open").toString());
    }

    protected void select() {
        label.addStyleName(CssName.ACTIVE);
        dateInput.addStyleName(CssName.VALID);

        // Ensure the value change event is
        // triggered on selecting a date if the picker is open
        // to avoid conflicts on setValue(value, fireEvents).
        if (isOpen() && !suppressChangeEvent) {
            ValueChangeEvent.fire(this, getValue());
        }
    }

    public String getPickerId() {
        return getPicker().get("id").toString();
    }

    public Element getPickerRootElement() {
        return $("#" + getPickerId() + "_root").asElement();
    }

    /**
     * Sets the current date of the picker.
     *
     * @param date - must not be <code>null</code>
     */
    public void setDate(Date date) {
        setValue(date);
    }

    /**
     * Get the minimum date limit.
     */
    public Date getDateMin() {
        return dateMin;
    }

    /**
     * Set the minimum date limit.
     */
    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;

        if (isInitialize() && dateMin != null) {
            getPicker().set("min", JsDate.create((double) dateMin.getTime()));
        }
    }

    /**
     * Get the maximum date limit.
     */
    public Date getDateMax() {
        return dateMax;
    }

    /**
     * Set the maximum date limit.
     */
    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;

        if (isInitialize() && dateMax != null) {
            getPicker().set("max", JsDate.create((double) dateMax.getTime()));
        }
    }

    /**
     * Set the pickers date.
     */
    public void setPickerDate(JsDate date, Element picker) {
        try {
            $(picker).pickadate("picker").set("select", date, () -> {
                DOM.createFieldSet().setPropertyObject("muted", true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the pickers date.
     */
    protected Date getPickerDate() {
        try {
            JsDate pickerDate = getPicker().get("select").obj;
            return new Date((long) pickerDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected JsMaterialElement getPicker() {
        return $(pickatizedDateInput).pickadate("picker");
    }

    public Date getDate() {
        return getPickerDate();
    }

    /**
     * Clears the values of the picker field.
     */
    public void clearValues() {
        if (pickatizedDateInput != null) {
            clearValues(pickatizedDateInput);
        }
    }

    public void clearValues(Element picker) {
        $(picker).pickadate("picker").clear();
    }

    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;

        if (placeholder != null) {
            placeholderLabel.setText(placeholder);
        }
    }

    /**
     * Get the pickers selection type.
     */
    public MaterialDatePickerType getSelectionType() {
        return selectionType;
    }

    /**
     * Set the pickers selection type.
     */
    public void setSelectionType(MaterialDatePickerType selectionType) {
        this.selectionType = selectionType;
    }

    /**
     * Set the pickers selection type with the ability to set the number of years to display
     * in the dropdown list.
     */
    public void setSelectionType(MaterialDatePickerType selectionType, int yearsToDisplay) {
        this.selectionType = selectionType;
        this.yearsToDisplay = yearsToDisplay;
    }

    /**
     * @return the orientation
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set : can be Vertical or Horizontal
     */
    @Override
    public void setOrientation(Orientation orientation) {
        JsMaterialElement element = getPicker();
        if (isInitialize() && this.orientation != null) {
            element.root.removeClass(this.orientation.getCssName());
        }
        this.orientation = orientation;
        if (isInitialize() && orientation != null) {
            element.root.addClass(orientation.getCssName());
        }
    }

    public void setDetectOrientation(boolean detectOrientation) {
        this.detectOrientation = detectOrientation;

        if (orientationHandler != null) {
            orientationHandler.removeHandler();
            orientationHandler = null;
        }

        if (detectOrientation) {
            orientationHandler = com.google.gwt.user.client.Window.addResizeHandler(resizeEvent -> {
                detectAndApplyOrientation();
            });
            detectAndApplyOrientation();
        }
    }

    public boolean isDetectOrientation() {
        return detectOrientation;
    }

    protected void detectAndApplyOrientation() {
        if (Window.matchMedia("(orientation: portrait)")) {
            setOrientation(Orientation.PORTRAIT);
        } else {
            setOrientation(Orientation.LANDSCAPE);
        }
    }

    @Override
    public void setError(String error) {
        super.setError(error);
        dateInput.addStyleName(CssName.INVALID);
        dateInput.removeStyleName(CssName.VALID);
    }

    @Override
    public void setSuccess(String success) {
        super.setSuccess(success);
        dateInput.addStyleName(CssName.VALID);
        dateInput.removeStyleName(CssName.INVALID);
    }

    @Override
    public void clearErrorOrSuccess() {
        super.clearErrorOrSuccess();
        dateInput.removeStyleName(CssName.VALID);
        dateInput.removeStyleName(CssName.INVALID);
    }

    public String getFormat() {
        return format;
    }

    /**
     * To call before initialization.
     */
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public Date getValue() {
        return getPickerDate();
    }

    @Override
    public void setValue(Date value, boolean fireEvents) {
        if (value == null) {
            clearValues();
            return;
        }
        this.date = value;
        if (isInitialize()) {
            suppressChangeEvent = !fireEvents;
            setPickerDate(JsDate.create((double) value.getTime()), pickatizedDateInput);
            suppressChangeEvent = false;
            label.addStyleName(CssName.ACTIVE);
        }
        super.setValue(value, fireEvents);
    }

    @Override
    public void setValue(Date value) {
        setValue(value, false);
    }

    @Override
    public HandlerRegistration addCloseHandler(final CloseHandler<MaterialDatePicker> handler) {
        return addHandler(handler, CloseEvent.getType());
    }

    @Override
    public HandlerRegistration addOpenHandler(final OpenHandler<MaterialDatePicker> handler) {
        return addHandler(handler, OpenEvent.getType());
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        dateInput.setEnabled(enabled);
        if (isInitialize()) {
            setPopupEnabled(enabled);
        }
    }

    @Override
    public boolean isEnabled() {
        return dateInput.isEnabled();
    }

    @Override
    public void setTabIndex(int index) {
        tabIndex = String.valueOf(index);
        dateInput.setTabIndex(index);
    }

    @Override
    public int getTabIndex() {
        return dateInput.getTabIndex();
    }

    public DatePickerLanguage getLanguage() {
        return language;
    }

    public void setLanguage(DatePickerLanguage language) {
        this.language = language;

        if (language.getJs() != null) {
            ScriptInjector.fromString(language.getJs().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
        }
        stop();
        reinitialize();
    }

    /**
     * Stop the datepicker instance.
     */
    public void stop() {
        if (pickatizedDateInput != null) {
            getPicker().stop();
        }
    }

    protected void start() {
        getPicker().start();
    }

    @Override
    public MaterialIcon getIcon() {
        return icon;
    }

    @Override
    public void setIconType(IconType iconType) {
        icon.setIconType(iconType);
        icon.setIconPrefix(true);
        errorLabel.setPaddingLeft(44);
        insert(icon, 0);
    }

    @Override
    public void setIconPosition(IconPosition position) {
        icon.setIconPosition(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        icon.setIconSize(size);
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        icon.setIconFontSize(size, unit);
    }

    @Override
    public void setIconColor(Color iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        icon.setIconPrefix(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return icon.isIconPrefix();
    }

    @Override
    protected ErrorMixin<AbstractValueWidget, MaterialLabel> getErrorMixin() {
        return errorMixin;
    }

    public ReadOnlyMixin<MaterialDatePicker, DateInput> getReadOnlyMixin() {
        if (readOnlyMixin == null) {
            readOnlyMixin = new ReadOnlyMixin<>(this, dateInput);
        }
        return readOnlyMixin;
    }

    @Override
    public void setReadOnly(boolean value) {
        getReadOnlyMixin().setReadOnly(value);
    }

    @Override
    public boolean isReadOnly() {
        return getReadOnlyMixin().isReadOnly();
    }

    @Override
    public void setToggleReadOnly(boolean toggle) {
        getReadOnlyMixin().setToggleReadOnly(toggle);
    }

    @Override
    public boolean isToggleReadOnly() {
        return getReadOnlyMixin().isToggleReadOnly();
    }

    public DateInput getDateInput() {
        return dateInput;
    }

    public boolean isAutoClose() {
        return autoCloseHandler != null;
    }

    /**
     * Enables or disables auto closing when selecting a date.
     */
    public void setAutoClose(boolean autoClose) {
        if (autoCloseHandler != null) {
            autoCloseHandler.removeHandler();
            autoCloseHandler = null;
        }

        if (autoClose) {
            autoCloseHandler = addValueChangeHandler(event -> close());
        }
    }

    public int getYearsToDisplay() {
        return yearsToDisplay;
    }

    /**
     * Ability to set the number of years to display
     * in the dropdown list.
     */
    public void setYearsToDisplay(int yearsToDisplay) {
        this.yearsToDisplay = yearsToDisplay;
    }

    public DatePickerContainer getContainer() {
        return container;
    }

    /**
     * Set the Root Picker Container (Default : SELF)
     */
    public void setContainer(DatePickerContainer container) {
        this.container = container;
    }

    public Label getLabel() {
        return label;
    }

    public MaterialLabel getPlaceholderLabel() {
        return placeholderLabel;
    }

    public MaterialLabel getErrorLabel() {
        return errorLabel;
    }
}