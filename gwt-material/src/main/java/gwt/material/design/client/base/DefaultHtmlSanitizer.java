package gwt.material.design.client.base;

import com.google.gwt.safehtml.shared.HtmlSanitizer;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * HTML-escapes its argument and returns the result wrapped as a SafeHtml.
 */
public class DefaultHtmlSanitizer implements HtmlSanitizer {

    @Override
    public SafeHtml sanitize(String html) {
        return SafeHtmlUtils.fromString(html);
    }
}
