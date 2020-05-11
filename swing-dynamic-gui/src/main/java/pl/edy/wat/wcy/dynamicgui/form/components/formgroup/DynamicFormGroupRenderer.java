package pl.edy.wat.wcy.dynamicgui.form.components.formgroup;

import pl.edy.wat.wcy.dynamicgui.form.models.FormGroup;

import javax.swing.*;
import java.util.List;

public interface DynamicFormGroupRenderer {
    public JComponent renderFormGroups(List<FormGroup> groups);
}
