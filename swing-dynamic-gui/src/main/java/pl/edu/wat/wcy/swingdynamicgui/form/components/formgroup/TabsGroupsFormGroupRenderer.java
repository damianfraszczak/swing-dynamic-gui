package pl.edu.wat.wcy.swingdynamicgui.form.components.formgroup;

import pl.edu.wat.wcy.swingdynamicgui.form.models.FormGroup;
import pl.edu.wat.wcy.swingdynamicgui.utils.DynamicFormUtils;

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
