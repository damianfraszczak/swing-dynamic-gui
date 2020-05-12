package pl.edu.wat.wcy.swingdynamicgui.table.services;

import pl.edu.wat.wcy.swingdynamicgui.table.models.DynamicTableFieldMetadata;
import pl.edu.wat.wcy.swingdynamicgui.table.models.TableConfig;

import java.util.List;

public interface TableMetadataProvider {
    public List<DynamicTableFieldMetadata> loadMetadata(TableConfig tableConfig);
}
