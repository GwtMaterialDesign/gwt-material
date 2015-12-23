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
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.helper.UiHelper;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.events.PageSelectionEvent;
import gwt.material.design.client.ui.html.ListItem;
import gwt.material.design.client.ui.html.UnorderedList;

import static gwt.material.design.client.events.PageSelectionEvent.*;

//@formatter:off
/**
 * Material Pager with page event
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code<m:MaterialPager  ui:field='pager' />}
 * </pre>
 * @author Guaido79
 */
public class MaterialPager extends MaterialWidget  {

    private int total;
    private int pageSize = 10;
    private int currentPage = 1;
    private int maxPageElement = 10;

    private int $totalPages;

    private PagerListItem linkLeft;
    private PagerListItem linkRight;

    public MaterialPager() {
        super(Document.get().createULElement());
        addStyleName("pagination");
        setWaves(WavesType.DEFAULT);
    }

    public MaterialPager(int total, int pageSize) {
        this();
        this.total = total;
        this.pageSize = pageSize;
    }

    public MaterialPager(int total, int pageSize, int currentPage) {
        this(total, pageSize);
        this.currentPage = currentPage;
    }

    @Override
    protected void onLoad() {
        init();
    }

    private void init() {
        $totalPages = total / pageSize + (((double)total % (double)pageSize) > 0 ? 1 : 0);

        add(getOrCreateLiElementLeft());
        createPageNumberLinks();
        add(getOrCreateLiElementRight());

        onPageSelection(1);

    }

    private void createPageNumberLinks() {
        for (int i = 0; i < getWidgetCount(); i++) {
            PagerListItem widget = (PagerListItem) getWidget(i);
            if (!widget.isFixed()) { remove(i); }
        }
        for (int i = 0; i < $totalPages; i++) {
            add(createLiElementForPage(i + 1));
        }
    }

    private PagerListItem createLiElementForPage(final int page) {
        final PagerListItem pageLiElement = new PagerListItem();

        pageLiElement.setFixed(false);
        pageLiElement.add(createLinkPage(page));

        addPageSelectionHandler(new PageSelectionHandler() {
            @Override
            public void onPageSelected(PageSelectionEvent event) {
                pageLiElement.setActive(event.getPageTo() == page);
            }
        });

        pageLiElement.addHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onPageSelection(page);
                event.preventDefault();
                event.stopPropagation();
            }
        }, ClickEvent.getType());

        return pageLiElement;
    }

    private PagerListItem getOrCreateLiElementLeft() {
        linkLeft = new PagerListItem();
        linkLeft.setFixed(true);
        MaterialLink mLink = createLinkLeft();
        linkLeft.addHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (linkLeft.isEnabled())
                    onPageSelection(currentPage - 1);
                event.preventDefault();
                event.stopPropagation();
            }
        }, ClickEvent.getType());
        this.linkLeft.add(mLink);

        addPageSelectionHandler(new PageSelectionHandler() {
            @Override
            public void onPageSelected(PageSelectionEvent event) {
                MaterialPager.this.linkLeft.setEnabled(event.getPageTo() > 1);
            }
        });

        return this.linkLeft;
    }

    private PagerListItem getOrCreateLiElementRight() {
        linkRight = new PagerListItem();
        linkRight.setFixed(true);
        MaterialLink mLink = createLinkRight();
        linkRight.addHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (linkRight.isEnabled())
                    onPageSelection(currentPage + 1);
                event.stopPropagation();
                event.preventDefault();
            }
        }, ClickEvent.getType());
        this.linkRight.add(mLink);

        addPageSelectionHandler(new PageSelectionHandler() {
            @Override
            public void onPageSelected(PageSelectionEvent event) {
                MaterialPager.this.linkRight.setEnabled(event.getPageTo() < $totalPages);
            }
        });

        return this.linkRight;
    }

    private MaterialLink createLinkPage(final int page) {
        MaterialLink link = new MaterialLink(String.valueOf(page));

        return link;
    }
    private MaterialLink createLinkLeft() {
        final MaterialLink linkLeft = new MaterialLink(IconType.CHEVRON_LEFT);
        linkLeft.setIconPosition(IconPosition.NONE);
        return linkLeft;
    }

    private MaterialLink createLinkRight() {
        final MaterialLink linkRight = new MaterialLink(IconType.CHEVRON_RIGHT);
        linkRight.setIconPosition(IconPosition.NONE);
        return linkRight;
    }

    private void onPageSelection(int page) {
        this.currentPage = page;
        PageSelectionEvent event = new PageSelectionEvent();
        event.setPageFrom(this.currentPage);
        event.setPageTo(page);
        event.setTotalPage(this.$totalPages);
        fireEvent(event);
    }

    public void addPageSelectionHandler(PageSelectionHandler handler) {
        this.addHandler(handler, TYPE);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxPageElement() {
        return maxPageElement;
    }

    public void setMaxPageElement(int maxPageElement) {
        this.maxPageElement = maxPageElement;
    }

    static class PagerListItem extends ListItem {

        private boolean fixed;
        private boolean enabled;

        public PagerListItem() {
            addStyleName("waves-effect");
            sinkEvents(Event.ONCLICK | Event.TOUCHEVENTS);

        }

        public boolean isFixed() {
            return fixed;
        }

        public void setFixed(boolean fixed) {
            this.fixed = fixed;
        }

        public void setActive(boolean active) {
            if (active) {
                addStyleName("active");
            }
            else {
                removeStyleName("active");
            }
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }

        @Override
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
            if (!enabled) {
                addStyleName("disabled");
            }
            else {
                removeStyleName("disabled");
            }


        }
    }
}
