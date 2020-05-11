package pl.edy.wat.wcy.dynamicgui.form.services.defaults;

import pl.edy.wat.wcy.dynamicgui.form.components.swing.TextPlaceholder;
import pl.edy.wat.wcy.dynamicgui.form.models.DynamicFormFieldMetadata;
import pl.edy.wat.wcy.dynamicgui.form.models.FormConfig;
import pl.edy.wat.wcy.dynamicgui.form.services.DynamicFormFieldProvider;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class DefaultDynamicFormFieldProvider implements DynamicFormFieldProvider {
    @Override
    public JComponent getFormField(
            FormConfig formConfig,
            DynamicFormFieldMetadata metadata) {
        JComponent result = null;
        switch (metadata.getType()) {

            case COMBOBOX:
                result = new JComboBox<>(new DefaultComboBoxModel<Object>(metadata.getOptions().toArray()));
                break;
            case SELECT:
                JList selectList = new JList<>(metadata.getOptions().toArray());
                selectList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                result = selectList;
                break;
            case MULTI_SELECT:
                JList multiSelectList = new JList<>(metadata.getOptions().toArray());
                multiSelectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                result = multiSelectList;
                break;
            case CURRENCY:
                result = new JFormattedTextField(formConfig.getCurrencyFormat());
                break;
            case DATE:
                result = new JFormattedTextField(formConfig.getDateFormat());
                break;
            case DATE_TIME:
                result = new JFormattedTextField(formConfig.getDateTimeFormat());
                break;
            case TIME:
                result = new JFormattedTextField(formConfig.getTimeFormat());
                break;
            case CHECKBOX:
                result = new JCheckBox(metadata.getFieldLabel());
                break;
            case TOGGLE:
                result = new JToggleButton(metadata.getFieldLabel());
                break;
            case PASSWORD:
                result = new JPasswordField();
                break;
            case DOUBLE:
                result = new JFormattedTextField(formConfig.getDoubleFormat());
                break;
            case INTEGER:
                result = new JFormattedTextField(formConfig.getIntegerFormat());
                break;
        }
        if (result == null) {
            result = new JTextField();
        }
        if (result instanceof JTextComponent && formConfig.isShowPlaceholders()) {
            new TextPlaceholder(metadata.getFieldPlaceholder(), (JTextComponent) result);
        }
        return result;
    }


}
