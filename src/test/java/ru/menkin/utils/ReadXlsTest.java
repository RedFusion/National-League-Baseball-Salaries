package ru.menkin.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.junit.*;
import ru.menkin.models.*;

import java.io.*;
import java.util.*;

/**
 * @author Menkin
 * @since 06.10.2015
 */
public class ReadXlsTest extends Assert {
    private static final int NUMBER_OF_STARTING_SHEET = 0;

    @Test
    public void test() {
        try {
            //test readXls.isValidRow()
            InputStream inputStream = new FileInputStream("src\\test\\resources\\Test.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet worksheet = workbook.getSheetAt(NUMBER_OF_STARTING_SHEET);
            Iterator<Row> iterator = worksheet.iterator();
            Row row = iterator.next();
            ReadXls readXls = new ReadXls(inputStream);
            assertEquals("Wrong check of a line ", true, readXls.isValidRow(row));
            inputStream.close();

            //test readXls.convert()
            List<Player> list = new ArrayList<Player>();
            //ClosedChannelException
            InputStream inputStream1 = new FileInputStream("src\\test\\resources\\Test.xls");
            ReadXls readXls1 = new ReadXls(inputStream1);
            List<Player> resultList = readXls1.convert();
            inputStream1.close();

            Player player = new Player(1, "Arizona", "Greg", "325000.0", "Pitcher");
            list.add(player);

            for (int i = 0; i < resultList.size(); i++) {
                assertEquals("Different id", list.get(i).getId(), resultList.get(i).getId());
                assertEquals("Different team", list.get(i).getTeam(), resultList.get(i).getTeam());
                assertEquals("Different name", list.get(i).getName(), resultList.get(i).getName());
                assertEquals("Different salary", list.get(i).getSalary(), resultList.get(i).getSalary());
                assertEquals("Different position", list.get(i).getPosition(), resultList.get(i).getPosition());
            }
        }
        catch (IOException e) {/*NOP*/}
    }
}