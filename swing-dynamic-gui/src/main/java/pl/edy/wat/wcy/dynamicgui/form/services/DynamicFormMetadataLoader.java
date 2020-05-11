package pl.edy.wat.wcy.dynamicgui.form.services;

import pl.edy.wat.wcy.dynamicgui.form.models.DynamicFormFieldMetadata;
import pl.edy.wat.wcy.dynamicgui.form.models.FormConfig;

import java.util.List;

public interface DynamicFormMetadataLoader {
    public List<DynamicFormFieldMetadata> loadMetadata(FormConfig formConfig, Object object);
}
