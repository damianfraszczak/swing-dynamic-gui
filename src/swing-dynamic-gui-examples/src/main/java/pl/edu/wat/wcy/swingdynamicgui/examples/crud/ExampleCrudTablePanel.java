package pl.edu.wat.wcy.swingdynamicgui.examples.crud;

import org.jdesktop.swingx.JXTable;
import pl.edu.wat.wcy.swingdynamicgui.DynamicContextBuilder;
import pl.edu.wat.wcy.swingdynamicgui.form.components.DynamicFormDialog;
import pl.edu.wat.wcy.swingdynamicgui.form.components.formgroup.FieldsetFormGroupRenderer;
import pl.edu.wat.wcy.swingdynamicgui.form.components.formgroup.WithoutGroupsFormGroupRenderer;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormConfig;
import pl.edu.wat.wcy.swingdynamicgui.table.DynamicFieldTableAdapter;
import pl.edu.wat.wcy.swingdynamicgui.table.models.DynamicTableContext;
import pl.edu.wat.wcy.swingdynamicgui.table.models.TableConfig;
import pl.edu.wat.wcy.swingdynamicgui.table.renderers.DynamicTableContextRenderer;
import pl.edu.wat.wcy.swingdynamicgui.utils.SwingUtils;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.City;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class ExampleCrudTablePanel extends JComponent {

    private JTable table;
    private DynamicFieldTableAdapter<Person> adapter;
    private PersonCrudService crudService;
    private FormConfig formConfig;
    private DynamicTableContext tableContext;

    public ExampleCrudTablePanel(PersonCrudService personCrudService) {
        this.crudService = personCrudService;
        initFormConfig();
        initTableConfig();
        createAndLayoutTable();
        crudService.list().thenAccept(personList -> {
            SwingUtils.runInEDT(() -> this.adapter.loadData(personList));
        });
    }


    public Person getSelectedElement() {

        if (table.getSelectedRow() > -1) {
            return adapter.getObjectAtRow(table.convertRowIndexToModel(table.getSelectedRow()));
        } else {
            return null;
        }
    }

    protected void createAndLayoutTable() {
        SwingUtils.runInEDT(()->{
            setLayout(new BorderLayout());
            table = new JXTable(adapter);
            table.setDefaultRenderer(Object.class, new DynamicTableContextRenderer(tableContext));
            JScrollPane pane = new JScrollPane(table,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            add(table.getTableHeader(), BorderLayout.NORTH);
            add(pane, BorderLayout.CENTER);

            table.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    super.mouseClicked(e);
                    if (SwingUtilities.isRightMouseButton(e)) {
                        SwingUtils.createPopUpMenuFromActionsAndMenuItems(
                                getActions()).show(table, e.getX(), e.getY());
                    }

                }

            });
        });
    }

    private List<Action> getActions() {
        return Arrays.asList(getCreateAction(), getUpdateAction(), getRemoveAction());
    }


    private AbstractAction getCreateAction() {
        return new AbstractAction("Add") {

            @Override
            public void actionPerformed(ActionEvent e) {

                DynamicFormDialog<Person> dialog = createDynamicDialog(Person.builder().build());
                dialog.setOkListener((evt) -> {
                    crudService.add(dialog.getEditedObject())
                            .thenAccept(person -> {
                                SwingUtils.runInEDT(() -> adapter.addObject(person));
                            });
                });
                dialog.setTitle("Add new person");
                dialog.setVisible(true);

            }
        };
    }

    private AbstractAction getUpdateAction() {
        return new AbstractAction("Update") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Person selected = getSelectedElement();
                if (selected == null) {
                    JOptionPane.showMessageDialog(ExampleCrudTablePanel.this, "No person selected");
                } else {
                    DynamicFormDialog<Person> dialog = createDynamicDialog(selected);
                    dialog.setOkListener((evt) -> {
                        crudService.update(dialog.getEditedObject())
                                .thenAccept(person -> {
                                    SwingUtils.runInEDT(() -> adapter.updateObject(person));
                                });
                    });
                    dialog.setTitle("Update selected person");
                    dialog.setVisible(true);
                }


            }
        };
    }

    private AbstractAction getRemoveAction() {
        return new AbstractAction("Remove") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Person selected = getSelectedElement();
                if (selected == null) {
                    JOptionPane.showMessageDialog(ExampleCrudTablePanel.this, "No person selected");
                } else {
                    crudService.delete(selected)
                            .thenAccept(person -> {
                                SwingUtils.runInEDT(() -> adapter.removeObject(person));
                            });
                }


            }
        };
    }


    private DynamicFormDialog<Person> createDynamicDialog(Person person) {
        return new DynamicFormDialog<Person>(person, formConfig);
    }

    private void initTableConfig() {
        TableConfig tableConfig = new TableConfig(Person.class);
        tableContext = new DynamicTableContext(
                tableConfig,
                tableConfig.getMetadataProvider().loadMetadata(tableConfig)
        );
        adapter = new DynamicFieldTableAdapter(tableContext);
    }

    private void initFormConfig() {
        final List<City> cities = Arrays.asList(new City[]{
                new City("Warsaw"),
                new City("London"),
                new City("London"),
        });
        this.formConfig = DynamicContextBuilder.getFormConfigBasedOnAnnotation(Person.class);
        formConfig.addSelectProvider("city", (object, field) -> cities);
        formConfig.setFormGroupsRenderer(new FieldsetFormGroupRenderer());

    }
}
