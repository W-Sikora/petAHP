package pl.wsikora.petahp.utils;

import java.util.Random;

public class LinkGenerator {
    private static final int LEFT_LIMIT = 48;
    private static final int RIGHT_LIMIT = 122;
    private static final int TARGET_LENGTH = 10;
    private static final Random RANDOM = new Random();
    private static final String PATH = "http://localhost:8080/ankieta/";

    private LinkGenerator() {
    }

    public static String generate() {
        return RANDOM.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(TARGET_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .insert(0, PATH)
                .toString();
    }

}
