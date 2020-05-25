package pl.edu.wat.wcy.swingdynamicgui.examples.modelconfig;

import pl.edu.wat.wcy.swingdynamicgui.DynamicContextBuilder;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.City;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Person;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Sex;
import pl.edu.wat.wcy.swingdynamicgui.form.components.DynamicFormDialog;
import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormContext;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormConfig;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileConfigFormExampleApp {
    private static final String FORM_CONFIG_PATH = "/example-form-config.xml";

    public static void main(String[] args) throws IOException {

        // init data outside UI thread
        final List<City> cities = Arrays.asList(new City[]{
                new City("Warsaw"),
                new City("London"),
                new City("London"),
        });
        String finalFilePath = FileConfigFormExampleApp.class.getResource(FORM_CONFIG_PATH).getFile();
        FormConfig formConfig = FormConfig.loadConfig(new File(finalFilePath));
        formConfig.addSelectProvider("city", (object, field) -> cities);


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
