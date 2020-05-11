package pl.edu.wat.wcy.swingdynamicgui.examples.simple;

import pl.edy.wat.wcy.dynamicgui.form.components.DynamicForm;
import pl.edy.wat.wcy.dynamicgui.form.models.DynamicFormContext;
import pl.edy.wat.wcy.dynamicgui.form.models.FormConfig;
import pl.edy.wat.wcy.dynamicgui.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicFormDialog extends JFrame {

    private DynamicFormContext context;
    private DynamicForm form;


    public DynamicFormDialog(DynamicFormContext context) {
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
