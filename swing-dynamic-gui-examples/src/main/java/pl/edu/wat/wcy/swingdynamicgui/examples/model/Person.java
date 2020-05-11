package pl.edu.wat.wcy.swingdynamicgui.examples.model;

import lombok.Builder;
import lombok.Data;
import pl.edu.wat.wcy.swingdynamicgui.annotations.DynamicFormConfig;
import pl.edu.wat.wcy.swingdynamicgui.annotations.DynamicFormField;
import pl.edu.wat.wcy.swingdynamicgui.annotations.DynamicFormGroup;
import pl.edu.wat.wcy.swingdynamicgui.enums.FieldInputType;
import pl.edu.wat.wcy.swingdynamicgui.enums.FieldSize;
import pl.edu.wat.wcy.swingdynamicgui.enums.LabelPosition;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
@Builder
@DynamicFormConfig(
        labelPosition = LabelPosition.TOP,
        formGroups = {
                @DynamicFormGroup(name = "Personal information", size = FieldSize.TWO, fields = {"name", "surname", "sex", "dateOfBirth", "city"}),
                @DynamicFormGroup(name = "Additional info",size = FieldSize.TWO, fields = {"salary", "favouriteColors"}),
        }
)
public class Person {
    @DynamicFormField(fieldSize = FieldSize.TWO)
    @NotEmpty
    private String name;
    @DynamicFormField(fieldSize = FieldSize.TWO)
    @NotEmpty
    private String surname;
    @DynamicFormField
    @NotNull
    private Sex sex;
    @DynamicFormField(fieldInputType = FieldInputType.DATE,  displayInTable = false)
    @NotNull
    private Date dateOfBirth;
    @DynamicFormField(displayInTable = false)
    @NotNull
    private City city;
    @DynamicFormField(fieldInputType = FieldInputType.CURRENCY)
    @Min(0)
    private double salary;

    @DynamicFormField(displayInTable = false,
            fieldInputType = FieldInputType.MULTI_SELECT,
            staticOptionsAsCommaSeparatedString = "red,blue,yellow")
    @NotNull
    private List<String> favouriteColors;


    @Override
    public String toString() {
        return name + " " + surname;
    }
}