package io;

import java.util.ArrayList;

/**
 *  +-----------+
 *  |XXX        |
 *  +-----------+
 */
public class TablePrinter {

    /**
     * Prints The divider for the table
     * @param width The total width of the table
     */
    public static void printDivider(int width) {
        System.out.print("+");

        for (int i = 0; i < width - 2; i++) {
            System.out.print('-');
        }

        System.out.println('+');
    }

    /**
     * Prints a line of a table with the given parameters
     * @param header The text to be displayed in the header
     * @param text The value in table
     * @param width Total width of the table
     */
    public static void printLine(String header, String text, int width) {
        printLine(header, width);

        ArrayList<String> textLines = new ArrayList<String>();

        while (text.length() > width - 2) {
            String tmp = text.substring(0, width - 2);
            int breakpoint = tmp.lastIndexOf(' ');
            tmp = text.substring(0, breakpoint);
            text = text.substring(breakpoint + 1);
            textLines.add(tmp);
        }

        textLines.add(text);

        for (String line : textLines) {
            printLine(line, width);
        }

        printDivider(width);
    }

    private static void printLine(String text, int width) {
        System.out.print('|');

        int endSpace = width - text.length() - 2;

        // if string too long replace end with ...
        if (endSpace < 0) {
            text = text.substring(0, width - 5) + "...";
        }

        System.out.print(text);

        //Ending space is total - leading
        for (int i = 0; i < endSpace; i++) {
            System.out.print(' ');
        }

        System.out.println('|');
    }
}
