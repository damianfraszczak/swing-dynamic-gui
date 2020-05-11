package pl.edu.wat.wcy.swingdynamicgui.form.components;

import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormContext;
import pl.edu.wat.wcy.swingdynamicgui.form.models.ValidationResult;

import javax.swing.*;
import java.awt.*;

public class DynamicForm extends JComponent {
    private DynamicFormContext dynamicFormContext;


    public DynamicForm(DynamicFormContext context) {
        this.dynamicFormContext = context;
        this.layoutComponents();
        this.dynamicFormContext.copyValuesFromObjectsToComponents();
    }


    protected void layoutComponents() {
        this.setLayout(new BorderLayout());
        this.add(dynamicFormContext.getFormConfig().getFormGroupsRenderer().renderFormGroups(dynamicFormContext.getAllFormGroups()));
    }

    public ValidationResult validateForm() {
        return dynamicFormContext.validateForm();
    }

}
