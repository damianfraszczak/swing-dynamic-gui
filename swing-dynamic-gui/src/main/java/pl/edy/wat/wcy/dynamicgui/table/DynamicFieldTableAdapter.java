package pl.edy.wat.wcy.dynamicgui.table;

import pl.edy.wat.wcy.dynamicgui.table.models.DynamicTableContext;
import pl.edy.wat.wcy.dynamicgui.utils.ReflectionUtils;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DynamicFieldTableAdapter extends AbstractTableModel {


    private DynamicTableContext tableContext;
    private List<Object> data;


    public DynamicFieldTableAdapter(DynamicTableContext tableContext) {
        this.tableContext = tableContext;
    }

    public void loadData(List<Object> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return tableContext.getColumns().size();
    }

    @Override
    public String getColumnName(int column) {
        return tableContext.getColumns().get(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tableContext.getColumnsClass().get(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object valObject = data.get(rowIndex);
        return ReflectionUtils.getValue(valObject,
                tableContext.getVisibleFields().get(columnIndex).getRawField());
    }
}
