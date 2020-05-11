package pl.edy.wat.wcy.dynamicgui.table.models;

import pl.edy.wat.wcy.dynamicgui.utils.DynamicFormUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.swing.table.TableCellRenderer;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DynamicTableContext {
    @NonNull
    @Getter
    private TableConfig tableConfig;
    @NonNull
    @Getter
    private List<DynamicTableFieldMetadata> metadata;
    @Getter
    private List<DynamicTableFieldMetadata> visibleFields;


    private List<String> columns;
    private List<Class> columnsClass;


    public List<String> getColumns() {
        if (columns == null) {
            initContext();
        }
        return columns;
    }

    public List<Class> getColumnsClass() {
        if (columnsClass == null) {
            initContext();
        }
        return columnsClass;
    }

    private void initContext() {
        visibleFields = metadata.stream().filter(x -> x.isDisplay()).sorted().collect(Collectors.toList());
        columns = visibleFields.stream().map(x -> x.getTableHeader()).collect(Collectors.toList());
        columnsClass = visibleFields.stream().map(x -> x.getRawField().getType()).collect(Collectors.toList());

    }

    public TableCellRenderer getTableCellRendererByField(DynamicTableFieldMetadata fieldMetadata) {
        TableCellRenderer renderer = tableConfig.getTableRenderersByFieldName().getOrDefault(DynamicFormUtils.normalizeFieldName(fieldMetadata.getFieldName()), null);
        if (renderer == null) {
            renderer = tableConfig.getTableRenderersByFieldType().getOrDefault(fieldMetadata.getType(), null);
        }
        if (renderer == null) {
            renderer = tableConfig.getTableRenderersByType().getOrDefault(fieldMetadata.getRawField().getType(), null);
        }
        return renderer;
    }


}
