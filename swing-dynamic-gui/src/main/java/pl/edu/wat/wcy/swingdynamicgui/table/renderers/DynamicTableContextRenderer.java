package pl.edu.wat.wcy.swingdynamicgui.table.renderers;

import pl.edu.wat.wcy.swingdynamicgui.enums.FieldInputType;
import pl.edu.wat.wcy.swingdynamicgui.table.models.DynamicTableContext;
import pl.edu.wat.wcy.swingdynamicgui.table.models.DynamicTableFieldMetadata;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class DynamicTableContextRenderer implements TableCellRenderer {
    private final DynamicTableContext context;
    private DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();
    private Map<FieldInputType, TableCellRenderer> renderersByInputType = new HashMap<>();


    public DynamicTableContextRenderer(DynamicTableContext context) {
        this.context = context;
        this.initDefaultRenderers();
    }

    protected void initDefaultRenderers() {
        renderersByInputType.put(FieldInputType.PASSWORD, new PasswordCellRenderer());
        renderersByInputType.put(FieldInputType.MULTI_SELECT, new CollectionRenderer());
        renderersByInputType.put(FieldInputType.DATE, new FormatRenderer(context.getTableConfig().getDateFormat()));
        renderersByInputType.put(FieldInputType.TIME, new FormatRenderer(context.getTableConfig().getTimeFormat()));
        renderersByInputType.put(FieldInputType.DATE_TIME, new FormatRenderer(context.getTableConfig().getDateTimeFormat()));
        renderersByInputType.put(FieldInputType.INTEGER, new FormatRenderer(context.getTableConfig().getIntegerFormat()));
        renderersByInputType.put(FieldInputType.DOUBLE, new FormatRenderer(context.getTableConfig().getDoubleFormat()));
        renderersByInputType.put(FieldInputType.CURRENCY, new FormatRenderer(context.getTableConfig().getCurrencyFormat()));
    }


    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column) {
        column = table.convertColumnIndexToModel(column);
        DynamicTableFieldMetadata fieldMetadata = context.getVisibleFields().get(column);
        TableCellRenderer renderer = context.getTableCellRendererByField(fieldMetadata);
        if (renderer == null) {
            renderer = renderersByInputType.getOrDefault(fieldMetadata.getType(), null);
        }
        if (renderer == null) {
            renderer = defaultRenderer;
        }
        return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
