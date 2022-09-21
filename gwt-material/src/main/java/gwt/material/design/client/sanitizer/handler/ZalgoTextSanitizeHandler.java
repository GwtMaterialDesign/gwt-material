package gwt.material.design.client.sanitizer.handler;

import gwt.material.design.client.sanitizer.ValueSanitizerException;
import gwt.material.design.client.sanitizer.utils.ZalgoUtil;

public class ZalgoTextSanitizeHandler extends AbstractSanitizeHandler {

    public ZalgoTextSanitizeHandler() {
    }

    @Override
    public boolean sanitize(String value) {

        if (ZalgoUtil.hasZalgo(value)) {
            throw new ValueSanitizerException("Value must not contain Zalgo Characters");
        }
        return true;
    }
}
