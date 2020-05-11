package pl.edu.wat.wcy.swingdynamicgui.form.services.defaults;

import pl.edu.wat.wcy.swingdynamicgui.annotations.DynamicFormField;
import pl.edu.wat.wcy.swingdynamicgui.form.services.DynamicFormLabelProvider;
import pl.edu.wat.wcy.swingdynamicgui.utils.DynamicFormUtils;
import pl.edu.wat.wcy.swingdynamicgui.utils.ParsingUtils;

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
