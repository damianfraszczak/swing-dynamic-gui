package pl.edu.wat.wcy.swingdynamicgui.table.models;

import lombok.Builder;
import lombok.Data;
import pl.edu.wat.wcy.swingdynamicgui.enums.FieldInputType;

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
