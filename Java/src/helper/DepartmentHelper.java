package helper;

import java.util.ArrayList;
import java.util.Scanner;

import io.HeaderPrinter;
import io.MenuPrinter;
import io.TablePrinter;
import model.Department;

public class DepartmentHelper {

    /**
     * Prints the Department in a table
     *
     * @param department The department to print
     * @param printLength number of characters to go across the screen
     */
	public static void print(Department department, int printLength) throws Exception {
        HeaderPrinter.print(department.Name, printLength);

        TablePrinter.printDivider(printLength);

        TablePrinter.printLine("Location", department.Location, printLength);
    }

    /**
     * Prints the Menu for Departments
     *
     * @param departmentList The List of all Departments
     * @param printWidth     The width of the print area
     * @throws Exception The width is too small
     */
    public static void printMenu(ArrayList<Department> departmentList, int printWidth) throws Exception {
        HeaderPrinter.print("List of Departments", printWidth);

        ArrayList<String> options = new ArrayList<>();

        for (Department department : departmentList) {
            options.add(department.Name);
        }
        options.add("Add Department");

        MenuPrinter.print(options, printWidth);

        System.out.print("Enter option number:");
    }

    /**
     * allows modification of an entry
     *
     * @param department The department to modify
     * @param printWidth The width of the print area
     */
    public static void modify(Department department, int printWidth) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> properties = getPropertyNames();

        try {
            MenuPrinter.print(properties, printWidth);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }

        System.out.print("Enter option number:");
        while (input.hasNextInt()) {
            switch (input.nextInt()) {
                case 1:
                    System.out.println("Name: " + department.Name);
                    System.out.print("New value:");
                    input.nextLine();
                    department.Name = input.nextLine();
                    break;
                case 2:
                    System.out.println("Location: " + department.Location);
                    System.out.print("New value:");
                    input.nextLine();
                    department.Location = input.nextLine();
                    break;
                default:
                    return;
            }
            try {
                MenuPrinter.print(properties, printWidth);
            } catch (Exception e) {
                System.out.print(e.getMessage());
                return;
            }

            System.out.print("Enter option number:");
        }
    }

    /**
     * Add a new Department to the DepartmentList from int input Scanner
     *
     * @param departmentList the departments to add to
     */
    public static void addNewDepartment(ArrayList<Department> departmentList) {
        Scanner input = new Scanner(System.in);

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Location: ");
        String location = input.nextLine();

        int id = highestId(departmentList) + 1;

        departmentList.add(new Department(id, name, location));
    }

    /**
     * Gets the highest Id in a department array
     *
     * @param departmentList the array of departments
     * @return The highest ID found
     */
    private static int highestId(ArrayList<Department> departmentList) {
        int highest = 0;
        for (Department department : departmentList) {
            if (department.Id > highest) {
                highest = department.Id;
            }
        }
        return highest;
    }

    private static ArrayList<String> getPropertyNames() {
        ArrayList<String> properties = new ArrayList<>();
        properties.add("Name");
        properties.add("Location");
        return properties;
    }

}
