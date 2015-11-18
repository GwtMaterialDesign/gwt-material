package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import gwt.material.design.client.base.AbstractButton;

//@formatter:off

/**
 * Breadcrumbs are a good way to display your current location.
 * This is usually used when you have multiple layers of content.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialNavBar backgroundColor="blue">
 *     <m:MaterialBreadcrumb text="First" href="#first"/>
 *     <m:MaterialBreadcrumb text="Second" href="#second"/>
 *     <m:MaterialBreadcrumb text="Third" href="#third"/>
 * </m:MaterialNavBar>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#breadcrumbs">Material BreadCrumb</a>
 */
//@formatter:on
public class MaterialBreadcrumb extends AbstractButton {

    public MaterialBreadcrumb() {
        setStyleName("breadcrumb");
    }

    @Override
    protected Element createElement() {
        return Document.get().createAnchorElement();
    }

}

