package ru.viktorshiyan;

import org.junit.Assert;
import org.junit.Test;


import java.io.*;
import java.util.Arrays;


/**
 * Unit test for App.
 *
 * @author viktorshiyan
 */
public class AppTest {
    /**
     * Тест на проверку выходных данных
     *
     * @throws InterruptedException
     */
    @Test
    public void whenAllGood() throws InterruptedException {
        String[] args = {"src/test/resources/input1.csv", "src/test/resources/input2.csv", "src/test/resources/input3.csv"};
        Arrays.stream(args).forEach((x) -> new Parser(new File(x)).start());
        while (Thread.activeCount() > 2) {
            Thread.sleep((long) 100);
        }
        try {
            File fileID = new File("id");
            FileReader frID = new FileReader(fileID);
            BufferedReader readerID = new BufferedReader(frID);
            String line = readerID.readLine();
            Assert.assertEquals(line, "0;1;2;3;8;");
            readerID.close();
            fileID.delete();

            File fileName = new File("name");
            FileReader frName = new FileReader(fileName);
            BufferedReader readerName = new BufferedReader(frName);
            line = readerName.readLine();
            Assert.assertEquals(line, "Viktor;жорж;мария;пьер;ричард;");
            readerName.close();
            fileName.delete();

            File filePath = new File("path");
            FileReader frPath = new FileReader(filePath);
            BufferedReader readerPath = new BufferedReader(frPath);
            line = readerPath.readLine();
            Assert.assertEquals(line, "/hello/лошадка;/hello/собачка;/hello/уточка;");
            readerPath.close();
            filePath.delete();

            File fileVersion = new File("version");
            FileReader frVersion = new FileReader(fileVersion);
            BufferedReader readerVersion = new BufferedReader(frVersion);
            line = readerVersion.readLine();
            Assert.assertEquals(line, "1;2;3;");
            readerVersion.close();
            fileVersion.delete();


            File fileSex = new File("sex");
            FileReader frSex = new FileReader(fileSex);
            BufferedReader readerSex = new BufferedReader(frSex);
            line = readerSex.readLine();
            Assert.assertEquals(line, "ж;м;");
            readerSex.close();
            fileSex.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
