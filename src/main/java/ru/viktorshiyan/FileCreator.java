package ru.viktorshiyan;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс наследник потока
 * Который по индексу создаёт
 * или дописывает файл с данными
 *
 * @author viktorshiyan
 */
public class FileCreator extends Thread {
    private final int index;
    private final File fileWrite;
    private final File file;

    /**
     * @param file  путь к файлу
     * @param index индекс для парсинга значений
     */
    public FileCreator(File file, int index, File fileWrite) {
        this.file = file;
        this.index = index;
        this.fileWrite = fileWrite;
    }

    /**
     * Метод осуществляющий парсинг и запись
     */
    private void toParse() {
        try {
            synchronized (File.class) {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                String name = line.split(";")[index];
                Set<String> stringSet = new TreeSet<>();
                while (line != null) {
                    line = reader.readLine();
                    if (line != null) {
                        stringSet.add(line.split(";")[index]);
                    }
                }
                if (fileWrite.exists()) {
                    FileReader fr2 = new FileReader(fileWrite);
                    BufferedReader reader2 = new BufferedReader(fr2);
                    String resultReader = reader2.readLine();
                    System.out.println("Считывание файла " + name + "\n" + "Содержимое " + resultReader + "\n" + "Добавляемое " + stringSet.toString());
                    if (resultReader != null) {
                        String[] arrLine = resultReader.split(";");
                        stringSet.addAll(Arrays.asList(arrLine.clone()));
                    }
                }
                FileWriter writer = new FileWriter(fileWrite);
                String text = stringSet.stream().reduce("", (identity, x) -> identity.concat(x.concat(";")));
                writer.write(text);
                writer.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода вывода");
        }
    }

    @Override
    public void run() {
        this.toParse();
    }
}
