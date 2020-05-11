package pl.edy.wat.wcy.dynamicgui.form.models;

import pl.edy.wat.wcy.dynamicgui.enums.FieldInputType;
import pl.edy.wat.wcy.dynamicgui.enums.FieldSize;
import lombok.Builder;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

@Data
@Builder
public class DynamicFormFieldMetadata implements Comparable<DynamicFormFieldMetadata> {
    private Field rawField;
    private String fieldName;
    private FieldInputType type;
    private String fieldLabel;
    private String fieldPlaceholder;
    private FieldSize size;
    private Collection<Object> options;
    private int fieldOrder;
    private boolean display;


    @Override
    public int compareTo(DynamicFormFieldMetadata o) {
        return Integer.compare(fieldOrder, o.getFieldOrder());
    }

    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        return rawField.getAnnotation(cls);
    }
}
