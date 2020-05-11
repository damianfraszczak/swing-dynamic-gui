package pl.edu.wat.wcy.swingdynamicgui.form.components.formgroup;

import pl.edu.wat.wcy.swingdynamicgui.form.models.FormGroup;

import javax.swing.*;
import java.util.List;

public interface DynamicFormGroupRenderer {
    public JComponent renderFormGroups(List<FormGroup> groups);
}
