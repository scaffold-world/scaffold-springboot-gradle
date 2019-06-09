package com.jiaheng.scaffold.common.util.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 进行sheet写操作的sheet。
 * Created by 张嘉恒 on 2018/5/5.
 */
public class MultiThreadPoiWriter implements Runnable {

    private final CountDownLatch doneSignal;

    private Sheet sheet;

    private int start;

    private int end;

    private List<TableDataRow> dataRows;

    private CellStyle cellStyle;

    public MultiThreadPoiWriter(CountDownLatch doneSignal, Sheet sheet, List<TableDataRow> dataRows, CellStyle cellStyle, int start,
                                int end) {
        this.doneSignal = doneSignal;
        this.sheet = sheet;
        this.dataRows = dataRows;
        this.cellStyle = cellStyle;
        this.start = start;
        this.end = end;
    }

    /**
     * sheet的row使用treeMap存储的，是非线程安全的，所以在创建row时需要进行同步操作。
     *
     * @param sheet
     * @param rownum
     * @return
     */
    private static synchronized Row getRow(Sheet sheet, int rownum) {
        return sheet.createRow(rownum);
    }

    @Override
    public void run() {
        Row row = null;
        Cell cell = null;
        try {
            // 数据
            if (dataRows != null && !dataRows.isEmpty()) {
                for (int i = 0; i < dataRows.size(); i++) {
                    int rownum = start + i;
                    row = getRow(sheet, rownum);
                    TableDataRow tableDataRow = dataRows.get(i);

                    List<TableDataCell> tableDataCellList = tableDataRow.getCells();
                    for (int j = 0; j < tableDataCellList.size(); j++) {
                        TableDataCell tableDataCell = tableDataCellList.get(j);
                        cell = row.createCell(j);
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue(tableDataCell.getValue());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doneSignal.countDown();
        }
    }


}
