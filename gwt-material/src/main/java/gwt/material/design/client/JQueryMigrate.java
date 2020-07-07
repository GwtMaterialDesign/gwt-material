package gwt.material.design.client;

import gwt.material.design.client.resources.JQueryMigrateDebugResources;
import gwt.material.design.client.resources.JQueryMigrateResources;

public class JQueryMigrate {

    public static void load() {
        load(false);
    }

    public static void load(boolean debug) {
        if (debug) {
            MaterialDesign.injectJs(JQueryMigrateDebugResources.INSTANCE.jQueryMigrate());
        } else {
            MaterialDesign.injectJs(JQueryMigrateResources.INSTANCE.jQueryMigrate());
        }
    }
}
