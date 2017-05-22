package org.pms.common;

import java.util.List;

/**
 * Created by tijo on 9/12/14.
 */
public final class GridGenerator {


    public GridContainer createGridContainer(Integer total, Integer page, Integer record, List<? extends GridRow> rows) {
        GridContainer gridContainer = new GridContainer();
        Double totalDisplayPagesCount;
        try {
            totalDisplayPagesCount = (Math.ceil(total.doubleValue() / record.doubleValue()));
        } catch (ArithmeticException ex) {
            totalDisplayPagesCount = 0d;
        }
        Integer totalPagesDisplayCount = totalDisplayPagesCount.intValue();
        if (record != 0 && totalPagesDisplayCount == 0)
            totalPagesDisplayCount = 1;


        if (record == 0l)
            page = 0;
        gridContainer.setTotal(totalPagesDisplayCount);
        gridContainer.setPage(page);
        gridContainer.setRecords(total);
        gridContainer.setRows(rows);
        return gridContainer;
    }

}
