package gwt.material.design.client.async.loader;

import gwt.material.design.client.constants.IconType;

public interface AsyncIconDisplayLoader<V> extends AsyncDisplayLoader<V> {

    IconType getLoadingIcon();

    IconType getSuccessIcon();

    IconType getErrorIcon();
}
