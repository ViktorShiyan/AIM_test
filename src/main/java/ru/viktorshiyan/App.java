package ru.viktorshiyan;

import java.io.File;
import java.util.Arrays;

/**
 * Вход в программу
 *
 *@author viktorshiyan
 */
public class App {
    public static void main(String[] args) {
        String[] paths = FileChecker.check(args);
        Arrays.stream(paths).forEach((x) -> new Parser(new File(x)).start());
    }
}
