package ru.viktorshiyan;

import java.io.*;

/**
 * Parser for cvs
 *
 * @author viktorshiyan
 */
public class Parser extends Thread {
    private final File file;

    /**
     * Construction
     *
     * @param file path to file
     */
    public Parser(File file) {
        this.file = file;
    }

    synchronized public void toParse() {
        try {
            synchronized (File.class) {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                String[] arrName = line.split(";");
                reader.close();
                for (int i = 0; i < arrName.length; i++) {
                    System.out.println("Вызывается новый поток FileCreator для файла " + file.getPath() + " со значением " + arrName[i]);
                    new FileCreator(file, i, new File(arrName[i])).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.toParse();
    }
}
