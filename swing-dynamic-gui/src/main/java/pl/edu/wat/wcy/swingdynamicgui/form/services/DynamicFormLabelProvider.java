package pl.edu.wat.wcy.swingdynamicgui.form.services;

import pl.edu.wat.wcy.swingdynamicgui.annotations.DynamicFormField;

public interface DynamicFormLabelProvider {
    String getFieldPlaceholder(Class objectCls, String fieldName, DynamicFormField formField);
    String getFieldLabel(Class objectCls, String fieldName, DynamicFormField formField);

}
