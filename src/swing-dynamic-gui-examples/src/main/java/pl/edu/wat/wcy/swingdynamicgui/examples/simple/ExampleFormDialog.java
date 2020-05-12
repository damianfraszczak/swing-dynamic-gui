package pl.edu.wat.wcy.swingdynamicgui.examples.simple;

import pl.edu.wat.wcy.swingdynamicgui.form.components.DynamicForm;
import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormContext;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormConfig;
import pl.edu.wat.wcy.swingdynamicgui.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExampleFormDialog extends JFrame {

    private DynamicFormContext context;
    private DynamicForm form;


    public ExampleFormDialog(DynamicFormContext context) {
        this.context = context;
        this.form = new DynamicForm(context);
        add(form);

        JButton b = new JButton("Save");
        this.add(b, BorderLayout.SOUTH);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.validateForm();
            }
        });
        pack();
        SwingUtils.centerOnScreen(this, true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dynamic form example");
        setVisible(true);
    }

    public static void showDynamicForm(Object object, FormConfig formConfig) {

    }
}
