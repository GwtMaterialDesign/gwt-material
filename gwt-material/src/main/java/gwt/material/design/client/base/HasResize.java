package gwt.material.design.client.base;

public interface HasResize {

    enum Resizable {
        NONE("none"),
        BOTH("both"),
        HORIZONTAL("horizontal"),
        VERTICAL("vertical"),
        INITIAL("initial"),
        INHERIT("inherit");

        String name;

        Resizable(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    void setResize(Resizable value);

    String getResize();
}
