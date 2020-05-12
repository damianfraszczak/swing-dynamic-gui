package pl.edu.wat.wcy.swingdynamicgui.form.components;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.wcy.swingdynamicgui.DynamicContextBuilder;
import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormContext;
import pl.edu.wat.wcy.swingdynamicgui.form.models.FormConfig;
import pl.edu.wat.wcy.swingdynamicgui.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicFormDialog<T> extends JDialog {

    @Getter
    private T editedObject;
    @Getter
    private FormConfig config;
    @Getter
    private DynamicFormContext context;
    @Getter
    @Setter
    private String okButtonText = "Save";
    @Getter
    @Setter
    private String cancelButtonText = "Cancel";

    private DynamicForm form;


    @Getter
    @Setter
    private ActionListener okListener;

    @Getter
    @Setter
    private ActionListener cancelListener;

    private JButton okButton;
    private JButton cancelButton;

    public DynamicFormDialog(T editedObject) {
        this(editedObject, DynamicContextBuilder.getFormConfigBasedOnAnnotation(editedObject.getClass()));
    }

    public DynamicFormDialog(T editedObject, FormConfig config) {
        this.editedObject = editedObject;
        this.config = config;
    }

    @Override
    public void setVisible(boolean b) {
        if (context == null && b == true) {
            DynamicContextBuilder.getFormContext(editedObject, config)
                    .thenAccept(context -> {
                        this.context = context;
                        SwingUtils.runInEDT(() -> {
                            createFormContent();
                            initDialogProperties();
                            super.setVisible(b);
                        });
                    });
        } else {
            super.setVisible(b);
        }

    }

    protected void initDialogProperties() {
        setModal(true);
        setResizable(true);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SwingUtils.centerOnScreen(this, true);
    }

    protected void createFormContent() {

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

        cancelButton = new JButton(new AbstractAction(getCancelButtonText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cancelListener != null) {
                    cancelListener.actionPerformed(e);
                }
                setVisible(false);
            }
        });
        buttonPane.add(cancelButton);
        okButton = new JButton(new AbstractAction(getOkButtonText()) {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (context.validateForm().isValid()) {
                    if (okListener != null) {
                        okListener.actionPerformed(e);
                    }
                    setVisible(false);
                }
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        form = new DynamicForm(context);
        add(form);
        add(buttonPane, BorderLayout.SOUTH);
    }
}
