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


import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.HasError;
import gwt.material.design.client.custom.MaterialSuggestionOracle;

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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;

public class MaterialAutoComplete extends Composite implements HasError,  HasGrid{
	
    private List<String> itemValues = new ArrayList<String>();
    private List<ListItem> itemsHighlighted = new ArrayList<ListItem>();
    private FlowPanel panel = new FlowPanel();
    private UnorderedList list = new UnorderedList();
    private MultiWordSuggestOracle suggestions;
    private TextBox itemBox = new TextBox();
    private boolean disabled;
    private String placeholder = "";
    private MaterialLabel lblError = new MaterialLabel();
    
    /**
     * Use MaterialAutocomplete to search for matches from local or remote data sources.
     */
    public MaterialAutoComplete() {
    	 initWidget(panel);
	}
    
    /**
     * Use MaterialAutocomplete to search for matches from local or remote data sources.
     * @param suggestions
     */
    public MaterialAutoComplete(MaterialSuggestionOracle suggestions) {
        initWidget(panel);
        generateAutoComplete(suggestions);
    }
 
    /**
     * Generate and build the List Items to be set on Auto Complete box
     * @param suggestions
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
     * @param itemBox
     * @param list
     */
	private void addItem(final TextBox itemBox, final UnorderedList list) {
        if (itemBox.getValue() != null && !"".equals(itemBox.getValue().trim()) && !itemValues.contains(itemBox.getValue().trim())) {
           
            final ListItem displayItem = new ListItem();
            displayItem.setStyleName("multiValueSuggestBox-token");
            
            
            String imageChip = itemBox.getValue();
            String textChip = itemBox.getValue();
            final MaterialChip chip = new MaterialChip();
            String s = "<img src=\"";
            if(imageChip.contains(s)){
            	int ix = imageChip.indexOf(s)+s.length();
            	imageChip = imageChip.substring(ix, imageChip.indexOf("\"", ix+1));
            	chip.setImageUrl(imageChip);
            	textChip = textChip.replaceAll("[<](/)?img[^>]*[>]", "");
            }
            chip.setText(textChip);
            chip.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent clickEvent) {
                    if (itemsHighlighted.contains(displayItem)) { 
                    	chip.removeStyleName("blue white-text");
                        itemsHighlighted.remove(displayItem);
                    }
                    else {
                    	chip.addStyleName("blue white-text");
                        itemsHighlighted.add(displayItem);
                    }
                }
            });
 
         
           
            chip.getBtnClose().addClickHandler(new ClickHandler() {
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
            }else{
            	itemBox.setValue("");
            }
            
        }else{
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

	/**
	 * @return the placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	/**
	 * @param placeholder the placeholder to set
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		itemBox.getElement().setAttribute("placeholder", placeholder);
	}

	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		itemBox.setEnabled(!disabled);
	}

	
	

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}
	
	@Override
	public void setError(String error) {
		lblError.setText(error);
		lblError.addStyleName("field-error-label");
		lblError.removeStyleName("field-success-label");
		list.addStyleName("field-error");
		list.removeStyleName("field-success");
		lblError.setVisible(true);
	}

	@Override
	public void setSuccess(String success) {
		lblError.setText(success);
		lblError.addStyleName("field-success-label");
		lblError.removeStyleName("field-error-label");
		list.addStyleName("field-success");
		list.removeStyleName("field-error");
		lblError.setVisible(true);
	}
	
	@Override
	public void setOffset(String offset) {
		  String tobeadded = "";
		  String[] vals = offset.split(" ");
		  for(String val : vals){
		   tobeadded = tobeadded + " offset-" +  val;
		  }
		  this.addStyleName(tobeadded);
	}
}