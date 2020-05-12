package pl.edu.wat.wcy.swingdynamicgui.examples.simple;

import pl.edu.wat.wcy.swingdynamicgui.table.DynamicFieldTableAdapter;
import pl.edu.wat.wcy.swingdynamicgui.table.models.DynamicTableContext;
import pl.edu.wat.wcy.swingdynamicgui.table.models.TableConfig;
import pl.edu.wat.wcy.swingdynamicgui.table.renderers.DynamicTableContextRenderer;
import pl.edu.wat.wcy.swingdynamicgui.utils.SwingUtils;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Person;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Sex;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TableExampleApp {

    public static void main(String[] args) {
        // initialzie context and data beside UI Thread
        TableConfig tableConfig = new TableConfig(Person.class);
        DynamicTableContext tableContext = new DynamicTableContext(
                tableConfig,
                tableConfig.getMetadataProvider().loadMetadata(tableConfig)
        );
        DynamicFieldTableAdapter tableModel = new DynamicFieldTableAdapter(tableContext);

        // show frame with table in UI thread
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

        // load data outside the thread
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

    }
}
