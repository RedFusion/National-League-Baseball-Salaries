package ru.menkin.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ReadXls
{
    public static void main(String[] args)
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream("NLBB.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = worksheet.iterator();
            while (iterator.hasNext())
            {
                Row row = iterator.next();
                Iterator<Cell> iteratorCell = row.cellIterator();
                while (iteratorCell.hasNext())
                {
                    Cell cell = iteratorCell.next();
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                    {
                        System.out.print(cell.getStringCellValue() + " ");
                    }
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                    {
                        System.out.print(cell.getNumericCellValue() + " ");
                    }
                }
                System.out.println();
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
