package gwt.material.design.client.base;

/**
 * The contentEditable property sets or returns whether the content of an element is editable or not.
 *
 * @author kevzlou7979
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Editable_content">Documentation</a>
 */
public interface HasContentEditable {

    void setContentEditable(boolean value);

    boolean isContentEditable();
}
