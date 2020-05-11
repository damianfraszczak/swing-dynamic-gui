package pl.edy.wat.wcy.dynamicgui.form.components.formgroup;

import pl.edy.wat.wcy.dynamicgui.form.models.FormGroup;
import pl.edy.wat.wcy.dynamicgui.utils.DynamicFormUtils;

import javax.swing.*;
import java.util.List;

public class TabsGroupsFormGroupRenderer implements DynamicFormGroupRenderer {
    @Override
    public JComponent renderFormGroups(List<FormGroup> groups) {
        JTabbedPane pane = new JTabbedPane();
        groups
                .forEach(formGroup -> {
                    JPanel panel = new JPanel(DynamicFormUtils.getFormMigLayout());
                    formGroup.getGroupFields().forEach(field -> {
                        panel.add(field, DynamicFormUtils.getDynamicFormLayoutSizeParams(field.getFieldMetadata()));
                    });
                    pane.addTab(formGroup.getName(), panel);

                });
        return pane;
    }
}
