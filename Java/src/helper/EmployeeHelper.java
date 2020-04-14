package helper;

import java.util.ArrayList;
import java.util.Scanner;

import io.HeaderPrinter;
import io.MenuPrinter;
import io.TablePrinter;
import model.Department;
import model.Employee;

public class EmployeeHelper {

    /**
     * Print the Department in a table
     * @param employee The employee to print
     * @param printWidth The total width of the print area
     */
    public static void print(Employee employee, int printWidth) {
        try {
            HeaderPrinter.print(employee.getFullName(), printWidth);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }

        TablePrinter.printDivider(printWidth);

        TablePrinter.printLine("First Name", employee.FirstName, printWidth);
        TablePrinter.printLine("Last Name", employee.LastName, printWidth);
        TablePrinter.printLine("Email", employee.Email, printWidth);
        TablePrinter.printLine("Phone Number", employee.PhoneNumber, printWidth);
        TablePrinter.printLine("Salary", employee.Salary, printWidth);

        TablePrinter.printDivider(printWidth);
    }


    /**
     * Prints the Menu to select an Employee
     * @param employeeList The employees available
     * @param printWidth The total width of the print area
     */
    public static void printMenu(ArrayList<Employee> employeeList, int printWidth) {
        try {
            HeaderPrinter.print("List of Employees", printWidth);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }

        ArrayList<String> options = new ArrayList<>();

        for (Employee employee : employeeList) {
            options.add(employee.getFullName());
        }

        options.add("Add Employee");

        try {
            MenuPrinter.print(options, printWidth);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }
        System.out.print("Enter option number:");
    }

    /**
     * Modify this Employee
     *
     * @param employee The employee to modify
     * @param printWidth The total width of the print area
     */
    public static void modify(Employee employee, int printWidth) {
        Scanner input = new Scanner(System.in);

        if (printModifyOptions(printWidth)) {
            return;
        }

        while(input.hasNextInt()) {
            switch (input.nextInt()) {
                case 1:
                    System.out.println("First Name: " + employee.FirstName);
                    System.out.print("New value:");
                    input.nextLine();
                    employee.FirstName = input.nextLine();
                    break;
                case 2:
                    System.out.println("Last Name: " + employee.LastName);
                    System.out.print("New value:");
                    input.nextLine();
                    employee.LastName = input.nextLine();
                    break;
                case 3:
                    System.out.println("Email: " + employee.Email);
                    System.out.print("New value:");
                    input.nextLine();
                    employee.Email = input.nextLine();
                    break;
                case 4:
                    System.out.println("Phone Number: " + employee.PhoneNumber);
                    System.out.print("New value:");
                    input.nextLine();
                    employee.PhoneNumber = input.nextLine();
                    break;
                case 5:
                    System.out.println("Salary: " + employee.Salary);
                    System.out.print("New value:");
                    input.nextLine();
                    employee.Salary = input.nextLine();
                    break;
                default:
                    return;
            }

            if (printModifyOptions(printWidth)) {
                return;
            }
        }
    }

    private static boolean printModifyOptions(int printWidth) {
        ArrayList<String> properties = getPropertyNames();

        // Prints out the options
        try {
            MenuPrinter.print(properties, printWidth);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return true;
        }
        System.out.print("Enter option number:");
        return false;
    }

    private static ArrayList<String> getPropertyNames() {
        ArrayList<String> options = new ArrayList<>();
        options.add("First Name");
        options.add("Last Name");
        options.add("Email");
        options.add("Phone Number");
        options.add("Salary");
        return options;
    }

    /**
     * Add a new Employee to the EmployeeList from int input Scanner
     *
     * @param department the department to add an employee to
     */
    public static void AddNewEmployee(Department department){
        Scanner input = new Scanner(System.in);

        System.out.print("FirstName: ");
        String firstName = input.nextLine();

        System.out.print("LastName: ");
        String lastName = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("PhoneNumber: ");
        String phoneNumber = input.nextLine();

        System.out.print("Salary: ");
        String salary = input.nextLine();

        int id = highestId(department.EmployeeList) + 1;

        department.EmployeeList.add(new Employee(id, firstName, lastName, email, phoneNumber, salary, department.Id));
    }

    /**
     * The highest Identity in the array of employees
     * @param employeeList The array of employees to look through
     * @return The highest Id
     */
    public static int highestId(ArrayList<Employee> employeeList) {
        int highest = 0;
        for (Employee employee : employeeList) {
            if (employee.Id > highest) {
                highest = employee.Id;
            }
        }
        return highest;
    }
}
