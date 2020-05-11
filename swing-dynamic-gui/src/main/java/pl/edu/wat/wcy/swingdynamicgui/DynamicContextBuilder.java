package pl.edu.wat.wcy.swingdynamicgui;

import pl.edu.wat.wcy.swingdynamicgui.annotations.DynamicFormConfig;
import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormContext;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormConfig;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormGroupConfig;
import pl.edu.wat.wcy.swingdynamicgui.form.services.DynamicFormMetadataLoader;
import pl.edu.wat.wcy.swingdynamicgui.form.services.defaults.DefaultDynamicFormMetadataLoader;

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
            formConfig.setFormGroups(Arrays.stream(config.formGroups()).map(x-> new FormGroupConfig(x.name(),x.size(), x.fields())).collect(Collectors.toList()));
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
