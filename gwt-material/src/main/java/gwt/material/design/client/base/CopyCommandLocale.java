package gwt.material.design.client.base;

public interface CopyCommandLocale {

    default String Copied() {
        return "Copied";
    }

    default String CopyToClipboard() {
        return "Copy to Clipboard";
    }
}
