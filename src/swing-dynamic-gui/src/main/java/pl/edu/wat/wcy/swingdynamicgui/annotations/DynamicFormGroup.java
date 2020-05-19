package pl.edu.wat.wcy.swingdynamicgui.annotations;

import pl.edu.wat.wcy.swingdynamicgui.enums.FieldSize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicFormGroup {
    String name();
    String[] fields();
    FieldSize size() default FieldSize.ONE;
}
