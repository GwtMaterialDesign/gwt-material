package gwt.material.design.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class PageSelectionEvent extends GwtEvent<PageSelectionEvent.PageSelectionHandler> {

    private int pageFrom;
    private int pageTo;
    private int totalPage;

    public interface PageSelectionHandler extends EventHandler {
        void onPageSelected(PageSelectionEvent event);
    }

    public static final Type<PageSelectionHandler> TYPE = new Type<>();

    @Override
    public Type<PageSelectionHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PageSelectionHandler handler) {
        handler.onPageSelected(this);
    }

    public int getPageFrom() {
        return pageFrom;
    }

    public void setPageFrom(int pageFrom) {
        this.pageFrom = pageFrom;
    }

    public int getPageTo() {
        return pageTo;
    }

    public void setPageTo(int pageTo) {
        this.pageTo = pageTo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
