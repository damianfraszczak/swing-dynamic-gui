package pl.edu.wat.wcy.swingdynamicgui.form.components.formgroup;

import pl.edu.wat.wcy.swingdynamicgui.form.models.FormGroup;
import pl.edu.wat.wcy.swingdynamicgui.utils.DynamicFormUtils;
import pl.edu.wat.wcy.swingdynamicgui.utils.SwingUtils;

import javax.swing.*;
import java.util.List;

public class FieldsetFormGroupRenderer implements DynamicFormGroupRenderer {
    @Override
    public JComponent renderFormGroups(List<FormGroup> groups) {

        JPanel pane = new JPanel(DynamicFormUtils.getFormMigLayout());
        groups
                .forEach(formGroup -> {
                    JPanel panel = new JPanel(DynamicFormUtils.getFormMigLayout());
                    formGroup.getGroupFields().forEach(field -> {
                        panel.add(field, DynamicFormUtils.getDynamicFormLayoutSizeParams(field.getFieldMetadata()));
                    });
                    pane.add(SwingUtils.createTitlePanel(panel, formGroup.getName()), DynamicFormUtils.getDynamicFormLayoutSizeParams(formGroup.getSize()));

                });
        return pane;
    }
}

