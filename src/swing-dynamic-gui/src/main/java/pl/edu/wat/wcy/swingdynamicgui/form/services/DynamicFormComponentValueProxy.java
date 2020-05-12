package pl.edu.wat.wcy.swingdynamicgui.form.services;

import javax.swing.*;

public interface DynamicFormComponentValueProxy {
    public void setValue(JComponent component, Object value);
    public Object getValue(JComponent component);
}
