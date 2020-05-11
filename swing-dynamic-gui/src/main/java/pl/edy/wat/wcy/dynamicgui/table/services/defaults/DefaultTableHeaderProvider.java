package pl.edy.wat.wcy.dynamicgui.table.services.defaults;

import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormField;
import pl.edy.wat.wcy.dynamicgui.table.services.TableHeaderProvider;
import pl.edy.wat.wcy.dynamicgui.utils.DynamicFormUtils;
import pl.edy.wat.wcy.dynamicgui.utils.ParsingUtils;

public class DefaultTableHeaderProvider implements TableHeaderProvider {
    @Override
    public String getFieldTableHeader(Class objectCls, String fieldName, DynamicFormField formField) {
        if(!ParsingUtils.isEmptyString(formField.tableHeader())){
            return formField.tableHeader();
        }
        return DynamicFormUtils.fieldNameToUserFieldName(fieldName);
    }
}
