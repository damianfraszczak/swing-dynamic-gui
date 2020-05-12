package pl.edu.wat.wcy.swingdynamicgui.form.components.formgroup;

import pl.edu.wat.wcy.swingdynamicgui.form.models.FormGroup;
import pl.edu.wat.wcy.swingdynamicgui.utils.DynamicFormUtils;

import javax.swing.*;
import java.util.List;

public class WithoutGroupsFormGroupRenderer implements DynamicFormGroupRenderer {
    @Override
    public JComponent renderFormGroups(List<FormGroup> groups) {
        JPanel panel = new JPanel(DynamicFormUtils.getFormMigLayout());
        groups
                .forEach(formGroup -> {
                    formGroup.getGroupFields().forEach(field -> {
                        panel.add(field, DynamicFormUtils.getDynamicFormLayoutSizeParams(field.getFieldMetadata()));
                    });
                });

        return new JScrollPane(panel);
    }
}
