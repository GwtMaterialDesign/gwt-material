package gwt.material.design.client.ui.base;

import com.google.gwt.dom.client.Document;
import gwt.material.design.client.MaterialDesign;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.resources.MaterialResources;
import gwt.material.design.client.resources.WithJQueryResources;
import org.junit.Before;
import org.junit.Test;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class MaterialBasicTest extends MaterialWidgetTest {

    @Before
    public void testJQuery() {
        MaterialDesign.injectJs(WithJQueryResources.INSTANCE.jQuery());
        MaterialDesign.injectJs(MaterialResources.INSTANCE.materializeJs());
        assertTrue(MaterialDesign.isjQueryLoaded());
        assertTrue(MaterialDesign.isMaterializeLoaded());
        // gwt-material-jquery Test
        assertNotNull($("body"));
    }

    @Test
    public void testMaterialWidget() {
        MaterialWidget widget = new MaterialWidget(Document.get().createDivElement());
        checkWidget(widget);
    }
}
