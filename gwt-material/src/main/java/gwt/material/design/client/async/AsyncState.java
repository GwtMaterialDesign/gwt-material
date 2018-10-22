package gwt.material.design.client.async;

public class AsyncState<T> {

    private T value;

    public AsyncState() {}

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
