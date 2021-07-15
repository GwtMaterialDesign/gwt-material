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
        $(containerSelector + " " + childSelector).sort((elem1, elem2) -> {
            Object elem1Order = $(elem1).data(dataAttribute);
            Object elem2Order = $(elem2).data(dataAttribute);
            if (elem1Order != null && elem2Order != null) {
                return Integer.parseInt(elem2Order.toString()) < Integer.parseInt(elem1Order.toString()) ? 1 : -1;
            }
            return -1;
        }).appendTo(containerSelector);
    }
}
