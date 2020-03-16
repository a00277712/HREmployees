package model;

import io.HeaderPrinter;
import io.MenuPrinter;
import io.TablePrinter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Department Employee
 */
public class Employee {
    public int Id;
    public String FirstName;
    public String LastName;
    public String Email;
    public String PhoneNumber;
    public String Salary;
    public int DepartmentId;

    /**
     * A Department Employee
     * @param id The unique Identifier
     * @param firstName The Employees First Name
     * @param lastName The Employees Last Name
     * @param email The Employees Email Address
     * @param phoneNumber The Employees Phone number
     * @param salary The Employees Salary
     * @param departmentId The Identifier for the department the employee belongs
     */
    public Employee(int id, String firstName, String lastName, String email, String phoneNumber, String salary, int departmentId) {
        this.Id = id;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhoneNumber = phoneNumber;
        Salary = salary;
        DepartmentId = departmentId;
    }

    /**
     * A Department Employee
     * @param id The unique Identifier
     * @param firstName The Employees First Name
     * @param lastName The Employees Last Name
     * @param email The Employees Email Address
     * @param phoneNumber The Employees Phone number
     * @param salary The Employees Salary
     * @param departmentId The Identifier for the department the employee belongs
     */
    public Employee(String id, String firstName, String lastName, String email, String phoneNumber, String salary, String departmentId) {
        this(Integer.parseInt(id), firstName, lastName, email, phoneNumber, salary, Integer.parseInt(departmentId));
    }

    /**
     * A Department Employee
     * @param values All values passed as an array with Id, firstName, lastName, email, phoneNumber, salary, departmentId
     */
    public Employee(ArrayList<String> values) {
        this(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6));
    }

    /**
     * Print the Department in a table
     * @param printWidth The total width of the print area
     */
    public void print(int printWidth) {
        try {
            HeaderPrinter.print(getFullName(), printWidth);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }

        TablePrinter.printDivider(printWidth);

        TablePrinter.printLine("First Name", FirstName, printWidth);
        TablePrinter.printLine("Last Name", LastName, printWidth);
        TablePrinter.printLine("Email", Email, printWidth);
        TablePrinter.printLine("Phone Number", PhoneNumber, printWidth);
        TablePrinter.printLine("Salary", Salary, printWidth);

        TablePrinter.printDivider(printWidth);
    }

    private String getFullName() {
        return FirstName + " " + LastName;
    }

    private ArrayList<String> getPropertyNames() {
        ArrayList<String> options = new ArrayList<>();
        options.add("First Name");
        options.add("Last Name");
        options.add("Email");
        options.add("Phone Number");
        options.add("Salary");
        return options;
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
     * @param printWidth The total width of the print area
     */
    public void modifyEntry(int printWidth) {
        Scanner input = new Scanner(System.in);

        if (printModifyOptions(printWidth)) {
            return;
        }

        while(input.hasNextInt()) {
            switch (input.nextInt()) {
                case 1:
                    System.out.println("First Name: " + FirstName);
                    System.out.print("New value:");
                    input.nextLine();
                    FirstName = input.nextLine();
                    break;
                case 2:
                    System.out.println("Last Name: " + LastName);
                    System.out.print("New value:");
                    input.nextLine();
                    LastName = input.nextLine();
                    break;
                case 3:
                    System.out.println("Email: " + Email);
                    System.out.print("New value:");
                    input.nextLine();
                    Email = input.nextLine();
                    break;
                case 4:
                    System.out.println("Phone Number: " + PhoneNumber);
                    System.out.print("New value:");
                    input.nextLine();
                    PhoneNumber = input.nextLine();
                    break;
                case 5:
                    System.out.println("Salary: " + Salary);
                    System.out.print("New value:");
                    input.nextLine();
                    Salary = input.nextLine();
                    break;
                default:
                    return;
            }

            if (printModifyOptions(printWidth)) {
                return;
            }
        }
    }

    private boolean printModifyOptions(int printWidth) {
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

    private ArrayList<String> toArrayStrings() {
        ArrayList<String> itemStrings = new ArrayList<>();

        itemStrings.add(String.valueOf(Id));
        itemStrings.add(FirstName);
        itemStrings.add(LastName);
        itemStrings.add(Email);
        itemStrings.add(PhoneNumber);
        itemStrings.add(Salary);
        itemStrings.add(String.valueOf(DepartmentId));

        return itemStrings;
    }

    /**
     * Converts a given array of Employees as Arrays of strings
     * @param employeeList the Employees to convert
     * @return The Employee as an array of strings
     */
    public static ArrayList<ArrayList<String>> toArrayStrings(ArrayList<Employee> employeeList) {
        ArrayList<ArrayList<String>> values = new ArrayList<>();

        for (Employee employee : employeeList) {
            values.add(employee.toArrayStrings());
        }

        return values;
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

        int id = Employee.highestId(department.EmployeeList) + 1;

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

    @Override
    public String toString() {
        return "Employee{" +
                "Id=" + Id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Salary='" + Salary + '\'' +
                ", DepartmentId=" + DepartmentId +
                '}';
    }
}
