package pl.edy.wat.wcy.dynamicgui.form.services.defaults;

import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormField;
import pl.edy.wat.wcy.dynamicgui.enums.FieldInputType;
import pl.edy.wat.wcy.dynamicgui.form.models.DynamicFormFieldMetadata;
import pl.edy.wat.wcy.dynamicgui.form.models.FormConfig;
import pl.edy.wat.wcy.dynamicgui.form.services.DynamicFormMetadataLoader;
import pl.edy.wat.wcy.dynamicgui.utils.DynamicFormUtils;
import pl.edy.wat.wcy.dynamicgui.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultDynamicFormMetadataLoader implements DynamicFormMetadataLoader {
    @Override
    public List<DynamicFormFieldMetadata> loadMetadata(FormConfig formConfig, Object object) {
        List<Field> dynamicFormFields = ReflectionUtils.getAllFieldsWithAnnotation(object.getClass(), DynamicFormField.class);
        return dynamicFormFields.stream()
                .map(x -> createMetadataForField(formConfig, object, x))
                .sorted()
                .collect(Collectors.toList());
    }

    protected DynamicFormFieldMetadata createMetadataForField(FormConfig formConfig, Object object, Field field) {
        DynamicFormField formField = field.getAnnotation(DynamicFormField.class);
        Class objClass = object.getClass();
        return DynamicFormFieldMetadata.builder()
                .rawField(field)
                .display(formField.displayInForm())
                .fieldName(field.getName())
                .fieldOrder(formField.formFieldOrder())
                .fieldLabel(formConfig.getLabelProvider().getFieldLabel(objClass, field.getName(), formField))
                .size(formField.fieldSize())
                .type(getFieldType(field, formField))
                .fieldPlaceholder(formConfig.getLabelProvider().getFieldPlaceholder(objClass, field.getName(), formField))
                .options(getFieldOptions(formConfig, object, formField, field))
                .build();
    }


    protected Collection<Object> getFieldOptions(FormConfig formConfig,
                                                 Object object,
                                                 DynamicFormField formField,
                                                 Field field) {
       return DynamicFormUtils.getFieldOptions(formConfig, object, formField, field);
    }

    protected FieldInputType getFieldType(Field field, DynamicFormField formField) {
        return DynamicFormUtils.getFieldType(field, formField);
    }
}
