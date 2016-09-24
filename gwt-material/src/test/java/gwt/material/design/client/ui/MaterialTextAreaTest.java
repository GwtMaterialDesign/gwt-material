package gwt.material.design.client.ui;

import org.junit.Test;

public class MaterialTextAreaTest extends MaterialValueBoxTest {

    @Test
    public void testTextArea() {
        checkValueBox(new MaterialTextArea());
        checkResizeRule(new MaterialTextArea());
    }

    public <T extends MaterialTextArea> void checkResizeRule(T widget) {
        widget.setResizeRule(MaterialTextArea.ResizeRule.AUTO);

        widget.setResizeRule(MaterialTextArea.ResizeRule.FOCUS);

        widget.setResizeRule(MaterialTextArea.ResizeRule.NONE);
    }
}
