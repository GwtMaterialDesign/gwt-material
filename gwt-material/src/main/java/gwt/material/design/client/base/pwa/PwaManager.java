package gwt.material.design.client.base.pwa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

public class PwaManager implements HasAppManifest {

    private static PwaManager instance = GWT.create(PwaManager.class);

    @Override
    public void setupAppManifest(String manifestUrl) {
        Element head = Document.get().getElementsByTagName("head").getItem(0);
        Element linkManifest = Document.get().createLinkElement();
        linkManifest.setAttribute("rel", "manifest");
        linkManifest.setAttribute("href", manifestUrl);
        head.appendChild(linkManifest);
    }

    public static PwaManager getInstance() {
        return instance;
    }
}
