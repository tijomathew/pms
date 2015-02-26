package org.pms.helpers;

import java.util.List;

/**
 * Created by tijo on 9/12/14.
 */
public final class GridGenerator {


    public GridContainer createGridContainer(Integer total, Integer page, Integer record, List<? extends GridRow> rows) {
        GridContainer gridContainer = new GridContainer();
        gridContainer.setTotal(total);
        gridContainer.setPage(page);
        gridContainer.setRecords(record);
        gridContainer.setRows(rows);
        return gridContainer;
    }

}
