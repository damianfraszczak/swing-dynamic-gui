package pl.edy.wat.wcy.dynamicgui;

import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormConfig;
import pl.edy.wat.wcy.dynamicgui.form.models.DynamicFormContext;
import pl.edy.wat.wcy.dynamicgui.form.models.FormConfig;
import pl.edy.wat.wcy.dynamicgui.form.models.FormGroupConfig;
import pl.edy.wat.wcy.dynamicgui.form.services.DynamicFormMetadataLoader;
import pl.edy.wat.wcy.dynamicgui.form.services.defaults.DefaultDynamicFormMetadataLoader;
import pl.edy.wat.wcy.dynamicgui.table.models.TableConfig;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DynamicContextBuilder {

    private static DynamicFormMetadataLoader formMetadataLoader = new DefaultDynamicFormMetadataLoader();

    public static CompletableFuture<DynamicFormContext> getFormContext(Object object) {
        return getFormContext(object, getFormConfigBasedOnAnnotation(object.getClass()));
    }

    public static FormConfig getFormConfigBasedOnAnnotation(Class<?> cls) {
        DynamicFormConfig config = cls.getAnnotation(DynamicFormConfig.class);
        FormConfig formConfig = new FormConfig();
        if (config != null) {
            formConfig.setLabelPosition(config.labelPosition());
            formConfig.setShowPlaceholders(config.showPlaceholders());
            formConfig.setDateFormatString(config.dateFormatString());
            formConfig.setDateTimeFormatString(config.dateTimeFormatString());
            formConfig.setTimeFormatString(config.timeFormatString());
            formConfig.setFormGroups(Arrays.stream(config.formGroups()).map(x-> new FormGroupConfig(x.name(), x.fields())).collect(Collectors.toList()));
        }

        return formConfig;
    }



    public static CompletableFuture<DynamicFormContext> getFormContext(Object object, FormConfig formConfig) {
        return CompletableFuture.supplyAsync(() -> {
            return getFormContextSync(object, formConfig);
        });
    }

    public static DynamicFormContext getFormContextSync(Object object, FormConfig formConfig) {
        DynamicFormContext context = new DynamicFormContext(object, formMetadataLoader.loadMetadata(formConfig, object), formConfig);
        return context;
    }
}
