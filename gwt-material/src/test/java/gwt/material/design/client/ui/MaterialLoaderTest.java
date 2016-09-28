package gwt.material.design.client.ui;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.base.MaterialWidgetTest;
import org.junit.Test;

public class MaterialLoaderTest extends MaterialWidgetTest {

    @Test
    public void testLoader() {
        checkLoaderBasic();
        checkLoaderWithinPanel();
        checkProgressBasic();
        checkProgressWithinPanel();
    }

    protected void checkLoaderBasic() {
        checkLoader(RootPanel.get());
    }

    protected void checkLoaderWithinPanel() {
        checkLoader(new MaterialPanel());
    }

    protected void checkProgressBasic() {
        checkProgress(RootPanel.get());
    }

    protected void checkProgressWithinPanel() {
        checkProgress(new MaterialPanel());
    }

    protected void checkLoader(ComplexPanel panel) {
        MaterialLoader.showLoading(true, panel);
        assertNotNull(panel.getWidget(0));
        assertTrue(panel.getWidget(0) instanceof MaterialWidget);
        MaterialWidget loaderWrapper = (MaterialWidget) panel.getWidget(0);
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.LOADER_WRAPPER));
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        assertTrue(loaderWrapper.getWidget(0) instanceof MaterialPreLoader);
        MaterialPreLoader preLoader = (MaterialPreLoader) loaderWrapper.getWidget(0);
        checkPreLoader(preLoader);
        assertEquals(preLoader.getChildren().size(), 4);
        for (Widget w : preLoader.getChildren()) {
            assertTrue(w instanceof MaterialSpinner);
            MaterialSpinner spinner = (MaterialSpinner) w;
            checkSpinner(spinner);
        }

        MaterialLoader.showLoading(false);
        assertEquals(panel.getWidgetCount(), 0);
    }

    protected void checkPreLoader(MaterialPreLoader loader) {
        assertNotNull(loader);
        loader.setSize(LoaderSize.SMALL);
        assertEquals(loader.getSize(), LoaderSize.SMALL);
        loader.setSize(LoaderSize.MEDIUM);
        assertEquals(loader.getSize(), LoaderSize.MEDIUM);
        loader.setSize(LoaderSize.BIG);
        assertEquals(loader.getSize(), LoaderSize.BIG);
    }

    protected void checkSpinner(MaterialSpinner spinner) {
        spinner.setColor(SpinnerColor.YELLOW_ONLY);
        assertEquals(spinner.getColor(), SpinnerColor.YELLOW_ONLY);
        assertTrue(spinner.getElement().hasClassName(SpinnerColor.YELLOW_ONLY.getCssName()));
        spinner.setColor(SpinnerColor.YELLOW);
        assertEquals(spinner.getColor(), SpinnerColor.YELLOW);
        assertTrue(spinner.getElement().hasClassName(SpinnerColor.YELLOW.getCssName()));
    }

    protected void checkProgress(ComplexPanel panel) {
        MaterialLoader.showProgress(true, panel);
        assertNotNull(panel.getWidget(0));
        assertTrue(panel.getWidget(0) instanceof MaterialWidget);
        MaterialWidget loaderWrapper = (MaterialWidget) panel.getWidget(0);
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.PROGRESS_WRAPPER));
        assertTrue(loaderWrapper.getElement().hasClassName(CssName.VALIGN_WRAPPER));

        assertTrue(loaderWrapper.getWidget(0) instanceof MaterialProgress);
        MaterialProgress progress = (MaterialProgress) loaderWrapper.getWidget(0);
        checkProgress(progress);

        MaterialLoader.showLoading(false);
        assertEquals(panel.getWidgetCount(), 0);
    }

    protected void checkProgress(MaterialProgress progress) {
        assertNotNull(progress);
        // Check default type
        progress.setType(ProgressType.INDETERMINATE);
        assertEquals(progress.getType(), ProgressType.INDETERMINATE);
        progress.setType(ProgressType.DETERMINATE);
        assertEquals(progress.getType(), ProgressType.DETERMINATE);

        progress.setPercent(90);
        assertEquals(progress.getPercent(), (double) 90);
        // Get the target widget for percentage update
        assertNotNull(progress.getWidget(0));
        assertTrue(progress.getWidget(0) instanceof MaterialWidget);
        MaterialWidget progressDiv = (MaterialWidget) progress.getWidget(0);
        assertEquals(progressDiv.getElement().getStyle().getWidth(), "90.0%");
        // Check color
        progress.setColor(Color.RED);
        assertEquals(progress.getColor(), Color.RED);
        assertTrue(progressDiv.getElement().hasClassName(Color.RED.getCssName()));
    }
}
