package pl.edu.wat.wcy.swingdynamicgui.form.services;

import pl.edu.wat.wcy.swingdynamicgui.form.models.ValidationResult;

public interface DynamicFormValidator {
    ValidationResult validate(Object object);
}
