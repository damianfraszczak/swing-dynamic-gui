package pl.edy.wat.wcy.dynamicgui.form.services;

import pl.edy.wat.wcy.dynamicgui.form.models.ValidationResult;

public interface DynamicFormValidator {
    ValidationResult validate(Object object);
}
