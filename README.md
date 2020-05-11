# swing-dynamic-gui
This is a library providing easy and customisable way of building GUI in Java Swing applications utilizing forms and tables generation based on config via both annotations and code.


# Form configuration
## Models 
## Annotations
### DynamicFormField
This annotation is used to provide easy to use config for field both used in form and table. It provides following properties: 
-  `FieldInputType fieldInputType` - this is type of component used to choose corect field in form and renderer for tables. This options are available
- `FieldSize fieldSize` - size of the field on the form. Similary to web gui frameworks like Bootsrap etc. we use grid like system so each row contains of 12 columns. You can configure how much space your field should have, by default it is a full row
- `boolean displayInForm` - if field should be displayed in a form
- `int formFieldOrder` - order of field on the form
- `String staticOptionsAsCommaSeparatedString` - comma separated list of options used for selects. Not always you need to use enums or any backend data provider to initialize it. In such cases you can provide them in this way
- `String fieldPlaceholder` - placeholder for the field, displayed on the form when not data is provided to it
- `String fieldLabel` - label of field on the form, by default field name
- `boolean displayInTable` - if field should be displayed in a table
- `String tableHeader` - header title for this field, by default field name
- `int tableFieldOrder` - order of table header for field

