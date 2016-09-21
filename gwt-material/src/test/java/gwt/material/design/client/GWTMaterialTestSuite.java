package gwt.material.design.client;

import com.google.gwt.junit.tools.GWTTestSuite;
import gwt.material.design.client.ui.MaterialButtonTest;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Test suite for all GWT Material Widgets
 *
 * @author kevzlou7979
 */
public class GWTMaterialTestSuite extends TestCase {

    public static Test suite() {
        final GWTTestSuite suite = new GWTTestSuite("gwt-material test suite");
        suite.addTestSuite(MaterialButtonTest.class);
        return suite;
    }
}
