package pl.edu.wat.wcy.swingdynamicgui.table.renderers;

import pl.edu.wat.wcy.swingdynamicgui.utils.ReflectionUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Collection;
import java.util.stream.Collectors;

public class CollectionRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        Collection c = ReflectionUtils.getObjectAsCollection(value);
        String result = (String) c.stream().map(Object::toString).collect(Collectors.joining(","));
        setText(result);
        return this;
    }
}
