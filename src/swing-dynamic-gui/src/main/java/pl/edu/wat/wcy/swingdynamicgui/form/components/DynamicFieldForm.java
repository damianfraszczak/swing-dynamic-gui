package pl.edu.wat.wcy.swingdynamicgui.form.components;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormContext;
import pl.edu.wat.wcy.swingdynamicgui.form.models.DynamicFormFieldMetadata;
import pl.edu.wat.wcy.swingdynamicgui.utils.Icons;
import pl.edu.wat.wcy.swingdynamicgui.utils.ParsingUtils;

import javax.swing.*;
import javax.swing.border.Border;

public class DynamicFieldForm extends JComponent {

    private final DynamicFormContext context;

    @Getter
    private DynamicFormFieldMetadata fieldMetadata;
    @Getter
    private JComponent inputComponent;
    private JLabel fieldLabel;
    private JLabel validationLabel;

    private Border defaultInputBorder;



    public DynamicFieldForm(DynamicFormContext context,
                            DynamicFormFieldMetadata fieldMetadata) {
        super();
        this.context = context;
        this.fieldMetadata = fieldMetadata;
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        inputComponent = context.getFormField(fieldMetadata);
        fieldLabel = new JLabel(fieldMetadata.getFieldLabel() + context.getFormConfig().getLabelPostfix());
        defaultInputBorder = inputComponent.getBorder();
    }

    /**
     * @param validationError if empty or null removes
     */
    public void setValidationError(String validationError) {
        if (ParsingUtils.isEmptyString(validationError)) {
            if (this.validationLabel != null) {
                remove(this.validationLabel);
                validationLabel = null;
                revalidate();
                inputComponent.setBorder(defaultInputBorder);
            }
        } else {
            if (validationLabel == null) {
                validationLabel = new JLabel();
                validationLabel.setForeground(context.getFormConfig().getErrorColor());
                validationLabel.setIcon(Icons.ERROR_ICON);
                add(validationLabel, "span 2");
                inputComponent.setBorder(BorderFactory.createLineBorder(context.getFormConfig().getErrorColor()));
            }
            validationLabel.setText(validationError);
            revalidate();
        }
    }

    private void layoutComponents() {
        switch (context.getFormConfig().getLabelPosition()) {
            case LEFT:
                setLayout(new MigLayout("wrap 2", "[" + context.getLabelSize() + "][grow,fill]", "[shrink 0]"));
                add(fieldLabel);
                add(inputComponent);
                break;
            case RIGHT:
                setLayout(new MigLayout("wrap 2", "[grow,fill][" + context.getLabelSize() + "]", "[shrink 0]"));
                add(inputComponent);
                add(fieldLabel);
                break;
            case TOP:
                setLayout(new MigLayout("wrap 1", "[grow,fill]", "[shrink 0]"));
                add(fieldLabel);
                add(inputComponent);
                break;
            case HIDDEN:
                setLayout(new MigLayout("wrap 1", "[grow,fill]", "[shrink 0]"));
                add(inputComponent);
                break;
        }


    }


}
