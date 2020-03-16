package io;

import java.util.ArrayList;

/**
 * +--+--------------------+
 * |1 |XXX                 |
 * +--+--------------------+
 */
public class MenuPrinter {
    private static void printDivider(int length) {
        System.out.print("+--+");

        for (int i = 0; i < length - 5; i++) {
            System.out.print('-');
        }

        System.out.println('+');
    }

    private static void printItem(int index, String text, int length) throws Exception {
        if (index < 0 || index > 99) {
            System.out.println("between 0 - 99");
        } else {
            String indexString = String.valueOf(index);
            // if single digit pad
            if (index < 10) {
                indexString += ' ';
            }

            printItem(indexString, text, length);
        }
    }

    private static void printItem(String index, String text, int length) throws Exception {
        if (length < 9) {
            throw new Exception("too short");
        }
        if (index.length() > 2) {
            throw new Exception("index can only be 2 chars");
        }

        while (index.length() < 2) {
            index += ' ';
        }

        System.out.print('|');

        System.out.print(index);

        System.out.print('|');

        int endSpace = length - text.length() - 5;

        // if string too long replace end with ...
        if (endSpace < 0) {
            text = text.substring(0, length - 8) + "...";
        }

        System.out.print(text);

        //Ending space is total - leading
        for (int i = 0; i < endSpace; i++) {
            System.out.print(' ');
        }

        System.out.println('|');
    }

    /**
     * Prints the given text in the Menu format
     * @param header The text to be displayed
     * @param tableItems The Values in the table
     * @param width The width of the table
     * @throws Exception The width supplied isn't enough
     */
    public static void print(String header, ArrayList<String> tableItems, int width) throws Exception {
        printDivider(width);

        printItem("", header, width);

        print(tableItems, width);
    }

    /**
     * Prints the given text in the Menu format
     * @param tableItems The Items to display in the Menu
     * @param width The width of the table
     * @throws Exception The width supplied isn't enough
     */
    public static void print(ArrayList<String> tableItems, int width) throws Exception {
        printDivider(width);

        int index = 1;
        for (String item : tableItems) {
            printItem(index, item, width);
            index++;
        }
        printItem(0, "Return", width);

        printDivider(width);
    }
}
