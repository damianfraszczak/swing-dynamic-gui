package pl.edy.wat.wcy.dynamicgui.annotations;

import pl.edy.wat.wcy.dynamicgui.enums.FieldInputType;
import pl.edy.wat.wcy.dynamicgui.enums.FieldSize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface DynamicFormField {
    FieldInputType fieldInputType() default FieldInputType.DEFAULT;
    FieldSize fieldSize() default FieldSize.ONE;
    boolean displayInForm() default true;
    int formFieldOrder() default 0;
    String staticOptionsAsCommaSeparatedString() default "";
    String fieldPlaceholder() default "";
    String fieldLabel() default "";

    boolean displayInTable() default true;
    String tableHeader() default "";
    int tableFieldOrder() default 0;
}
