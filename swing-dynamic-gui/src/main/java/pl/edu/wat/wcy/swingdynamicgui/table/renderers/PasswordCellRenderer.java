package pl.edu.wat.wcy.swingdynamicgui.table.renderers;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class PasswordCellRenderer extends JPasswordField implements
        TableCellRenderer {

    public PasswordCellRenderer() {
        super();
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public Component getTableCellRendererComponent(JTable arg0, Object arg1,
                                                   boolean arg2, boolean arg3, int arg4, int arg5) {
        setText(arg1 != null ? arg1.toString() : "");
        return this;
    }
}
