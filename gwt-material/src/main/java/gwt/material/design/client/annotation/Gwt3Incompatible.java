package gwt.material.design.client.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Documented
public @interface Gwt3Incompatible {
    /**
     * Describes why the annotated element is incompatible with GWT 3. Since this is generally due to a
     * dependence on a type/method which GWT doesn't support, it is sufficient to simply reference the
     * unsupported type/method.
     */
    String value() default "";
}