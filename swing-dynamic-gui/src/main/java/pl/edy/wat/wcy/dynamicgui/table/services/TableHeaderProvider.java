package pl.edy.wat.wcy.dynamicgui.table.services;

import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormField;

public interface TableHeaderProvider {
    String getFieldTableHeader(Class objectCls, String fieldName, DynamicFormField formField);
}
