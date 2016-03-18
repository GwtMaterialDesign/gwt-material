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

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TextBox;
import gwt.material.design.client.base.HasActive;
import gwt.material.design.client.base.SearchObject;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.events.SearchFinishEvent;
import gwt.material.design.client.events.SearchNoResultEvent;
import gwt.material.design.client.ui.html.Label;

import java.util.ArrayList;
import java.util.List;

//@formatter:off

/**
 * Material Search is a value box component that returs a result based on your search
 *
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialSearch placeholder="Sample"/>
 * }
 * </pre>
 *
 * <h3>Populating the search result objects</h3>
 * {@code
 *
 * List<SearchObject> objects = new ArrayList<>();
 *
 * private void onInitSearch(){
 *   objects.add(new SearchObject(IconType.POLYMER, "Pushpin", "#!pushpin"));
 *   objects.add(new SearchObject(IconType.POLYMER, "SideNavs", "#!sidenavs"));
 *   objects.add(new SearchObject(IconType.POLYMER, "Scrollspy", "#!scrollspy"));
 *   objects.add(new SearchObject(IconType.POLYMER, "Tabs", "#!tabs"));
 *   txtSearch.setListSearches(objects);
 * }
 *
 * }
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#navigations">Material Search</a>
 */
//@formatter:on
public class MaterialSearch extends MaterialValueBox<String> implements HasCloseHandlers<String>, HasActive {

    private Label label = new Label();
    private MaterialIcon iconSearch = new MaterialIcon(IconType.SEARCH);
    private MaterialIcon iconClose = new MaterialIcon(IconType.CLOSE);

    /**
     * The list of search objects added to MaterialSearchResult panel to
     * display the lists of result items
     */
    private List<SearchObject> listSearches = new ArrayList<>();
    /**
     * Used to determine the selected searches while matching the keyword to result
     */
    private List<SearchObject> tempSearches = new ArrayList<>();
    /**
     * Panel to display the result items
     */
    private MaterialSearchResult searchResult;
    /**
     * Link selected to determine easily during the selection event (up / down key events)
     */
    private MaterialLink selectedLink;
    /**
     * Gets the selected object after Search Finish event
     */
    private SearchObject selectedObject;
    /**
     * -1 means that the selected index is not yet selected.
     * It will increment or decrement once triggere by key up / down events
     */
    private int curSel = -1;

    public MaterialSearch() {
        super(new TextBox());
        setType(InputType.SEARCH);
        label.add(iconSearch);
        label.getElement().setAttribute("for", "search");
        add(label);
        add(iconClose);
        iconClose.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                CloseEvent.fire(MaterialSearch.this, getText());
            }
        });
    }

    @Override
    public void onLoad() {
        super.onLoad();
        // populate the lists of search result on search panel
        searchResult = new MaterialSearchResult();
        add(searchResult);
        // add keyup event to filter the searches
        addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                String keyword = getText().toLowerCase();
                // Clear the panel and temp objects
                searchResult.clear();
                tempSearches.clear();

                // Populate the search result items
                for(final SearchObject obj : getListSearches()) {
                    MaterialLink link = new MaterialLink();
                    link.setIconColor("grey");
                    link.setTextColor("black");
                    // Generate an icon
                    if(obj.getIcon()!=null) {
                        link.setIconType(obj.getIcon());
                    }

                    // Generate an image
                    MaterialImage image = new MaterialImage();
                    if(obj.getResource() != null){
                        image.setResource(obj.getResource());
                        link.insert(image, 0);
                    }

                    if(obj.getImageUrl() != null){
                        image.setUrl(obj.getImageUrl());
                        link.insert(image, 0);
                    }

                    if(!obj.getLink().isEmpty()) {
                        link.setHref(obj.getLink());
                    }
                    link.setText(obj.getKeyword());
                    link.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            setSelectedObject(obj);
                            reset(obj.getKeyword());
                        }
                    });
                    // If matches add to search result container and object to temp searches
                    if (obj.getKeyword().toLowerCase().contains(keyword)){
                        searchResult.add(link);
                        tempSearches.add(obj);
                    }
                }

                // Apply selected search
                if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER){
                    if(getCurSel()==-1){
                        setSelectedObject(tempSearches.get(0));
                        setSelectedLink((MaterialLink) searchResult.getWidget(0));
                    }else{
                        setSelectedObject(tempSearches.get(curSel));
                    }

                    MaterialLink selLink = getSelectedLink();
                    if(!selLink.getHref().isEmpty()) {
                        locateSearch(selLink.getHref());
                    }
                    reset(selLink.getText());
                }

                // Fire an event if theres no search result
                if(searchResult.getWidgetCount() == 0){
                    SearchNoResultEvent.fire(MaterialSearch.this);
                }

                // Selection logic using key down event to navigate the search results
                int totalItems = searchResult.getWidgetCount();
                if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_DOWN){
                    if(curSel >= totalItems){
                        setCurSel(getCurSel());
                        applyHighlightedItem((MaterialLink) searchResult.getWidget(curSel - 1));
                    }else{
                        setCurSel(getCurSel() + 1);
                        applyHighlightedItem((MaterialLink) searchResult.getWidget(curSel));
                    }
                }

                // Selection logic using key up event to navigate the search results
                if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_UP){
                    if(curSel <= -1){
                        setCurSel(-1);
                        applyHighlightedItem((MaterialLink) searchResult.getWidget(curSel));
                    }else {
                        setCurSel(getCurSel() - 1);
                        applyHighlightedItem((MaterialLink) searchResult.getWidget(curSel));
                    }
                }
            }

            // Resets the search result panel
            private void reset(String keyword){
                SearchFinishEvent.fire(MaterialSearch.this);
                curSel = -1;
                setText(keyword);
                searchResult.clear();
            }
        });
    }

    private void applyHighlightedItem(MaterialLink link){
        link.addStyleName("higlighted");
        setSelectedLink(link);
    }

    private native void locateSearch(String location)/*-{
        $wnd.window.location.hash = location;
    }-*/;

    @Override
    public HandlerRegistration addCloseHandler(CloseHandler<String> handler) {
        return addHandler(handler, CloseEvent.getType());
    }

    @Override
    public void setActive(boolean active) {
        if(active){
            this.setTextColor("black");
            iconClose.setIconColor("black");
            iconSearch.setIconColor("black");
        }else{
            iconClose.setIconColor("white");
            iconSearch.setIconColor("white");
        }
    }

    @Override
    public boolean isActive() {
        return false;
    }

    public MaterialLink getSelectedLink() {
        return selectedLink;
    }

    public void setSelectedLink(MaterialLink selectedLink) {
        this.selectedLink = selectedLink;
    }

    public List<SearchObject> getListSearches() {
        return listSearches;
    }

    public void setListSearches(List<SearchObject> listSearches) {
        this.listSearches = listSearches;
    }

    public int getCurSel() {
        return curSel;
    }

    public void setCurSel(int curSel) {
        this.curSel = curSel;
    }

    public SearchObject getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(SearchObject selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * Gets the tempory search objects
     * @return
     */
    public List<SearchObject> getTempSearches() {
        return tempSearches;
    }

    /**
     * This handler will be triggered when search is finish
     */
    public HandlerRegistration addSearchFinishHandler(SearchFinishEvent.SearchFinishHandler handler) {
        return addHandler(handler, SearchFinishEvent.TYPE);
    }

    /**
     * This handler will be triggered when there's no search result
     */
    public HandlerRegistration addSearchNoResultHandler(SearchNoResultEvent.SearchNoResultHandler handler) {
        return addHandler(handler, SearchNoResultEvent.TYPE);
    }
}


