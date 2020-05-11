package pl.edy.wat.wcy.dynamicgui.form.models;

import pl.edy.wat.wcy.dynamicgui.FormConstants;
import pl.edy.wat.wcy.dynamicgui.form.components.DynamicFieldForm;
import pl.edy.wat.wcy.dynamicgui.form.services.DynamicFormComponentValueProxy;
import pl.edy.wat.wcy.dynamicgui.form.services.DynamicFormValidator;
import pl.edy.wat.wcy.dynamicgui.form.services.defaults.DefaultDynamicFormValidator;
import pl.edy.wat.wcy.dynamicgui.utils.DynamicFormUtils;
import pl.edy.wat.wcy.dynamicgui.utils.ReflectionUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DynamicFormContext {
    private static final Logger LOGGER = Logger.getLogger(DynamicFormContext.class.getName());
    @Getter
    private Object object;
    @Getter
    private List<DynamicFormFieldMetadata> metadata;
    @Getter
    private FormConfig formConfig;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private int maxLabelSize = -1;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, DynamicFieldForm> formComponentsMap = new HashMap();

    public DynamicFormContext(Object object, List<DynamicFormFieldMetadata> metadata, FormConfig formConfig) {
        this.object = object;
        this.metadata = metadata;
        this.formConfig = formConfig;
    }

    public int getLabelSize() {
        if (maxLabelSize == -1) {
            maxLabelSize = metadata.stream().map(x -> x.getFieldLabel().length()).max(Integer::compareTo).orElse(0);
        }
        return maxLabelSize * FormConstants.LABEL_CHARACTER_SIZE;
    }

    public List<FormGroup> getAllFormGroups() {
        if (formConfig.getFormGroups().isEmpty()) {
            return Arrays.asList(new FormGroup("", metadata
                    .stream().map(fieldMetadata -> getDynamicFieldForm(fieldMetadata))
                    .collect(Collectors.toList())));
        } else {
            return formConfig.getFormGroups().stream().map(x -> new FormGroup(x.getName(),
                    metadata.stream()
                            .filter(y -> containsCaseInsensitive(y.getFieldName(), x.getFields()))
                            .map(fieldMetadata -> getDynamicFieldForm(fieldMetadata))
                            .collect(Collectors.toList())
            )).collect(Collectors.toList());
        }
    }

    public JComponent getFormField(DynamicFormFieldMetadata fieldMetadata) {
        return formConfig.getFormFieldProvider().getFormField(formConfig, fieldMetadata);
    }

    public DynamicFormComponentValueProxy getComponentValueProxy() {
        return formConfig.getComponentValueProxy();
    }

    private DynamicFieldForm getDynamicFieldForm(DynamicFormFieldMetadata fieldMetadata) {
        DynamicFieldForm formField = new DynamicFieldForm(this, fieldMetadata);
        formComponentsMap.put(DynamicFormUtils.normalizeFieldName(fieldMetadata.getFieldName()), formField);
        return formField;
    }

    private DynamicFieldForm getFieldFormForFieldName(String fieldName) {
        return formComponentsMap.get(DynamicFormUtils.normalizeFieldName(fieldName));
    }


    private DynamicFormValidator getValidator() {
        DynamicFormValidator validator = getFormConfig().getValidator();
        if (validator == null) {
            validator = new DefaultDynamicFormValidator();
        }
        return validator;
    }

    public ValidationResult validateForm() {
        copyValuesFromComponentsToObject();
        clearValidationResult();
        ValidationResult result = getValidator().validate(getObject());

        result.getValidationMessages().forEach(validationError -> {
            DynamicFieldForm dff = getFieldFormForFieldName(validationError.getFieldName());
            if (dff != null) {
                dff.setValidationError(validationError.getErrorMessage());
            } else {
                LOGGER.log(Level.WARNING, " no rawField definoied for " + validationError.getFieldName());
            }
        });
        return result;
    }

    private void clearValidationResult() {
        formComponentsMap.values().stream().forEach(x -> x.setValidationError(""));
    }

    public void copyValuesFromObjectsToComponents() {
        getMetadata().forEach(fieldMetadata -> {
            DynamicFieldForm dynamicFieldForm = getFieldFormForFieldName(fieldMetadata.getFieldName());
            // not all fields need to be visible
            if (dynamicFieldForm != null) {
                getComponentValueProxy().setValue(
                        dynamicFieldForm,
                        ReflectionUtils.getValue(getObject(),
                                fieldMetadata.getFieldName()));
            }


        });
    }

    public void copyValuesFromComponentsToObject() {
        getMetadata().forEach(fieldMetadata -> {
            DynamicFieldForm dynamicFieldForm = getFieldFormForFieldName(fieldMetadata.getFieldName());
            // not all fields need to be visible
            if (dynamicFieldForm != null) {
                Object componentValue = getComponentValueProxy().getValue(dynamicFieldForm);
                ReflectionUtils.setValue(getObject(),
                        fieldMetadata.getFieldName(),
                        componentValue);
            }
        });
    }

    private boolean containsCaseInsensitive(String s, String[] array) {
        return Arrays.stream(array).anyMatch(x -> x.equalsIgnoreCase(s));
    }
}
