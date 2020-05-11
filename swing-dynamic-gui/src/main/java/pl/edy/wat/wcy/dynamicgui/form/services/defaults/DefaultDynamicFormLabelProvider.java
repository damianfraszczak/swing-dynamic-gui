package pl.edy.wat.wcy.dynamicgui.form.services.defaults;

import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormField;
import pl.edy.wat.wcy.dynamicgui.form.services.DynamicFormLabelProvider;
import pl.edy.wat.wcy.dynamicgui.utils.DynamicFormUtils;
import pl.edy.wat.wcy.dynamicgui.utils.ParsingUtils;

public class DefaultDynamicFormLabelProvider implements DynamicFormLabelProvider {
    @Override
    public String getFieldPlaceholder(Class objectCls, String fieldName, DynamicFormField formField) {
        if(!ParsingUtils.isEmptyString(formField.fieldPlaceholder())){
            return formField.fieldPlaceholder();
        }
        return DynamicFormUtils.fieldNameToUserFieldName(fieldName);
    }

    @Override
    public String getFieldLabel(Class objectCls, String fieldName, DynamicFormField formField) {
        if(!ParsingUtils.isEmptyString(formField.fieldLabel())){
            return formField.fieldLabel();
        }
        return DynamicFormUtils.fieldNameToUserFieldName(fieldName);
    }
}
