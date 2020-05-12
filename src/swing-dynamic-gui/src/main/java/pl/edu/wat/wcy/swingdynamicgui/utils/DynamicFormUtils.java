package pl.edu.wat.wcy.swingdynamicgui.utils;


import net.miginfocom.swing.MigLayout;
import pl.edu.wat.wcy.swingdynamicgui.annotations.DynamicFormField;
import pl.edu.wat.wcy.swingdynamicgui.enums.FieldInputType;
import pl.edu.wat.wcy.swingdynamicgui.enums.FieldSize;
import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormFieldMetadata;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormConfig;
import pl.edu.wat.wcy.swingdynamicgui.form.services.SelectOptionProvider;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

public class DynamicFormUtils {

    public static LayoutManager getFormMigLayout() {
        return new MigLayout(
                "wrap 12", // Layout Constraints
                "[grow]", // Column constraints
                "[shrink 0]");
    }

    public static String normalizeFieldName(String fieldName) {
        return fieldName.toLowerCase();
    }

    public static String getDynamicFormLayoutSizeParams(DynamicFormFieldMetadata field) {
        return getDynamicFormLayoutSizeParams(field.getSize());
    }
    public static String getDynamicFormLayoutSizeParams(FieldSize size) {
        return "span " + size.getSize() + ", grow";
    }
    public static Collection<Object> getFieldOptions(FormConfig formConfig, Object object, DynamicFormField formField, Field field) {
        if (ReflectionUtils.isEnum(field.getType())) {
            return Arrays.asList(field.getType().getEnumConstants());
        }
        String[] staticOptions = ParsingUtils.getSeparatedOptions(formField.staticOptionsAsCommaSeparatedString());
        if (staticOptions.length > 0) {
            return Arrays.asList(staticOptions);
        }
        SelectOptionProvider provider = formConfig.getSelectProviderForField(field.getName());
        if (provider != null) {
            return provider.getList(object, field.getName());
        }
        return new LinkedList<>();
    }

    public static FieldInputType getFieldTypeBasedOnClass(Class<?> type, DynamicFormField formField) {
        if (ReflectionUtils.isEnum(type)) {
            return FieldInputType.COMBOBOX;
        }
        if (ParsingUtils.getSeparatedOptions(formField.staticOptionsAsCommaSeparatedString()).length > 0) {
            return FieldInputType.COMBOBOX;
        }
        if (ReflectionUtils.isUserDefinedClass(type)) {
            return FieldInputType.COMBOBOX;
        }
        if (ReflectionUtils.isIntAndDerivative(type)) {
            return FieldInputType.INTEGER;
        }
        if (ReflectionUtils.isFloatAndDerivative(type)) {
            return FieldInputType.DOUBLE;
        }
        if (ReflectionUtils.isBoolean(type)) {
            return FieldInputType.CHECKBOX;
        }

        if (ReflectionUtils.isDate(type)) {
            return FieldInputType.DATE_TIME;
        }
        return FieldInputType.DEFAULT;
    }

    public static FieldInputType getFieldType(Field field, DynamicFormField formField) {
        if (formField.fieldInputType() == FieldInputType.DEFAULT) {
            return getFieldTypeBasedOnClass(field.getType(), formField);
        } else {
            return formField.fieldInputType();
        }
    }

    public static String fieldNameToUserFieldName(String s) {
        return titleCaseConversion(splitCamelCase(s));
    }

    public static String titleCaseConversion(String inputString) {
        if (ParsingUtils.isEmptyString(inputString)) {
            return "";
        }

        if (inputString.length() == 1) {
            return inputString.toUpperCase();
        }

        StringBuffer resultPlaceHolder = new StringBuffer(inputString.length());
        inputString = inputString.replace("_", " ");
        Stream.of(inputString.split(" ")).forEach(stringPart ->
        {
            if (stringPart.length() > 1)
                resultPlaceHolder.append(stringPart.substring(0, 1)
                        .toUpperCase())
                        .append(stringPart.substring(1)
                                .toLowerCase());
            else
                resultPlaceHolder.append(stringPart.toUpperCase());

            resultPlaceHolder.append(" ");
        });
        return resultPlaceHolder.toString().trim();
    }

    public static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

}
