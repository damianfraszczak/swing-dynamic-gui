package pl.edu.wat.wcy.swingdynamicgui.form.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.wat.wcy.swingdynamicgui.enums.FieldSize;
import pl.edu.wat.wcy.swingdynamicgui.form.components.DynamicFieldForm;

import java.util.List;

@Data
@AllArgsConstructor
public class FormGroup {
    private String name;
    private FieldSize size;
    private List<DynamicFieldForm> groupFields;
}
