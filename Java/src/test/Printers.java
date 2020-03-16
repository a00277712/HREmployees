package test;

import io.HeaderPrinter;
import io.MenuPrinter;
import io.TablePrinter;

import java.util.ArrayList;

public class Printers {
    public static void main(String[] args) throws Exception {
        ArrayList<String> testValues = new ArrayList<>();

        testValues.add("First");
        testValues.add("Second");
        testValues.add("Third long piece of text that should get cut off");

        //HeaderPrinterTest();

        //MenuPrinterTestWHeader(testValues);
        //MenuPrinterTestWoHeader(testValues);

        //TablePrinter.printDivider(50);
        //TablePrinter.printLine("Test Header","Hello", 50);
        //TablePrinter.printLine("Long Header that will get cut off", "Long body that will get cut off and put onto the next line ", 20);
    }

    private static void MenuPrinterTestWHeader(ArrayList<String> testValues) throws Exception {
        MenuPrinter.print("Header Text", testValues, 20);
    }

    private static void MenuPrinterTestWoHeader(ArrayList<String> testValues) throws Exception {
        MenuPrinter.print(testValues, 20);
    }

    private static void HeaderPrinterTest() throws Exception {
        HeaderPrinter.print("Long Test String that's too long", 7);
        HeaderPrinter.print("Test String that has room ", 50);
        HeaderPrinter.print("Just Right", 12);
    }
}
