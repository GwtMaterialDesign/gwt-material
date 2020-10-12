package gwt.material.design.client.base;

public interface HasSingleValue<T> {

    void setSingleValue(T value);

    void setSingleValue(T value, boolean fireEvents);

    T getSingleValue();
}
