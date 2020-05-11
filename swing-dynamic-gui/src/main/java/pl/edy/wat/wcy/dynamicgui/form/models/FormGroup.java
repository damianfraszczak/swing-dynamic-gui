package pl.edy.wat.wcy.dynamicgui.form.models;

import pl.edy.wat.wcy.dynamicgui.form.components.DynamicFieldForm;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FormGroup {
    private String name;
    private List<DynamicFieldForm> groupFields;
}
