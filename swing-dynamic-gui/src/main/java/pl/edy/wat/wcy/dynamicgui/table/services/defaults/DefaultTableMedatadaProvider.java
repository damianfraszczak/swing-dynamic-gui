package pl.edy.wat.wcy.dynamicgui.table.services.defaults;

import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormField;
import pl.edy.wat.wcy.dynamicgui.enums.FieldInputType;
import pl.edy.wat.wcy.dynamicgui.table.models.DynamicTableFieldMetadata;
import pl.edy.wat.wcy.dynamicgui.table.models.TableConfig;
import pl.edy.wat.wcy.dynamicgui.table.services.TableMetadataProvider;
import pl.edy.wat.wcy.dynamicgui.utils.DynamicFormUtils;
import pl.edy.wat.wcy.dynamicgui.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultTableMedatadaProvider implements TableMetadataProvider {
    @Override
    public List<DynamicTableFieldMetadata> loadMetadata(TableConfig tableConfig) {
        List<Field> dynamicFormFields = ReflectionUtils.getAllFieldsWithAnnotation(tableConfig.getDataType(), DynamicFormField.class);
        return dynamicFormFields.stream()
                .map(x -> createMetadataForField(x, tableConfig))
                .sorted()
                .collect(Collectors.toList());
    }

    private DynamicTableFieldMetadata createMetadataForField(Field field, TableConfig tableConfig) {
        DynamicFormField formField = field.getAnnotation(DynamicFormField.class);
        return DynamicTableFieldMetadata.builder()
                .rawField(field)
                .display(formField.displayInTable())
                .fieldName(field.getName())
                .tableOrder(formField.tableFieldOrder())
                .tableHeader(tableConfig.getHeaderProvider().getFieldTableHeader(tableConfig.getDataType(), field.getName(), formField))
                .type(getFieldType(field, formField))
                .build();
    }

    protected FieldInputType getFieldType(Field field, DynamicFormField formField) {
        return DynamicFormUtils.getFieldType(field,formField);
    }
}
