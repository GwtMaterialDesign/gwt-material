package gwt.material.design.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface JQueryMigrateDebugResources extends ClientBundle {

    JQueryMigrateDebugResources INSTANCE = GWT.create(JQueryMigrateDebugResources.class);

    @Source("js/jquery-migrate-3.3.1.js")
    TextResource jQueryMigrate();
}
