package pl.edu.wat.wcy.swingdynamicgui.form.services;

import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormFieldMetadata;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormConfig;

import java.util.List;

public interface DynamicFormMetadataLoader {
    public List<DynamicFormFieldMetadata> loadMetadata(FormConfig formConfig, Object object);
}
