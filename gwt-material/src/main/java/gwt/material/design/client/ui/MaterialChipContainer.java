package gwt.material.design.client.ui;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class MaterialChipContainer extends MaterialPanel {

    private List<MaterialChip> chipList = new ArrayList<>();
    private boolean enableToggle = true;

    public MaterialChipContainer() {
        super("chip-container");
    }

    @Override
    protected void add(Widget child, Element container) {
        super.add(child, container);

        if (child instanceof MaterialChip) {
            MaterialChip chip = (MaterialChip) child;
            chip.registerHandler(chip.addClickHandler(event -> {
                if (isEnableToggle()) setActive(chip);
            }));
            chipList.add(chip);
        }
    }

    @Override
    protected void insert(Widget child, Element container, int beforeIndex, boolean domInsert) {
        super.insert(child, container, beforeIndex, domInsert);

        if (child instanceof MaterialChip) {
            chipList.add(beforeIndex, (MaterialChip) child);
        }
    }

    @Override
    public boolean remove(Widget w) {
        if (w instanceof MaterialChip) {
            chipList.remove(w);
        }
        return super.remove(w);
    }

    @Override
    public void clear() {
        super.clear();

        chipList.clear();
    }

    public void setActive(MaterialChip chip) {
        clearActive();
        chip.setActive(true);
    }

    public void setActive(int index) {
        clearActive();
        MaterialChip chip = chipList.get(index);
        if (chip != null) {
            setActive(chip);
        }
    }

    public void clearActive() {
        chipList.forEach(c -> c.setActive(false));
    }

    public boolean isEnableToggle() {
        return enableToggle;
    }

    public void setEnableToggle(boolean enableToggle) {
        this.enableToggle = enableToggle;
    }
}
