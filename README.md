# swing-dynamic-gui
This is a library providing easy and customisable way of building GUI in Java Swing applications utilizing forms and tables generation based on config via both annotations and code.

# How to start
## Provided examples
In order to run attached examples please download/clone repository, execute `mvn install` and then just run the chosen example.
### model package
This package consists of java POJOs used in forms
### simple package
This package includes the simplest examples of using lib. You can check `FormExampleApp` to visualize generated form and `TableExampleApp`
 to check how to create dynamic table
#### crud package
This package provides an example how to can use lib in CRUD like app and how to laod data asynchronously outside the event dispatch thread. Moreover this example use one of the Look And Feels provided by Substance. Check it to get best UI with SWING.
## Custom form
- Create used model and add `@DynamicFormField` annotations to some fields, for example
````java
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
````

- Create form context
If you use data which is not available in the moment of creating the form please load them asynchronousloy and after that load them in event dispatch thread. The lib provides convinient method to do in `SwingUtils.invokeInEDT`, for example
```java
// load data from db etc
final List<City> cities = Arrays.asList(new City[]{
                new City("Warsaw"),
                new City("London"),
                new City("London"),
        });
// create config based on annotations defined in model
FormConfig formConfig = DynamicContextBuilder.getFormConfigBasedOnAnnotation(Person.class);
// extend form config to set providers for fields or configure other options
formConfig.addSelectProvider("city", (object, field) -> cities);
// set groups renderer
formConfig.setFormGroupsRenderer(new TabsGroupsFormGroupRenderer());
```
- Create form context
The form context object is a form manager resposible for logic and loading asynchronously data etc. You can load context synchronosuly or asynchronously. In this stage you should also load object to display in form.
```java
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
```
- Show obtained form in any component. For this example we have used a custom dialog or you can use provided `DynamicFormDialog` for simplify this process
```java
// show form in UI thread
SwingUtils.runInEDT(() -> new ExampleFormDialog(formContext));
```
- Get results

| Default SWING LAF  |  Substance LAF |
|---|---|
| ![generated form](images/simple-tabbed-form.PNG)  |  ![generated form](images/crud-table-tabs-groups.png) |
| ![generated form](images/simple-form-fieldset-group-validation.png) |  ![generated form](images/crud-validation.png) |




## Custom table
- Create used model and add `@DynamicFormField` annotations to some fields/ Let's suppose that we use the same model as for form generation
- Crate table config, context and table model
```java
 TableConfig tableConfig = new TableConfig(Person.class);
DynamicTableContext tableContext = new DynamicTableContext(
    tableConfig,
    tableConfig.getMetadataProvider().loadMetadata(tableConfig)
);
DynamicFieldTableAdapter tableModel = new DynamicFieldTableAdapter(tableContext);
```
- Create table component and show it for example in form. Please remember to do this in EDT
```java
SwingUtils.runInEDT(()->{
            JFrame frame = new JFrame();


            JTable table = new JTable(tableModel);
            table.setDefaultRenderer(Object.class, new DynamicTableContextRenderer(tableContext));
            JScrollPane pane = new JScrollPane(table,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            frame.add(table.getTableHeader(), BorderLayout.NORTH);
            frame.add(pane, BorderLayout.CENTER);

            frame.setTitle("Dynamic table example");
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SwingUtils.centerOnScreen(frame, true);
            frame.setVisible(true);
        });
```
- load table data asynchronosuly besides EDT
```java
tableModel.loadData(Arrays.asList(
                Person.builder()
                        .name("John")
                        .surname("Test")
                        .salary(500)
                        .sex(Sex.MALE)
                        .build(),
                Person.builder()
                        .name("Anna")
                        .surname("Swing")
                        .sex(Sex.FEMALE)
                        .salary(500)
                        .build()));
```
- get results

|  SWING LAF | Substance LAF   |
|---|---|
| ![generated table](images/simple-table-1.png)  | ![generated table](images/crud-table.png)  |

# Form configuration
## Models 
### BaseDynamicConfig
It is a base for config both tables and forms. It contains the following properties:
- `String dateFormatString` - format used for date formatting by default *dd/MM/yyyy*;
- `String timeFormatString` - format used for time formatting by default *HH:mm*;
- `String dateTimeFormatString` - format used for date and time formatting by default *dd/MM/yyyy HH:mm*;
- `Format doubleFormat` - java fromatter used to format double values by default *NumberFormat.getNumberInstance()*
- `Format integerFormat` - java formatter used to format integer values by default *NumberFormat.getIntegerInstance()*
- `Format currencyFormat` - java formatter used to format currency values by default *NumberFormat.getCurrencyInstance()*
- `Color errorColor` - color used to mark errors on forms by default *Color.RED*
### FormConfig
It is config used for form layout it inhertis fields from BaseDynamicConfig and additionaly consist of the following properties

- `boolean showPlaceholders` - if show placeholders on text fields by default *true*
- `LabelPosition labelPosition` - position of the field label on the form. Available options are:
  - LEFT - show on the left side of the field
  - RIGHT - show on the right side of the field
  - TOP - show on the top side of the field
  - HIDDEN - fields label is hidden

by default *LEFT* is set
- `String labelPostfix` - used to add additional character after label for example to add semi-colon by default *:* is set
- `DynamicFormGroupRenderer formGroupsRenderer` - form groups renderer component. Form can contains from many groups and this component is resposible for layout them. The lib provides following options:
  - `WithoutGroupsFormGroupRenderer` - don't display form groups like groups only like plain fields
  - `TabsGroupsFormGroupRenderer` - display groups utilizing tabs component
  - `FieldsetFormGroupRenderer` - display groups as fieldsets
by default *WithoutGroupsFormGroupRenderer* is used. To implement custom component resposible for rendering groups you need only to implement `DynamicFormGroupRenderer` interface and set it in `FormConfig`. Below there is a code resposible for rendering groups as tabs.
``` java
public class TabsGroupsFormGroupRenderer implements DynamicFormGroupRenderer {
    @Override
    public JComponent renderFormGroups(List<FormGroup> groups) {
        JTabbedPane pane = new JTabbedPane();
        groups
                .forEach(formGroup -> {
                    JPanel panel = new JPanel(DynamicFormUtils.getFormMigLayout());
                    formGroup.getGroupFields().forEach(field -> {
                        panel.add(field, DynamicFormUtils.getDynamicFormLayoutSizeParams(field.getFieldMetadata()));
                    });
                    pane.addTab(formGroup.getName(), panel);

                });
        return pane;
    }
}

```
- `DynamicFormComponentValueProxy componentValueProxy` - this service is responsible for copy values from object to components and vice versa. Providing this mechanism as a service is great if you would like to add new components for new types of fields 
- `DynamicFormFieldProvider formFieldProvider` - this service is responsible for build fields used in forms
- `DynamicFormValidator validator` - this service is resposible for validation. You can extend this component if you want to write custom validation logic. Currently this service utilize javax.validation package.
- `DynamicFormLabelProvider labelProvider` - this service is resposible for providing labels and placeholders for fields. It is useful to overidde it when you have to implement i18n. By default it uses value form annotation if it is not set it use field name with title case conversion
- `Map<String, SelectOptionProvider> selectProviders` - this is map of services resposible for providing dynamic data to select like components. You use them when you need to load asynchronously data from db or other type of resource
- `List<FormGroupConfig> formGroups` - user defined form groups. Each group consists of name, size and fields in it.

## Annotations
### DynamicFormField
This annotation is used to provide easy to use config for field both used in form and table. It provides following properties: 
- `FieldInputType fieldInputType` - this is type of component used to choose corect field in form and renderer for tables. This options are available:
  - DEFAULT - field component is choosen based on field type
  - COMBOBOX - *JComboBox* is used as a component
  - SELECT - *JList* is used as a component with single select option
  - MULTI_SELECT - *JList* is used as a component with multi select option
  - CHECKBOX - *JCheckbox* is used as a component 
  - TOGGLE - *JToogleButton* is used as a component 
  - PASSWORD - *JPasswordField* is used as a component 
  - DATE - *JFormattedTextField* is used as a component with format configured in *FormConfig*
  - DATE_TIME - *JFormattedTextField* is used as a component with format configured in *FormConfig*
  - TIME - *JFormattedTextField* is used as a component with format configured in *FormConfig*
  - CURRENCY - *JFormattedTextField* is used as a component with format configured in *FormConfig*
  - DOUBLE - *JFormattedTextField* is used as a component with format configured in *FormConfig*
  - INTEGER - *JFormattedTextField* is used as a component with format configured in *FormConfig*
- `FieldSize fieldSize` - size of the field on the form. Similary to web gui frameworks like Bootsrap etc. we use grid like system so each row contains of 12 columns. You can configure how much space your field should have, by default it is a full row.  Available options are:
  - ONE - field takes whole row size
  - TWO - field takes 1/2 row size
  - THREE - field takes 1/3 row size
  - FOUR - field takes 1/4 row size
  - SIX - field takes 1/6 row size
- `boolean displayInForm` - if field should be displayed in a form
- `int formFieldOrder` - order of field on the form
- `String staticOptionsAsCommaSeparatedString` - comma separated list of options used for selects. Not always you need to use enums or any backend data provider to initialize it. In such cases you can provide them in this way
- `String fieldPlaceholder` - placeholder for the field, displayed on the form when not data is provided to it
- `String fieldLabel` - label of field on the form, by default field name
- `boolean displayInTable` - if field should be displayed in a table
- `String tableHeader` - header title for this field, by default field name
- `int tableFieldOrder` - order of table header for field
### DynamicFormGroup
- `String name` - name of group
- `FieldSize size` - size of the form group
- `String[] fields` - array of field names used in group
## Custom validation
To provide custom validation (when *javax.validation* is not sufficient) you can implement interface `DynamicFormValidator` to write custom validation. It is recommended to extends `DefaultDynamicFormValidator` to write custom validation logic. It provides `ValidationResult validate(Object object)` method, check below example:
```java
class AppDefaultDynamicFormValidator extends  DefaultDynamicFormValidator{
    @Override
    public ValidationResult validate(Object object) {
        ValidationResult vr = super.validate(object);
        if(object instanceof  Person   ){
            Person p = (Person) object;
            if(p.getSurname().length() > 30){
                vr.addMessage("surname", "Surname is too long, it can be max 30 characters length");
            }
        }

        return vr;
    }
}
```
and set it in `FormConfig` 
```java
formConfig.setValidator(new AppDefaultDynamicFormValidator());
```
# Table configuration
## Models 
### TableConfig
It inherits from *BaseDynamicConfig* and consists of the following additional properties:
- `String[] visibleFields` - list of configured visible fields, be default it is empty. It takes higher importance than value set in annotation
- `String[] excludedFields`  - list of configured excluded fields, be default it is empty. It takes higher importance than value 
- `Map<String, TableCellRenderer> tableRenderersByFieldName` - mappings for providing appropriate table renderer for field. it can be used when you don't want to override global defined renderers for field type or defined type
- `Map<Class, TableCellRenderer> tableRenderersByType` - mappings for providing appropriate table renderer for field type. it can be used when you want to override global defined renderer for type
- `Map<FieldInputType, TableCellRenderer> tableRenderersByFieldType` -  mappings for providing appropriate table renderer for field input type
- `TableMetadataProvider metadataProvider` - service responsible for providing table config metadata
- `TableHeaderProvider headerProvider` - service is resposible for providing headers in table for fields. It is useful to override it when you have to implement i18n. By default it uses value form annotation if it is not set it use field name with title case conversion
## Annotations
### DynamicFormField
This is the same annotation used to mark fields being visible in form, for tables following properties are considered:
- `FieldInputType fieldInputType` - this is type of component used to choose corect field in form and renderer for tables. This options are available:
  - DEFAULT - field component is choosen based on field type
  - COMBOBOX - *DefaultTableTreeCellRendere* is used as a component
  - SELECT - *DefaultTableTreeCellRendere* is used as a component with single select option
  - MULTI_SELECT - *CollectionRenderer* is used as a table cell renderer
  - CHECKBOX - *DefaultTableTreeCellRendere* is used as a component 
  - TOGGLE - *DefaultTableTreeCellRendere* is used as a component 
  - PASSWORD - *PasswordCellRenderer* is used as a table cell renderer
  - DATE - *FormatRenderer* is used as a table cell renderer
  - DATE_TIME -  *FormatRenderer* is used as a table cell renderer
  - TIME -  *FormatRenderer* is used as a table cell renderer
  - CURRENCY -  *FormatRenderer* is used as a table cell renderer
  - DOUBLE -  *FormatRenderer* is used as a table cell renderer
  - INTEGER -  *FormatRenderer* is used as a table cell renderer
- `boolean displayInTable` - if field should be displayed in a table
- `String tableHeader` - header title for this field, by default field name
- `int tableFieldOrder` - order of table header for field
