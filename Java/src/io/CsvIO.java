package io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads and writes to a file using a separator
 */
public class CsvIO {

    private File CsvFile;
    private String Separator;

    /**
     * Constructor for the CsvIO Class
     * @param csvFile the file to read and write to
     * @param separator the separator used by the file
     */
    public CsvIO(File csvFile, String separator) {
        CsvFile = csvFile;
        Separator = separator;
    }

    /**
     * Gets the values from the file as a lists of strings.
     * @return The lists of strings from the csv file
     */
    public ArrayList<ArrayList<String>> getValues() {
        ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
        try (Scanner scanner = new Scanner(CsvFile)) {
            while (scanner.hasNextLine()) {
                values.add(getValuesFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return values;
    }

    private ArrayList<String> getValuesFromLine(String line) {
        return new ArrayList<>(Arrays.asList(line.split(Separator)));
    }

    /**
     * Writes a lists of strings to the file the the separator between each string
     * @param values the lists of strings to be written
     */
    public void write(ArrayList<ArrayList<String>> values) {
        ArrayList<String> csvLines = new ArrayList<>();

        for (ArrayList<String> lineValues: values) {
            csvLines.add(toCvsString(lineValues));
        }

        writeCsvLines(csvLines);
    }

    private String toCvsString(ArrayList<String> lineValues){
        StringBuffer lineString = new StringBuffer();

        for (String value: lineValues) {
            lineString.append(value);
            lineString.append(Separator);
        }

        return lineString.toString();
    }

    /**
     * Writes a list of strings to the file the the separator between each string
     * @param csvLines the list of strings to be written
     */
    private void writeCsvLines(ArrayList<String> csvLines) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(CsvFile), StandardCharsets.UTF_8));
            for (String line : csvLines) {
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
