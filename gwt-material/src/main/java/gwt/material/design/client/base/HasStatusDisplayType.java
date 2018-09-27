package gwt.material.design.client.base;

import gwt.material.design.client.base.mixin.StatusDisplayMixin;
import gwt.material.design.client.constants.StatusDisplayType;

public interface HasStatusDisplayType {

    void setStatusDisplayType(StatusDisplayType displayType);

    StatusDisplayType getStatusDisplayType();

    void updateStatusDisplay(StatusDisplayMixin.StatusType statusType);
}
