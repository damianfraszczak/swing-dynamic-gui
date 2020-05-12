package pl.edu.wat.wcy.swingdynamicgui.table.services.defaults;

import pl.edu.wat.wcy.swingdynamicgui.annotations.DynamicFormField;
import pl.edu.wat.wcy.swingdynamicgui.table.services.TableHeaderProvider;
import pl.edu.wat.wcy.swingdynamicgui.utils.DynamicFormUtils;
import pl.edu.wat.wcy.swingdynamicgui.utils.ParsingUtils;

public class DefaultTableHeaderProvider implements TableHeaderProvider {
    @Override
    public String getFieldTableHeader(Class objectCls, String fieldName, DynamicFormField formField) {
        if(!ParsingUtils.isEmptyString(formField.tableHeader())){
            return formField.tableHeader();
        }
        return DynamicFormUtils.fieldNameToUserFieldName(fieldName);
    }
}
