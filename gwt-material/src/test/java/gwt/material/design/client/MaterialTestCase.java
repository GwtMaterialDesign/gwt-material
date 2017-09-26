package gwt.material.design.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.resources.MaterialResources;
import gwt.material.design.client.resources.WithJQueryResources;
import org.junit.Ignore;

import static gwt.material.design.jquery.client.api.JQuery.$;

@Ignore
public class MaterialTestCase extends GWTTestCase {

    private boolean tearDownRootPanel = false;

    @Override
    public String getModuleName() {
        return "gwt.material.design.GwtMaterialDesign";
    }

    @Override
    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();

        if(!MaterialDesign.isjQueryLoaded()) {
            WithJQueryResources jQueryResources = GWT.create(WithJQueryResources.class);
            // Test JQuery
            MaterialDesign.injectJs(jQueryResources.jQuery());
            assertTrue(MaterialDesign.isjQueryLoaded());
            // Test Materialize
            MaterialDesign.injectJs(MaterialResources.INSTANCE.materializeJs());
            assertTrue(MaterialDesign.isMaterializeLoaded());
            // gwt-material-jquery Test
            assertNotNull($("body"));
        }

        // Always tear down root panel by default
        setTearDownRootPanel(true);
    }

    @Override
    protected void gwtTearDown() throws Exception {
        super.gwtTearDown();

        if(isTearDownRootPanel()) {
            RootPanel.get().clear();
        }
    }

    protected void setTearDownRootPanel(boolean tearDownRootPanel) {
        this.tearDownRootPanel = tearDownRootPanel;
    }

    protected boolean isTearDownRootPanel() {
        return tearDownRootPanel;
    }
}
