package pl.edu.wat.wcy.swingdynamicgui.table.renderers;

import lombok.AllArgsConstructor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.Format;

@AllArgsConstructor
public class FormatRenderer extends DefaultTableCellRenderer {

    private Format format;


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);
        label.setText(format.format(value));
        return label;
    }
}
