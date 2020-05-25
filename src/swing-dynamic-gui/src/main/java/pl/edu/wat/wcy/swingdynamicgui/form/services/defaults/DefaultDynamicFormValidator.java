package pl.edu.wat.wcy.swingdynamicgui.form.services.defaults;

import pl.edu.wat.wcy.swingdynamicgui.form.models.ValidationResult;
import pl.edu.wat.wcy.swingdynamicgui.form.services.DynamicFormValidator;

import javax.validation.Validation;
import javax.validation.Validator;

public class DefaultDynamicFormValidator implements DynamicFormValidator {
    private transient final Validator validator;

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
