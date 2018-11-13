package gwt.material.design.client.base;

import gwt.material.design.client.base.density.DisplayDensity;

/**
 * An interface that defines the density of a widget.
 *
 * @author kevzlou7979@gmail.com
 * @see <a href="https://material.io/design/layout/density.html">Docuemntation</a>
 * @see DisplayDensity
 */
public interface HasDensity {

    /**
     * Set the density of widget
     */
    void setDensity(DisplayDensity density);

    /**
     * Get the density of widget. By Default {@link DisplayDensity#DEFAULT}
     */
    DisplayDensity getDensity();
}
