package pl.edy.wat.wcy.dynamicgui.form.components.formgroup;

import pl.edy.wat.wcy.dynamicgui.form.models.FormGroup;
import pl.edy.wat.wcy.dynamicgui.utils.DynamicFormUtils;

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
