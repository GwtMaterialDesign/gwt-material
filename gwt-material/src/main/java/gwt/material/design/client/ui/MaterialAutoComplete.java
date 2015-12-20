package gwt.material.design.client.ui;

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

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.base.mixin.ProgressMixin;
import gwt.material.design.client.constants.AutocompleteType;
import gwt.material.design.client.constants.CssType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.ProgressType;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;

import java.util.*;
import java.util.Map.Entry;

// @formatter:off

/**
 * Use GWT Autocomplete to search for matches from local or remote data sources.
 * We used MultiWordSuggestOracle to populate the list to be added on the
 * autocomplete values.
 *
 * <h3>UiBinder Usage:</h3>
 *
 * <pre>
 * {@code
 *    <m:MaterialAutoComplete ui:field="autocomplete" placeholder="States" />}
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#autocompletes">Material
 *      AutoComplete</a>
 */
// @formatter:on
public class MaterialAutoComplete extends MaterialWidget implements HasError, HasPlaceholder,
        HasValue<List<? extends Suggestion>>, HasProgress, HasKeyUpHandlers, HasType<AutocompleteType> {

    private Map<Suggestion, MaterialChip> suggestionMap = new LinkedHashMap<>();

    private List<ListItem> itemsHighlighted = new ArrayList<>();
    private FlowPanel panel = new FlowPanel();
    private UnorderedList list = new UnorderedList();
    private SuggestOracle suggestions;
    private TextBox itemBox = new TextBox();
    private SuggestBox box;
    private int limit = 0;
    private MaterialLabel lblError = new MaterialLabel();
    private final ProgressMixin<MaterialAutoComplete> progressMixin = new ProgressMixin<>(this);

    private boolean directInputAllowed = true;
    private MaterialChipProvider chipProvider = new DefaultMaterialChipProvider();

    private final ErrorMixin<MaterialAutoComplete, MaterialLabel> errorMixin = new ErrorMixin<>(this,
            lblError, list);
    public final CssTypeMixin<AutocompleteType, MaterialAutoComplete> typeMixin = new CssTypeMixin<>(this);

    /**
     * Use MaterialAutocomplete to search for matches from local or remote data
     * sources.
     */
    public MaterialAutoComplete() {
        super(Document.get().createDivElement());
        add(panel);
    }

    public MaterialAutoComplete(AutocompleteType type){
        this();
        setType(type);
    }

    /**
     * Use MaterialAutocomplete to search for matches from local or remote data
     * sources.
     *
     * @see #setSuggestions(SuggestOracle)
     */
    public MaterialAutoComplete(SuggestOracle suggestions) {
        this();
        generateAutoComplete(suggestions);
    }

    /**
     * Generate and build the List Items to be set on Auto Complete box.
     */
    private void generateAutoComplete(SuggestOracle suggestions) {
        list.setStyleName("multiValueSuggestBox-list");
        this.suggestions = suggestions;
        final ListItem item = new ListItem();

        item.setStyleName("multiValueSuggestBox-input-token");
        box = new SuggestBox(suggestions, itemBox);
        setLimit(this.limit);
        String autocompleteId = DOM.createUniqueId();
        itemBox.getElement().setId(autocompleteId);

        item.add(box);
        list.add(item);

        list.addDomHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                box.showSuggestionList();
            }
        }, ClickEvent.getType());

        itemBox.addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                boolean itemsChanged = false;

                switch (event.getNativeKeyCode()) {
                    case KeyCodes.KEY_ENTER:
                        if (directInputAllowed) {
                            String value = itemBox.getValue();
                            if (value != null && !(value = value.trim()).isEmpty()) {
                                gwt.material.design.client.base.Suggestion directInput = new gwt.material.design.client.base.Suggestion();
                                directInput.setDisplay(value);
                                directInput.setSuggestion(value);
                                itemsChanged = addItem(directInput);
                                itemBox.setValue("");
                                itemBox.setFocus(true);
                            }
                        }
                        break;

                    case KeyCodes.KEY_BACKSPACE:
                        if (itemBox.getValue().trim().isEmpty()) {
                            if (itemsHighlighted.isEmpty()) {
                                if (suggestionMap.size() > 0) {

                                    ListItem li = (ListItem) list.getWidget(list.getWidgetCount() - 2);
                                    MaterialChip p = (MaterialChip) li.getWidget(0);

                                    Set<Entry<Suggestion, MaterialChip>> entrySet = suggestionMap.entrySet();
                                    for (Entry<Suggestion, MaterialChip> entry : entrySet) {
                                        if (p.equals(entry.getValue())) {
                                            suggestionMap.remove(entry.getKey());
                                            itemsChanged = true;
                                            break;
                                        }
                                    }

                                    list.remove(li);
                                }
                            }
                        }

                    case KeyCodes.KEY_DELETE:
                        if (itemBox.getValue().trim().isEmpty()) {
                            for (ListItem li : itemsHighlighted) {
                                li.removeFromParent();
                                MaterialChip p = (MaterialChip) li.getWidget(0);

                                Set<Entry<Suggestion, MaterialChip>> entrySet = suggestionMap.entrySet();
                                for (Entry<Suggestion, MaterialChip> entry : entrySet) {
                                    if (p.equals(entry.getValue())) {
                                        suggestionMap.remove(entry.getKey());
                                        itemsChanged = true;
                                        break;
                                    }
                                }
                            }
                            itemsHighlighted.clear();
                        }
                        itemBox.setFocus(true);
                        break;
                }

                if (itemsChanged) {
                    ValueChangeEvent.fire(MaterialAutoComplete.this, getValue());
                }
            }
        });

        itemBox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                box.showSuggestionList();
            }
        });

        box.addSelectionHandler(new SelectionHandler<Suggestion>() {
            public void onSelection(SelectionEvent<Suggestion> selectionEvent) {
                Suggestion selectedItem = selectionEvent.getSelectedItem();
                itemBox.setValue("");
                if (addItem(selectedItem)) {
                    ValueChangeEvent.fire(MaterialAutoComplete.this, getValue());
                }
                itemBox.setFocus(true);
            }
        });

        panel.add(list);
        panel.getElement().setAttribute("onclick",
                "document.getElementById('" + autocompleteId + "').focus()");
        panel.add(lblError);
        box.setFocus(true);
    }

    /**
     * Adding the item value using Material Chips added on auto complete box
     */
    protected boolean addItem(final Suggestion suggestion) {
        if (getLimit() > 0) {
            if (suggestionMap.size() >= getLimit()) {
                return false;
            }
        }

        if (suggestionMap.containsKey(suggestion)) {
            return false;
        }

        final MaterialChip chip = chipProvider.getChip(suggestion);
        if (chip == null) {
            return false;
        }

        final ListItem displayItem = new ListItem();
        displayItem.setStyleName("multiValueSuggestBox-token");

        chip.setIconType(IconType.CLOSE);
        chip.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                if (itemsHighlighted.contains(displayItem)) {
                    chip.removeStyleName("blue white-text");
                    itemsHighlighted.remove(displayItem);
                } else {
                    chip.addStyleName("blue white-text");
                    itemsHighlighted.add(displayItem);
                }
            }
        });

        chip.getIcon().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                suggestionMap.remove(suggestion);
                list.remove(displayItem);
                ValueChangeEvent.fire(MaterialAutoComplete.this, getValue());
                box.showSuggestionList();
            }
        });

        suggestionMap.put(suggestion, chip);
        if(getType() == AutocompleteType.TEXT) {
            suggestionMap.clear();
            itemBox.setText(suggestion.getDisplayString());
            displayItem.add(itemBox);
        }else{
            displayItem.add(chip);
        }
        list.insert(displayItem, list.getWidgetCount() - 1);
        return true;
    }

    /**
     * Clear the chip items on the autocomplete box
     */
    public void clear() {
        itemBox.setValue("");

        Collection<MaterialChip> values = suggestionMap.values();
        for (MaterialChip chip : values) {
            Widget parent = chip.getParent();
            if (parent instanceof ListItem) {
                parent.removeFromParent();
            }
        }
        suggestionMap.clear();

        clearErrorOrSuccess();
    }

    /**
     * @return the item values on autocomplete
     * @see #getValue()
     */
    public List<String> getItemValues() {
        Set<Suggestion> keySet = suggestionMap.keySet();
        List<String> values = new ArrayList<>(keySet.size());
        for (Suggestion suggestion : keySet) {
            values.add(suggestion.getReplacementString());
        }
        return values;
    }

    /**
     * @param itemValues
     *            the itemsSelected to set
     * @see #setValue(List)
     */
    public void setItemValues(List<String> itemValues) {
        if (itemValues == null) {
            clear();
            return;
        }
        List<Suggestion> list = new ArrayList<>(itemValues.size());
        for (String value : itemValues) {
            Suggestion suggestion = new gwt.material.design.client.base.Suggestion(value, value);
            list.add(suggestion);
        }
        setValue(list);
    }

    /**
     * @return the itemsHighlighted
     */
    public List<ListItem> getItemsHighlighted() {
        return itemsHighlighted;
    }

    /**
     * @param itemsHighlighted
     *            the itemsHighlighted to set
     */
    public void setItemsHighlighted(List<ListItem> itemsHighlighted) {
        this.itemsHighlighted = itemsHighlighted;
    }

    /**
     * @return the suggestion oracle
     */
    public SuggestOracle getSuggestions() {
        return suggestions;
    }

    /**
     * Sets the SuggestOracle to be used to provide suggestions. Also setups the
     * component with the needed event handlers and UI elements.
     *
     * @param suggestions
     *            the suggestion oracle to set
     */
    public void setSuggestions(SuggestOracle suggestions) {
        this.suggestions = suggestions;
        generateAutoComplete(suggestions);
    }

    public void setSuggestions(SuggestOracle suggestions, AutocompleteType type){
        setType(type);
        setSuggestions(suggestions);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
        if (this.box != null) {
            this.box.setLimit(limit);
        }
    }

    @Override
    public String getPlaceholder() {
        return itemBox.getElement().getAttribute("placeholder");
    }

    @Override
    public void setPlaceholder(String placeholder) {
        itemBox.getElement().setAttribute("placeholder", placeholder);
    }

    @Override
    public void setError(String error) {
        errorMixin.setError(error);
    }

    @Override
    public void setSuccess(String success) {
        errorMixin.setSuccess(success);
    }

    @Override
    public void clearErrorOrSuccess() {
        errorMixin.clearErrorOrSuccess();
    }

    /**
     * Gets the current {@link MaterialChipProvider}. By default, the class uses
     * an instance of {@link DefaultMaterialChipProvider}.
     */
    public MaterialChipProvider getChipProvider() {
        return chipProvider;
    }

    /**
     * Sets a {@link MaterialChipProvider} that can customize how the
     * {@link MaterialChip} is created for each selected {@link Suggestion}.
     */
    public void setChipProvider(MaterialChipProvider chipProvider) {
        this.chipProvider = chipProvider;
    }

    /**
     * When set to <code>false</code>, only {@link Suggestion}s from the
     * SuggestionOracle are accepted. Direct input create by the user is
     * ignored. By default, direct input is allowed.
     */
    public void setDirectInputAllowed(boolean directInputAllowed) {
        this.directInputAllowed = directInputAllowed;
    }

    /**
     * @return if {@link Suggestion}s created by direct input from the user
     *         should be allowed. By default directInputAllowed is
     *         <code>true</code>.
     */
    public boolean isDirectInputAllowed() {
        return directInputAllowed;
    }

    @Override
    public void showProgress(ProgressType type) {
        progressMixin.showProgress(ProgressType.INDETERMINATE);
    }

    @Override
    public void setPercent(double percent) {
        progressMixin.setPercent(percent);
    }

    @Override
    public void hideProgress() {
        progressMixin.hideProgress();
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return itemBox.addKeyUpHandler(handler);
    }

    @Override
    public void setType(AutocompleteType type) {
        typeMixin.setType(type);
    }

    @Override
    public AutocompleteType getType() {
        return typeMixin.getType();
    }

    /**
     * Interface that defines how a {@link MaterialChip} is created, given a
     * {@link Suggestion}.
     *
     * @see MaterialAutoComplete#setChipProvider(MaterialChipProvider)
     */
    public static interface MaterialChipProvider {

        /**
         * Creates and returns a {@link MaterialChip} based on the selected
         * {@link Suggestion}.
         *
         * @param suggestion
         *            the selected {@link Suggestion}
         *
         * @return the created MaterialChip, or <code>null</code> if the
         *         suggestion should be ignored.
         */
        MaterialChip getChip(Suggestion suggestion);
    }

    /**
     * Default implementation of the {@link MaterialChipProvider} interface,
     * used by the {@link MaterialAutoComplete}.
     *
     * @see MaterialAutoComplete#setChipProvider(MaterialChipProvider)
     */
    public static class DefaultMaterialChipProvider implements MaterialChipProvider {

        @Override
        public MaterialChip getChip(Suggestion suggestion) {
            final MaterialChip chip = new MaterialChip();

            String imageChip = suggestion.getDisplayString();
            String textChip = imageChip;

            String s = "<img src=\"";
            if (imageChip.contains(s)) {
                int ix = imageChip.indexOf(s) + s.length();
                imageChip = imageChip.substring(ix, imageChip.indexOf("\"", ix + 1));
                chip.setUrl(imageChip);
                textChip = textChip.replaceAll("[<](/)?img[^>]*[>]", "");
            }
            chip.setText(textChip);

            return chip;
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<? extends Suggestion>> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    /**
     * Returns the selected {@link Suggestion}s. Modifications to the list are
     * not propagated to the component.
     *
     * @return the list of selected {@link Suggestion}s, or empty if none was
     *         selected (never <code>null</code>).
     */
    @Override
    public List<? extends Suggestion> getValue() {
        return new ArrayList<>(suggestionMap.keySet());
    }

    @Override
    public void setValue(List<? extends Suggestion> value) {
        setValue(value, false);
    }

    @Override
    public void setValue(List<? extends Suggestion> value, boolean fireEvents) {
        clear();
        if (value != null) {
            for (Suggestion suggestion : value) {
                addItem(suggestion);
            }
        }
        if (fireEvents) {
            ValueChangeEvent.fire(this, getValue());
        }
    }
}
