package pl.edy.wat.wcy.dynamicgui.form.models;

import pl.edy.wat.wcy.dynamicgui.BaseDynamicConfig;
import pl.edy.wat.wcy.dynamicgui.enums.LabelPosition;
import pl.edy.wat.wcy.dynamicgui.form.components.formgroup.DynamicFormGroupRenderer;
import pl.edy.wat.wcy.dynamicgui.form.components.formgroup.TabsGroupsFormGroupRenderer;
import pl.edy.wat.wcy.dynamicgui.form.components.formgroup.WithoutGroupsFormGroupRenderer;
import pl.edy.wat.wcy.dynamicgui.form.services.*;
import pl.edy.wat.wcy.dynamicgui.form.services.defaults.*;
import pl.edy.wat.wcy.dynamicgui.utils.DynamicFormUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormConfig extends BaseDynamicConfig {


    private boolean showPlaceholders = true;
    private LabelPosition labelPosition = LabelPosition.LEFT;
    private String labelPostfix = " :";

    private DynamicFormGroupRenderer formGroupsRenderer = new WithoutGroupsFormGroupRenderer();


    private DynamicFormComponentValueProxy componentValueProxy = new DefaultDynamicFormComponentValueProxy();
    private DynamicFormFieldProvider formFieldProvider = new DefaultDynamicFormFieldProvider();
    private DynamicFormValidator validator = new DefaultDynamicFormValidator();
    private DynamicFormLabelProvider labelProvider = new DefaultDynamicFormLabelProvider();
    private Map<String, SelectOptionProvider> selectProviders = new HashMap<>();
    private List<FormGroupConfig> formGroups = new LinkedList<>();


    public void addSelectProvider(String fieldName, SelectOptionProvider provider) {
        selectProviders.put(DynamicFormUtils.normalizeFieldName(fieldName), provider);
    }
    public void addFormGroup(String groupName, String [] groupFields) {
        formGroups.add(new FormGroupConfig(groupName, groupFields));
    }
    public SelectOptionProvider getSelectProviderForField(String fieldName) {
        return selectProviders.getOrDefault(DynamicFormUtils.normalizeFieldName(fieldName), null);
    }
}
