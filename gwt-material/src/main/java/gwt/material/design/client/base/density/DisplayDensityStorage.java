package gwt.material.design.client.base.density;

import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;

public class DisplayDensityStorage {

    public static final String STORAGE_ID = "app_density";
    private static Storage storage = Storage.getLocalStorageIfSupported();

    public static void apply(DisplayDensity density) {
        if (Storage.isLocalStorageSupported()) {
            storage.setItem(STORAGE_ID, density.getCssName());
            Window.Location.reload();
        } else {
            GWT.log("localStorage API is not supported by your browser.");
        }
    }

    public static DisplayDensity get() {
        if (Storage.isLocalStorageSupported()) {
            Storage storage = Storage.getLocalStorageIfSupported();
            return DisplayDensity.fromStyleName(storage.getItem(STORAGE_ID));
        } else {
            return DisplayDensity.DEFAULT;
        }
    }
}
