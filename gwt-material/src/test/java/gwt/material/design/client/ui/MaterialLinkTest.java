package gwt.material.design.client.ui;

import gwt.material.design.client.ui.base.AbstractIconButtonTest;
import org.junit.Test;

public class MaterialLinkTest extends AbstractIconButtonTest {

    @Test
    public void tsetLink() {
        MaterialLink link = new MaterialLink();
        checkIconButton(link);
    }
}
