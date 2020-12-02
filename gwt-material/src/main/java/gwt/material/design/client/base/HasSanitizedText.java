package gwt.material.design.client.base;

import com.google.gwt.safehtml.shared.HtmlSanitizer;

public interface HasSanitizedText {

    void setText(String text, HtmlSanitizer sanitizer);
}
