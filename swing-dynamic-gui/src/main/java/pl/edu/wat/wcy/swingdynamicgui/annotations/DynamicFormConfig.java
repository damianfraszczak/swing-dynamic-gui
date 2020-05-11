package pl.edu.wat.wcy.swingdynamicgui.annotations;

import pl.edu.wat.wcy.swingdynamicgui.enums.LabelPosition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface DynamicFormConfig {
    String dateFormatString() default "dd/MM/yyyy";

    String timeFormatString() default "HH:mm";

    String dateTimeFormatString() default "dd/MM/yyyy HH:mm";

    boolean showPlaceholders() default true;

    LabelPosition labelPosition() default LabelPosition.LEFT;

    String labelPostfix() default " :";

    DynamicFormGroup[] formGroups();
}
