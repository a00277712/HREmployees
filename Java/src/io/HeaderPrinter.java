package io;

/**
 *  ****************
 *  *      XXX     *
 *  ****************
 */
public class HeaderPrinter {

    private static void lengthCheck(int length) throws Exception {
        if (length < 5) {
            throw new Exception("Length must be greater than 4");
        }
    }

    /**
     * Prints the given text in the header format
     * @param text Inner text
     * @param width Total width of the header
     * @throws Exception The width given is too short
     */
    public static void print(String text, int width) throws Exception {
        lengthCheck(width);

        printStars(width);
        printHeaderText(text, width);
        printStars(width);
    }

    private static void printStars(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print('*');
        }
        System.out.println();
    }

    private static void printHeaderText(String text, int length) {
        System.out.print('*');

        //Calculate white space total length - text length - space taken up by *
        int totalSpace = length - text.length() - 2;
        int leadingSpace = (totalSpace) / 2;

        for (int i = 0; i < leadingSpace; i++) {
            System.out.print(' ');
        }

        if (totalSpace < 0) {
            text = text.substring(0, length - 5) + "...";
        }

        System.out.print(text);

        //Ending space is total - leading
        for (int i = 0; i < totalSpace - leadingSpace; i++) {
            System.out.print(' ');
        }

        System.out.println('*');
    }
}
