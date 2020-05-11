package pl.edy.wat.wcy.dynamicgui.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicFormGroup {
    String name();

    String[] fields();
}
