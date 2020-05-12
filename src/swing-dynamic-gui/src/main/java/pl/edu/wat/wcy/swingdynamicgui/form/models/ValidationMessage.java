package pl.edu.wat.wcy.swingdynamicgui.form.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ValidationMessage {
    private String fieldName;
    private String errorMessage;

}
