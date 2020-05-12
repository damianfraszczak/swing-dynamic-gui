package pl.edu.wat.wcy.swingdynamicgui.form.models;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class ValidationResult {
    @Getter
    private List<ValidationMessage> validationMessages = new LinkedList<>();

    public void addMessage(ValidationMessage validationMessage) {
        validationMessages.add(validationMessage);
    }

    public void addMessage(String field, String message) {
        addMessage(new ValidationMessage(field, message));
    }

    public boolean isValid() {
        return validationMessages.isEmpty();
    }
}
