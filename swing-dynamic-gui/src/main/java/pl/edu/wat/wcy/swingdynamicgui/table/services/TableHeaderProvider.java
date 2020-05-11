package pl.edu.wat.wcy.swingdynamicgui.table.services;

import pl.edu.wat.wcy.swingdynamicgui.annotations.DynamicFormField;

public interface TableHeaderProvider {
    String getFieldTableHeader(Class objectCls, String fieldName, DynamicFormField formField);
}
