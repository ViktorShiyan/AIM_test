package ru.viktorshiyan;

import java.io.File;
import java.util.Arrays;

/**
 * Класс для проверки введенных файлов
 *
 * @author viktorshiyan
 */
public class FileChecker {
    /**
     * Метод возврощает только пути по которым файлы существуют
     *
     * @param paths входные пути
     * @return отфильтрованные
     */
    private static String[] fileExists(String[] paths) {
        return Arrays.stream(paths).filter((path) -> new File(path).exists()).toArray(String[]::new);
    }

    /**
     * Метод возврощает только пути по которым файлы CSV
     *
     * @param paths входные пути
     * @return отфильтрованные
     */
    private static String[] fileCSV(String[] paths) {
        return Arrays.stream(paths).filter(s -> s.endsWith(".csv")).toArray(String[]::new);
    }

    public static String[] check(String[] paths) {
        return fileExists(fileCSV(paths));
    }
}
