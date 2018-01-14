package gwt.material.design.client.base.helper;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.js.ScrollOption;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * A set of scroll helper utils for GMD Widgets
 *
 * @author kevzlou7979@gmail.com
 */
public class ScrollHelper {

    /**
     * Scroll to the target widget's offset
     */
    public static void scrollTo(Widget widget) {
        scrollTo(widget.getElement());
    }

    /**
     * Scroll to the target element's offset
     */
    public static void scrollTo(Element element) {
        scrollTo(element, $("html, body").asElement());
    }

    /**
     * Scroll to the target widget's offset inside the desired container. Default by "html, body".
     */
    public static void scrollTo(Element element, Element container) {
        scrollTo($(element).offset().top, container);
    }

    /**
     * Scroll to the target offset value.
     */
    public static void scrollTo(double offset) {
        scrollTo(offset, $("html, body").asElement());
    }

    /**
     * Scroll to the target offset value inside the desired container. Default by "html, body".
     */
    public static void scrollTo(double offset, Element container) {
        ScrollOption option = new ScrollOption();
        option.scrollTop = offset;
        $(container).animate(option, 400);
    }
}
