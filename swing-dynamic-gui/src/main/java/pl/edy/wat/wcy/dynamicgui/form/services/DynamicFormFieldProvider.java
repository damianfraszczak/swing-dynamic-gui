package pl.edy.wat.wcy.dynamicgui.form.services;

import pl.edy.wat.wcy.dynamicgui.form.models.DynamicFormFieldMetadata;
import pl.edy.wat.wcy.dynamicgui.form.models.FormConfig;

import javax.swing.*;

public interface DynamicFormFieldProvider {
    public JComponent getFormField(FormConfig config, DynamicFormFieldMetadata metadata);
}
