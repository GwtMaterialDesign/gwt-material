/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2021 GwtMaterialDesign
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
package gwt.material.design.client.base.helper;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class UiSortHelper {

    /**
     * Calls a Jquery sort api
     *
     * @param containerSelector - The container Id i.e UnorderedList - (ul)
     * @param childSelector     - The child element selector (i.e List - li)
     * @param dataAttribute     - The data attribute that indicate the order of each children (i.e order -> data-attribute='order')
     */
    public static void sort(String containerSelector, String childSelector, String dataAttribute) {
        if (containerSelector != null && !containerSelector.isEmpty() && childSelector != null && !childSelector.isEmpty() && dataAttribute != null && !dataAttribute.isEmpty()) {
            $(containerSelector + " " + childSelector + "[data-order]").sort((elem1, elem2) -> {
                Object elem1Order = $(elem1).data(dataAttribute);
                Object elem2Order = $(elem2).data(dataAttribute);
                if (elem1Order != null && elem2Order != null) {
                    int order1 = Integer.parseInt(elem1Order.toString());
                    int order2 = Integer.parseInt(elem2Order.toString());
                    return order2 < order1 || order1 < 0 ? 1 : -1;
                }
                return -1;
            }).appendTo(containerSelector);
        }
    }
}
