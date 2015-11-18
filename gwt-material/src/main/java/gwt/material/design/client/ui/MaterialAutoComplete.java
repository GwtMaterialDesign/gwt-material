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

import gwt.material.design.client.base.HasError;
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.base.MaterialSuggestionOracle;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;

//@formatter:off
/**
 * Use GWT Autocomplete to search for matches from local or remote data sources.
 * We used MultiWordSuggestOracle to populate the list to be added on the
 * autocomplete values.
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 *  <m:MaterialAutoComplete ui:field="autocomplete" placeholder="States" />}
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#autocompletes">Material AutoComplete</a>
 */
//@formatter:on
public class MaterialAutoComplete extends MaterialWidget implements HasError, HasPlaceholder {

    private List<String> itemValues = new ArrayList<>();
    private List<ListItem> itemsHighlighted = new ArrayList<>();
    private FlowPanel panel = new FlowPanel();
    private UnorderedList list = new UnorderedList();
    private MultiWordSuggestOracle suggestions;
    private TextBox itemBox = new TextBox();
    private int limit = 0;
    private MaterialLabel lblError = new MaterialLabel();

    private final ErrorMixin<MaterialAutoComplete, MaterialLabel> errorMixin = new ErrorMixin<>(this, lblError, list);
    
    /**
     * Use MaterialAutocomplete to search for matches from
     * local or remote data sources.
     */
    public MaterialAutoComplete() {
         initWidget(panel);
    }
    
    /**
     * Use MaterialAutocomplete to search for matches from
     * local or remote data sources.
     */
    public MaterialAutoComplete(MaterialSuggestionOracle suggestions) {
        initWidget(panel);
        generateAutoComplete(suggestions);
    }
 
    /**
     * Generate and build the List Items to be set on Auto Complete box.
     */
    private void generateAutoComplete(MaterialSuggestionOracle suggestions) {
        list.setStyleName("multiValueSuggestBox-list");
        this.suggestions = suggestions;
        final ListItem item = new ListItem();

        item.setStyleName("multiValueSuggestBox-input-token");
        final SuggestBox box = new SuggestBox(suggestions, itemBox);
        String autocompleteId = DOM.createUniqueId();
        itemBox.getElement().setId(autocompleteId);

        item.add(box);
        list.add(item);
        
        itemBox.addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                switch (event.getNativeKeyCode()) {
                case KeyCodes.KEY_ENTER:
                   addItem(itemBox, list);
                   break;

                case KeyCodes.KEY_BACKSPACE:
                    if (itemBox.getValue().trim().isEmpty()) {
                        if (itemsHighlighted.isEmpty()) {
                            if (itemValues.size() > 0) {
                                ListItem li = (ListItem) list.getWidget(list.getWidgetCount() - 2);
                                MaterialChip p = (MaterialChip) li.getWidget(0);
                                if (itemValues.contains(p.getText())) {
                                    itemValues.remove(p.getText());
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
                            itemValues.remove(p.getText());
                        }
                        itemsHighlighted.clear();
                    }
                    itemBox.setFocus(true);
                    break;
                }
            }
        });
        
        itemBox.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                box.showSuggestionList();
            }
        });
 
        box.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            public void onSelection(SelectionEvent<SuggestOracle.Suggestion> selectionEvent) {
                addItem(itemBox, list);
            }
        });
 
        panel.add(list);
        panel.getElement().setAttribute("onclick", "document.getElementById('"+autocompleteId+"').focus()");
        panel.add(lblError);
        box.setFocus(true);
    }

    /**
     * Adding the item value using Material Chips added on auto complete box
     */
    private void addItem(final TextBox itemBox, final UnorderedList list) {
        if(getLimit() > 0) {
            if(itemValues.size() == getLimit()) {
                itemBox.setValue("");
                return;
            }
        }

        String value = itemBox.getValue();
        if (value != null && !"".equals(value.trim()) && !itemValues.contains(value.trim())) {

            final ListItem displayItem = new ListItem();
            displayItem.setStyleName("multiValueSuggestBox-token");

            String imageChip = value;
            String textChip = value;
            final MaterialChip chip = new MaterialChip();

            String s = "<img src=\"";
            if(imageChip.contains(s)){
                int ix = imageChip.indexOf(s)+s.length();
                imageChip = imageChip.substring(ix, imageChip.indexOf("\"", ix+1));
                chip.setUrl(imageChip);
                textChip = textChip.replaceAll("[<](/)?img[^>]*[>]", "");
            }
            chip.setIconType(IconType.CLOSE);
            chip.setText(textChip);
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
                    itemValues.remove(chip.getText());
                    list.remove(displayItem);
                }
            });

            if(!itemValues.contains(textChip)){
                displayItem.add(chip);
                itemValues.add(chip.getText());
                list.insert(displayItem, list.getWidgetCount() - 1);
                itemBox.setValue("");
                itemBox.setFocus(true);
            } else {
                itemBox.setValue("");
            }
        } else {
            itemBox.setValue("");
        }
    }

    /**
     * Clear the chip items on the autocomplete box
     */
    public void clear() {
        ListItem tagToRemove;
        int num = list.getWidgetCount();
        for (int i=num-1; i>=0; i--) {
            tagToRemove = ((ListItem)list.getWidget(i));
            tagToRemove.removeFromParent();
        }
        clearErrorOrSuccess();
    }
 
    /**
     * @return the item values on  autocomplete
     */
    public List<String> getItemValues() {
        return itemValues;
    }

    /**
     * @param itemValues the itemsSelected to set
     */
    public void setItemValues(List<String> itemValues) {
        this.itemValues = itemValues;
    }

    /**
     * @return the itemsHighlighted
     */
    public List<ListItem> getItemsHighlighted() {
        return itemsHighlighted;
    }

    /**
     * @param itemsHighlighted the itemsHighlighted to set
     */
    public void setItemsHighlighted(List<ListItem> itemsHighlighted) {
        this.itemsHighlighted = itemsHighlighted;
    }

    /**
     * @return the suggestions
     */
    public MultiWordSuggestOracle getSuggestions() {
        return suggestions;
    }

    /**
     * @param suggestions the suggestions to set
     */
    public void setSuggestions(MaterialSuggestionOracle suggestions) {
        this.suggestions = suggestions;
        generateAutoComplete(suggestions);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
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
}