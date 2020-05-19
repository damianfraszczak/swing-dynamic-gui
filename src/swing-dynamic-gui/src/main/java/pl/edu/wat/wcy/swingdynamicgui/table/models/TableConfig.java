package pl.edu.wat.wcy.swingdynamicgui.table.models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.edu.wat.wcy.swingdynamicgui.BaseDynamicConfig;
import pl.edu.wat.wcy.swingdynamicgui.enums.FieldInputType;
import pl.edu.wat.wcy.swingdynamicgui.table.services.TableHeaderProvider;
import pl.edu.wat.wcy.swingdynamicgui.table.services.TableMetadataProvider;
import pl.edu.wat.wcy.swingdynamicgui.table.services.defaults.DefaultTableHeaderProvider;
import pl.edu.wat.wcy.swingdynamicgui.table.services.defaults.DefaultTableMedatadaProvider;
import pl.edu.wat.wcy.swingdynamicgui.utils.DynamicFormUtils;

import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.Format;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Data
public class TableConfig extends BaseDynamicConfig {


    // from BaseDynamicConfig
    private String dateFormatString = "dd/MM/yyyy";
    private String timeFormatString = "HH:mm";
    private String dateTimeFormatString = dateFormatString + " " + timeFormatString;

    private Format doubleFormat = NumberFormat.getNumberInstance();
    private Format integerFormat = NumberFormat.getIntegerInstance();
    private Format currencyFormat = NumberFormat.getCurrencyInstance();

    private Color errorColor = Color.RED;

    @NonNull
    private Class dataType;
    private String[] visibleFields;
    private String[] excludedFields;
    private Map<String, TableCellRenderer> tableRenderersByFieldName = new HashMap<>();
    private Map<Class, TableCellRenderer> tableRenderersByType = new HashMap<>();
    private Map<FieldInputType, TableCellRenderer> tableRenderersByFieldType = new HashMap<>();
    private TableMetadataProvider metadataProvider = new DefaultTableMedatadaProvider();
    private TableHeaderProvider headerProvider = new DefaultTableHeaderProvider();


    public void addTableCellRendererByFieldName(String fieldName, TableCellRenderer renderer) {
        this.tableRenderersByFieldName.put(DynamicFormUtils.normalizeFieldName(fieldName), renderer);
    }

    public void addTableCellRendererByType(Class type, TableCellRenderer renderer) {
        this.tableRenderersByType.put(type, renderer);
    }
    public void addTableCellRendererByFieldType(FieldInputType inputType, TableCellRenderer renderer) {
        this.tableRenderersByFieldType.put(inputType, renderer);
    }

}
