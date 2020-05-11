package pl.edu.wat.wcy.swingdynamicgui.examples.model;

import lombok.Builder;
import lombok.Data;
import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormConfig;
import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormField;
import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormGroup;
import pl.edy.wat.wcy.dynamicgui.enums.FieldInputType;
import pl.edy.wat.wcy.dynamicgui.enums.FieldSize;
import pl.edy.wat.wcy.dynamicgui.enums.LabelPosition;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;



@Data
@Builder
@DynamicFormConfig(
        labelPosition = LabelPosition.TOP,
        formGroups = {
                @DynamicFormGroup(name = "Personal information", fields = {"name", "surname", "sex", "dateOfBirth", "city"}),
                @DynamicFormGroup(name = "Additional info", fields = {"salary", "favouriteColors"}),
        }
)
public class Person {
    @DynamicFormField(fieldSize = FieldSize.TWO)
    @NotNull
    private String name;
    @DynamicFormField(fieldSize = FieldSize.TWO)
    @NotNull
    private String surname;
    @DynamicFormField
    @NotNull
    private Sex sex;
    @DynamicFormField(fieldInputType = FieldInputType.DATE,  displayInTable = false)
    @NotNull
    private Date dateOfBirth;
    @DynamicFormField(displayInTable = false)
    private City city;
    @DynamicFormField(fieldInputType = FieldInputType.CURRENCY)
    @Min(0)
    private double salary;

    @DynamicFormField(displayInTable = false,
            fieldInputType = FieldInputType.MULTI_SELECT,
            staticOptionsAsCommaSeparatedString = "red,blue,yellow")
    private List<String> favouriteColors;


    @Override
    public String toString() {
        return name + " " + surname;
    }
}