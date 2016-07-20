package gwt.material.design.client.base;

import gwt.material.design.client.ui.MaterialModal;

import java.util.ArrayList;
import java.util.List;

/**
 * Modal Manager that easily compute and applied the correct z-index values
 * @author kebzlou7979
 */
public class MaterialModalManager {

    private static List<MaterialModal> modals = new ArrayList<>();
    private static int i = 1010;

    /**
     * Registers the modal and added to static modal lists
     */
    public static void register(MaterialModal modal) {
        modals.add(modal);
        resetZIndex();
    }

    /**
     *  Unregisters the modal and removed it from static modal lists
     */
    public static void unregister(MaterialModal modal) {
        modals.remove(modal);
        resetZIndex();
    }

    /**
     * Need to reset everytime we have register / unregister process
     */
    protected static void resetZIndex(){

        for(MaterialModal modal : modals) {
            modal.setDepth(i);
            i++;
        }
    }

}
