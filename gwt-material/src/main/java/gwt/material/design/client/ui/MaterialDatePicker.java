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
package gwt.material.design.client.ui;

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
import gwt.material.design.client.base.helper.DateFormatHelper;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.base.mixin.FieldTypeMixin;
import gwt.material.design.client.base.mixin.ReadOnlyMixin;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.js.JsDatePickerOptions;
import gwt.material.design.client.js.JsMaterialElement;
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
public class MaterialDatePicker extends AbstractValueWidget<Date> implements JsLoader, HasPlaceholder,
        HasOpenHandlers<MaterialDatePicker>, HasCloseHandlers<MaterialDatePicker>, HasIcon, HasReadOnly,
        HasFieldTypes {

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
    private String tabIndex = "0";
    private Date date;
    private Date dateMin;
    private Date dateMax;
    private DatePickerLanguage language;
    private Orientation orientation;
    private DatePickerContainer container = DatePickerContainer.SELF;
    private MaterialDatePickerType selectionType = MaterialDatePickerType.DAY;
    private int yearsToDisplay = 10;
    private boolean autoClose;
    private boolean suppressChangeEvent;
    protected Element pickatizedDateInput;
    private DateInput dateInput = new DateInput();
    private Label label = new Label();
    private MaterialLabel placeholderLabel = new MaterialLabel();
    private MaterialLabel errorLabel = new MaterialLabel();
    private MaterialIcon icon = new MaterialIcon();

    private JsDatePickerOptions options = new JsDatePickerOptions();
    private HandlerRegistration autoCloseHandlerRegistration, attachHandler;

    private ErrorMixin<AbstractValueWidget, MaterialLabel> errorMixin;
    private ReadOnlyMixin<MaterialDatePicker, DateInput> readOnlyMixin;
    private FieldTypeMixin<MaterialDatePicker> fieldTypeMixin;

    public MaterialDatePicker() {
        super(Document.get().createDivElement(), CssName.INPUT_FIELD);

        add(dateInput);
        label.add(placeholderLabel);
        add(label);
        add(errorLabel);
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
    protected void onLoad() {
        super.onLoad();

        load();
    }

    @Override
    public void load() {
        pickatizedDateInput = $(dateInput.getElement()).pickadate(options).asElement();

        getPicker().on("set", thing -> {
            if (thing.hasOwnProperty("clear")) {
                clear();
            } else if (thing.hasOwnProperty("select")) {
                select();
            }
        });

        getPicker().on(options).on("open", (e, param1) -> {
            onOpen();
            return true;
        }).on("close", (e, param1) -> {
            onClose();
            $(pickatizedDateInput).blur();
            return true;
        });

        label.getElement().setAttribute("for", getPickerId());
        setPopupEnabled(isEnabled());
        setAutoClose(autoClose);
        setDate(date);
        setDateMin(dateMin);
        setDateMax(dateMax);
    }

    @Override
    public void onUnload() {
        super.onUnload();

        unload();
    }

    @Override
    public void unload() {
        JsMaterialElement picker = getPicker();
        if (picker != null) {
            picker.off("set");
            picker.off("open");
            picker.off("close");
        }
    }

    @Override
    public void reload() {
        unload();
        load();
    }

    /**
     * As of now use {@link MaterialDatePicker#setSelectionType(MaterialDatePickerType)}
     *
     * @param type
     */
    @Deprecated
    public void setDateSelectionType(MaterialDatePickerType type) {
        if (type != null) {
            this.selectionType = type;
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

        if (isAttached() && dateMin != null) {
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

        if (isAttached() && dateMax != null) {
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
        switch (selectionType) {
            case MONTH_DAY:
                options.selectMonths = true;
                break;
            case YEAR_MONTH_DAY:
                options.selectYears = yearsToDisplay;
                options.selectMonths = true;
                break;
            case YEAR:
                options.selectYears = yearsToDisplay;
                options.selectMonths = false;
                break;
        }
    }

    /**
     * Set the pickers selection type with the ability to set the number of years to display
     * in the dropdown list.
     */
    public void setSelectionType(MaterialDatePickerType selectionType, int yearsToDisplay) {
        setSelectionType(selectionType);
        setYearsToDisplay(yearsToDisplay);
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;

        JsMaterialElement picker = getPicker();
        if (picker != null && orientation != null) {
            picker.root.removeClass(orientation.getCssName());
        }
        if (picker != null && orientation != null) {
            picker.root.addClass(orientation.getCssName());
        }
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
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
        removeErrorModifiers();
    }

    @Override
    public void setHelperText(String helperText) {
        super.setHelperText(helperText);
        removeErrorModifiers();
    }

    protected void removeErrorModifiers() {
        dateInput.removeStyleName(CssName.VALID);
        dateInput.removeStyleName(CssName.INVALID);
    }

    public String getFormat() {
        return options.format;
    }

    /**
     * To call before initialization.
     */
    public void setFormat(String format) {
        options.format = DateFormatHelper.format(format);
    }

    @Override
    public Date getValue() {
        if (isAttached()) {
            return getPickerDate();
        }
        else {
            return this.date;
        }
    }

    @Override
    public void setValue(Date value, boolean fireEvents) {
        this.date = value;
        if (value == null) {
            clear();
            return;
        }
        if (isAttached()) {
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
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        dateInput.setEnabled(enabled);
        if (isAttached()) {
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

        if (attachHandler != null) {
            attachHandler.removeHandler();
            attachHandler = null;
        }

        if (isAttached()) {
            setupLanguage(language);
        } else {
            attachHandler = registerHandler(addAttachHandler(attachEvent -> setupLanguage(language)));
        }
    }

    protected void setupLanguage(DatePickerLanguage language) {
        if (language.getJs() != null) {
            ScriptInjector.fromString(language.getJs().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
            getPicker().stop();
            Scheduler.get().scheduleDeferred(() -> load());
        }
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
    public Color getIconColor() {
        return icon.getIconColor();
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
        return autoClose;
    }

    /**
     * Enables or disables auto closing when selecting a date.
     */
    public void setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;

        if (autoCloseHandlerRegistration != null) {
            autoCloseHandlerRegistration.removeHandler();
            autoCloseHandlerRegistration = null;
        }

        if (autoClose) {
            autoCloseHandlerRegistration = registerHandler(addValueChangeHandler(event -> close()));
        }
    }

    public int getYearsToDisplay() {
        return options.selectYears;
    }

    /**
     * Ability to set the number of years to display
     * in the dropdown list.
     */
    public void setYearsToDisplay(int yearsToDisplay) {
        options.selectYears = yearsToDisplay;
    }

    public DatePickerContainer getContainer() {
        return container;
    }

    /**
     * Set the Root Picker Container (Default : SELF)
     */
    public void setContainer(DatePickerContainer container) {
        this.container = container;
        options.container = container == DatePickerContainer.SELF ? getElement().getId() : container.getCssName();
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

    /**
     * Programmatically close the date picker component
     */
    public void close() {
        Scheduler.get().scheduleDeferred(() -> getPicker().close());
    }

    /**
     * Programmatically open the date picker component
     */
    public void open() {
        Scheduler.get().scheduleDeferred(() -> getPicker().open());
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

    protected void onClose() {
        CloseEvent.fire(this, this);
        fireEvent(new BlurEvent() {});
    }

    protected void onOpen() {
        label.addStyleName(CssName.ACTIVE);
        dateInput.setFocus(true);
        OpenEvent.fire(this, this);
        fireEvent(new FocusEvent() {});
    }

    @Override
    public void reset() {
        super.reset();

        clear();
    }

    /**
     * Replace by {@link MaterialDatePicker#unload()}
     */
    @Deprecated
    public void stop() {
        unload();
    }

    @Override
    public void clear() {
        this.date = null;
        dateInput.clear();
        if (getPicker() != null) {
            getPicker().set("select", null);
        }
        // Clear all active / error styles on datepicker
        clearErrorOrSuccess();
        label.removeStyleName(CssName.ACTIVE);
        dateInput.removeStyleName(CssName.VALID);

    }

    protected void setPopupEnabled(boolean enabled) {
        if (getPicker() != null) {
            if (!enabled) {
                $(getPickerRootElement()).attr("tabindex", "-1");
            } else {
                $(getPickerRootElement()).attr("tabindex", tabIndex);
            }
        }
    }

    @Override
    public void setFieldType(FieldType type) {
        getFieldTypeMixin().setFieldType(type);
    }

    @Override
    public FieldType getFieldType() {
        return getFieldTypeMixin().getFieldType();
    }

    @Override
    public void setLabelWidth(double percentWidth) {
        getFieldTypeMixin().setLabelWidth(percentWidth);
    }

    @Override
    public void setFieldWidth(double percentWidth) {
        getFieldTypeMixin().setFieldWidth(percentWidth);
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
    protected ErrorMixin<AbstractValueWidget, MaterialLabel> getErrorMixin() {
        if (errorMixin == null) {
            errorMixin = new ErrorMixin<>(this, errorLabel, dateInput, placeholderLabel);
        }
        return errorMixin;
    }

    protected ReadOnlyMixin<MaterialDatePicker, DateInput> getReadOnlyMixin() {
        if (readOnlyMixin == null) {
            readOnlyMixin = new ReadOnlyMixin<>(this, dateInput);
        }
        return readOnlyMixin;
    }

    protected FieldTypeMixin<MaterialDatePicker> getFieldTypeMixin() {
        if (fieldTypeMixin == null) {
            fieldTypeMixin = new FieldTypeMixin<>(this);
        }
        return fieldTypeMixin;
    }
}