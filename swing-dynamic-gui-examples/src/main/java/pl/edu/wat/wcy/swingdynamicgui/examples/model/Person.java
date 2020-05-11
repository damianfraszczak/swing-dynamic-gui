package pl.edu.wat.wcy.swingdynamicgui.examples.model;

import lombok.Builder;
import lombok.Data;
import pl.edy.wat.wcy.dynamicgui.annotations.DynamicFormField;
import pl.edy.wat.wcy.dynamicgui.enums.FieldInputType;
import pl.edy.wat.wcy.dynamicgui.enums.FieldSize;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;



@Data
@Builder
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