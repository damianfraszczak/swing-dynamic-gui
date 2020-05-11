package pl.edy.wat.wcy.dynamicgui.form.services;

import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormField;

public interface DynamicFormLabelProvider {
    String getFieldPlaceholder(Class objectCls, String fieldName, DynamicFormField formField);
    String getFieldLabel(Class objectCls, String fieldName, DynamicFormField formField);

}
