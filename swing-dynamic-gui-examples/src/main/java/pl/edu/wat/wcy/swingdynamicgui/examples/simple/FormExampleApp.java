package pl.edu.wat.wcy.swingdynamicgui.examples.simple;

import pl.edu.wat.wcy.swingdynamicgui.examples.model.City;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Person;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Sex;
import pl.edy.wat.wcy.dynamicgui.DynamicContextBuilder;
import pl.edy.wat.wcy.dynamicgui.form.components.formgroup.TabsGroupsFormGroupRenderer;
import pl.edy.wat.wcy.dynamicgui.form.models.DynamicFormContext;
import pl.edy.wat.wcy.dynamicgui.form.models.FormConfig;
import pl.edy.wat.wcy.dynamicgui.utils.SwingUtils;

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
        formConfig.setFormGroupsRenderer(new TabsGroupsFormGroupRenderer());

        DynamicFormContext formContext = DynamicContextBuilder.getFormContextSync(
                Person.builder()
                        .name("John")
                        .surname("Test")
                        .salary(500)
                        .sex(Sex.MALE)
                        .dateOfBirth(new Date())
                        .city(cities.get(0))
                        .favouriteColors(Arrays.asList("red"))
                        .build()
                , formConfig);
        // show form in UI thread
        SwingUtils.runInEDT(() -> new DynamicFormDialog(formContext));
    }
}
