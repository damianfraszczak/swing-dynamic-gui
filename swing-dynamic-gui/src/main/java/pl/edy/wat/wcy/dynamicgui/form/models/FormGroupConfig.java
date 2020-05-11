package pl.edy.wat.wcy.dynamicgui.form.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormGroupConfig {
    private String name;
    private String[] fields;
}
