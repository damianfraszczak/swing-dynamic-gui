package pl.edy.wat.wcy.dynamicgui.table.models;

import pl.edy.wat.wcy.dynamicgui.enums.FieldInputType;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Field;

@Builder
@Data
public class DynamicTableFieldMetadata implements Comparable<DynamicTableFieldMetadata> {
    private Field rawField;
    private String fieldName;
    private FieldInputType type;
    private String tableHeader;
    private int tableOrder;
    private boolean display;

    @Override
    public int compareTo(DynamicTableFieldMetadata o) {
        return Integer.compare(tableOrder, o.getTableOrder());
    }
}
