package gwt.material.design.client.ui;

import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.constants.Edge;
import gwt.material.design.client.constants.SideNavType;

public class MaterialCardSideNav extends MaterialSideNav {

    private HandlerRegistration cardOpenedHandler;
    private HandlerRegistration cardClosedHandler;
    private HandlerRegistration cardOpeningHandler;
    private HandlerRegistration cardClosingHandler;

    public MaterialCardSideNav() {
        super(SideNavType.CARD);
    }

    @Override
    protected void build() {
        applyCardType();
    }

    /**
     * Applies a card that contains a shadow and this type
     * is good for few sidenav link items
     */
    protected void applyCardType() {
        applyTransition(getMain(), 200);
        if (cardOpeningHandler == null) {
            cardOpeningHandler = addOpeningHandler(event -> pushElement(getMain(), getWidth() + 20 ));
        }
        if (cardOpenedHandler == null) {
            cardOpenedHandler = addOpenedHandler(event -> {
                if (getEdge() == Edge.LEFT) {
                    setLeft(0);
                } else {
                    setRight(0);
                }
            });
        }
        if (cardClosingHandler == null) {
            cardClosingHandler = addClosingHandler(event -> pushElement(getMain(), 0));
        }
        if (cardClosedHandler == null) {
            cardClosedHandler = addClosedHandler(event -> {
                if (getEdge() == Edge.LEFT) {
                    setLeft(-(getWidth() + 20));
                } else {
                    setRight(-(getWidth() + 20));
                }
            });
        }
    }
}
