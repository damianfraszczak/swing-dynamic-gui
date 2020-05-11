package pl.edy.wat.wcy.dynamicgui.table.services;

import pl.edy.wat.wcy.dynamicgui.table.models.DynamicTableFieldMetadata;
import pl.edy.wat.wcy.dynamicgui.table.models.TableConfig;

import java.util.List;

public interface TableMetadataProvider {
    public List<DynamicTableFieldMetadata> loadMetadata(TableConfig tableConfig);
}
