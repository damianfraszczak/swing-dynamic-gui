package pl.edu.wat.wcy.swingdynamicgui.form.services;

import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormFieldMetadata;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormConfig;

import javax.swing.*;

public interface DynamicFormFieldProvider {
    public JComponent getFormField(FormConfig config, DynamicFormFieldMetadata metadata);
}
