package gwt.material.design.client.base;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import gwt.material.design.client.ui.MaterialModal;

import java.util.ArrayList;
import java.util.List;

/**
 * Modal Manager that easily compute and applied the correct z-index values
 * @author kebzlou7979
 */
public class MaterialModalManager {

    private static List<MaterialModal> modals;
    private static int index = 1010;

    /**
     * Registers the modal and added to static modal lists
     */
    public static void register(MaterialModal modal) {
        if(modals == null) { modals = new ArrayList<>(); }
        modals.add(modal);
        resetZIndex();
    }

    /**
     *  Unregisters the modal and removed it from static modal lists
     */
    public static void unregister(MaterialModal modal) {
        if(modals == null) { modals = new ArrayList<>(); }
        modals.remove(modal);
        resetZIndex();
    }

    /**
     * Need to reset everytime we have register / unregister process
     */
    protected static void resetZIndex(){
        for(MaterialModal modal : modals) {
            modal.setDepth(index++);
        }
    }
}
