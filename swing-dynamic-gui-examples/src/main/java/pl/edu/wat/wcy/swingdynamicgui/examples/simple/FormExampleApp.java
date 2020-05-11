package pl.edu.wat.wcy.swingdynamicgui.examples.simple;

import pl.edu.wat.wcy.swingdynamicgui.DynamicContextBuilder;
import pl.edu.wat.wcy.swingdynamicgui.form.components.DynamicFormDialog;
import pl.edu.wat.wcy.swingdynamicgui.form.components.formgroup.FieldsetFormGroupRenderer;
import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormContext;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormConfig;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.City;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Person;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Sex;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FormExampleApp {
    public static void main(String[] args) {

        // init data outside UI thread
        final List<City> cities = Arrays.asList(new City[]{
                new City("Warsaw"),
                new City("London"),
                new City("London"),
        });
        FormConfig formConfig = DynamicContextBuilder.getFormConfigBasedOnAnnotation(Person.class);
        formConfig.addSelectProvider("city", (object, field) -> cities);
        formConfig.setFormGroupsRenderer(new FieldsetFormGroupRenderer());

        Person example = Person.builder()
                .name("John")
                .surname("Test")
                .salary(500)
                .sex(Sex.MALE)
                .dateOfBirth(new Date())
                .city(cities.get(0))
                .favouriteColors(Arrays.asList("red"))
                .build();
        DynamicFormContext formContext = DynamicContextBuilder.getFormContextSync(
                example
                , formConfig);
        // show form in UI thread
        //SwingUtils.runInEDT(() -> new ExampleFormDialog(formContext));

        DynamicFormDialog d = new DynamicFormDialog(example, formConfig);
        d.setTitle("Personel edition");
        d.setOkListener((e) -> System.out.println("Form saved"));
        d.setVisible(true);

    }
}
