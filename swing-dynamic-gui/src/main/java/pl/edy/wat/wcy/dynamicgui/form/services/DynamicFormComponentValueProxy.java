package pl.edy.wat.wcy.dynamicgui.form.services;

import javax.swing.*;

public interface DynamicFormComponentValueProxy {
    public void setValue(JComponent component, Object value);
    public Object getValue(JComponent component);
}
