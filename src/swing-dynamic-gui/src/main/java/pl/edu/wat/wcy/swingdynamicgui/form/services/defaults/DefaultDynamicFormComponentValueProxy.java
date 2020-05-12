package pl.edu.wat.wcy.swingdynamicgui.form.services.defaults;

import pl.edu.wat.wcy.swingdynamicgui.form.components.DynamicFieldForm;
import pl.edu.wat.wcy.swingdynamicgui.form.services.DynamicFormComponentValueProxy;
import pl.edu.wat.wcy.swingdynamicgui.utils.ParsingUtils;
import pl.edu.wat.wcy.swingdynamicgui.utils.ReflectionUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.Collection;

public class DefaultDynamicFormComponentValueProxy implements DynamicFormComponentValueProxy {
    @Override
    public void setValue(JComponent component, Object value) {
        component = getInputComponent(component);

        if (component instanceof JFormattedTextField) {
            JFormattedTextField formattedTextField = (JFormattedTextField) component;
            formattedTextField.setValue(value);
        } else if (component instanceof JTextComponent) {
            JTextComponent textComponent = (JTextComponent) component;
            textComponent.setText(ParsingUtils.getNotNullString(value));
        } else if (component instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) component;
            comboBox.setSelectedItem(value);
        } else if (component instanceof JList) {
            JList list = (JList) component;
            Collection selectedValues = ReflectionUtils.getObjectAsCollection(value);
            int[] selectedIndixes = new int[selectedValues.size()];
            int currentIndex = 0;
            for(Object selectedValue : selectedValues){
                selectedIndixes[currentIndex++] = getIndexOfElement(list.getModel(), selectedValue);
            }
            list.setSelectedIndices(selectedIndixes);
        } else if (component instanceof JToggleButton) {
            JToggleButton toggleButton = (JToggleButton) component;
            toggleButton.setSelected(ParsingUtils.getBool(value));
        }
    }


    @Override
    public Object getValue(JComponent component) {
        component = getInputComponent(component);
        if (component instanceof JFormattedTextField) {
            JFormattedTextField formattedTextField = (JFormattedTextField) component;
            return formattedTextField.getValue();
        } else if (component instanceof JTextComponent) {
            JTextComponent textComponent = (JTextComponent) component;
            return textComponent.getText();
        } else if (component instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) component;
            return comboBox.getSelectedItem();
        } else if (component instanceof JList) {
            JList list = (JList) component;
            return list.getSelectedValuesList();
        } else if (component instanceof JToggleButton) {
            JToggleButton toggleButton = (JToggleButton) component;
            return toggleButton.isSelected();
        }
        return null;
    }

    private JComponent getInputComponent(JComponent component) {
        if (component instanceof DynamicFieldForm) {
            return ((DynamicFieldForm) component).getInputComponent();
        } else {
            return component;
        }
    }

    private int getIndexOfElement(ListModel listModel, Object value) {
        for (int i = 0; i < listModel.getSize(); i++) {
            Object listVal = listModel.getElementAt(i);
            if (listVal.equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
