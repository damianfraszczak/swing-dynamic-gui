package pl.edy.wat.wcy.dynamicgui.form.services.defaults;

import pl.edy.wat.wcy.dynamicgui.form.models.ValidationResult;
import pl.edy.wat.wcy.dynamicgui.form.services.DynamicFormValidator;

import javax.validation.Validation;
import javax.validation.Validator;

public class DefaultDynamicFormValidator implements DynamicFormValidator {
    private final Validator validator;

    public DefaultDynamicFormValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public ValidationResult validate(Object object) {

        ValidationResult result = new ValidationResult();
        validator.validate(object).forEach(validationError -> {
            result.addMessage(validationError.getPropertyPath().toString(), validationError.getMessage());
        });
        return result;
    }
}
