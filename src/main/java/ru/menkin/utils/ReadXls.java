package ru.menkin.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.menkin.models.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadXls {
    private InputStream inputStream;

    private static final int COUNT_CELLS_IN_ROW = 4;
    private static final int NUMBER_OF_NUMERIC_CELL = 3;

    public ReadXls(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    /**
     *  using POI library for read .xls(x)
     * @return converted list from inputStream
     */
    public List<Player> convert() {
        int index = 1;
        List<Player> list = new ArrayList<Player>();
        try {
            Workbook workbook = new HSSFWorkbook(inputStream);
            //Get the number of sheets in the xls file
            int numberOfSheets = workbook.getNumberOfSheets();
            //loop through each of the sheets
            for (int i = 0; i < numberOfSheets; i++) {
                //Get the nth sheet from the workbook
                Sheet sheet = workbook.getSheetAt(i);
                //every sheet has rows, iterate over them
                for (Row row : sheet) {
                    //Get the row object
                    //Every row has columns, get the column iterator and iterate over them
                    if (isValidRow(row)) {
                        Iterator<Cell> cells = row.cellIterator();
                        Player player =
                                new Player(index++, cells.next().getStringCellValue(),
                                        cells.next().getStringCellValue(),
                                        String.valueOf(cells.next().getNumericCellValue()),
                                        cells.next().getStringCellValue());
                        list.add(player);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *check row on validity
     * @param row
     * @return validity of row
     */
    boolean isValidRow(Row row) {
        int count = 0;
        boolean flag = true; //for one return
        Iterator<Cell> cells = row.cellIterator();
        while (cells.hasNext()) {
            Cell cell = cells.next();
            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                count++;
            }
            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                count++;
                if(count == NUMBER_OF_NUMERIC_CELL){
                    flag = false;
                    break;
                }
            }
        }
        if (count != COUNT_CELLS_IN_ROW) {
            flag = false;
        }
        return flag;
    }
}
