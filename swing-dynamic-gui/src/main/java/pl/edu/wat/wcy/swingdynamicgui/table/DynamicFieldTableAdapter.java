package pl.edu.wat.wcy.swingdynamicgui.table;

import pl.edu.wat.wcy.swingdynamicgui.table.models.DynamicTableContext;
import pl.edu.wat.wcy.swingdynamicgui.utils.ReflectionUtils;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

public class DynamicFieldTableAdapter<T> extends AbstractTableModel {


    private List<T> data = new LinkedList<>();
    private DynamicTableContext tableContext;

    public DynamicFieldTableAdapter(DynamicTableContext tableContext) {
        this.tableContext = tableContext;
    }

    public void loadData(List<T> data) {

        this.data = data;
        fireTableDataChanged();
    }


    @Override
    public int getRowCount() {
        return data.size();
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

    public T getObjectAtRow(int rowIndex) {
        return data.get(rowIndex);
    }

    public void addObject(T object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void updateObject(T object) {
        fireTableDataChanged();
    }

    public void removeObject(T object) {
        data.remove(object);
        fireTableDataChanged();
    }
}
