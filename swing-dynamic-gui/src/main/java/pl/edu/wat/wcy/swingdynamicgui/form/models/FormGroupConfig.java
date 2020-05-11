package pl.edu.wat.wcy.swingdynamicgui.form.models;

import lombok.Data;
import pl.edu.wat.wcy.swingdynamicgui.enums.FieldSize;

@Data

public class FormGroupConfig {
    private String name;
    private FieldSize size = FieldSize.ONE;
    private String[] fields;

    public FormGroupConfig(String name, String[] fields) {
        this.name = name;
        this.fields = fields;
    }

    public FormGroupConfig(String name, FieldSize size, String[] fields) {
        this.name = name;
        this.size = size;
        this.fields = fields;
    }
}
