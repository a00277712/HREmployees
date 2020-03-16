package test;

import java.io.File;
import java.util.ArrayList;

public class CsvIOTest {

    public static void main(String[] args) {
        //getValues(new File("data/Employees.csv"));
        write(new File("src/test/tmp.csv"));
    }

    private static void write(File file){
        io.CsvIO testIO = new io.CsvIO(file, ",");
        ArrayList<ArrayList<String>> values = new ArrayList<>();

        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("First");
        tmp.add("Second");
        tmp.add("Third");

        values.add(tmp);

        tmp = new ArrayList<>();

        tmp.add("Fourth");
        tmp.add("Fifth");

        values.add(tmp);

        testIO.write(values);

        getValues(file);
    }

    private static void getValues(File file) {
        io.CsvIO testIO = new io.CsvIO(file, ",");

        ArrayList<ArrayList<String>> valuesArray = testIO.getValues();

        for(ArrayList<String> values: valuesArray){
            for(String value : values){
                System.out.print(value+',');
            }
            System.out.println();
        }
    }
}
