package gwt.material.design.client.base.mixin;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasDependency;

import java.util.LinkedHashMap;
import java.util.Map;

public class DependencyMixin implements HasDependency {

    private static Map<Class<? extends Widget>, Boolean> libs = new LinkedHashMap();
    private static boolean debug = false;
    private final Class<? extends Widget> widgetClass;

    public DependencyMixin(Class<? extends Widget> widgetClass) {
        this.widgetClass = widgetClass;
    }

    @Override
    public void onDependencyLoaded() {

    }

    public static void install(Class<? extends Widget> widgetClass, TextResource minifiedPath, TextResource debugPath) {
        if (!debug) {
            install(minifiedPath, widgetClass, false);
        } else {
            install(debugPath, widgetClass, true);
        }
    }

    protected static void install(TextResource resource, Class<? extends Widget> widgetClass, boolean sourceUrl) {
        String name = resource.getName();
        if (libs.get(widgetClass) == null || !libs.get(widgetClass)) {
            libs.put(widgetClass, false);
            String text = resource.getText() + (sourceUrl ?
                    "//# sourceURL=" + resource.getName() + ".js" : "");
            ScriptInjector.fromString(text)
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    /*.setCallback(new Callback<Void, Exception>() {
                        @Override
                        public void onFailure(Exception reason) {
                            // Handle failure
                            GWT.log("Script injection failed: " + reason.getMessage());
                        }

                        @Override
                        public void onSuccess(Void result) {
                            // Handle success
                            GWT.log("Script injection successful!");
                            libs.put(widgetClass, true);
                        }
                    })*/
                    .inject();


        } else {
            // onLoad();
        }
    }

    public boolean isDependencyLoaded(Class<? extends Widget> widgetClass) {
        return libs.get(widgetClass);
    }
}
