package model;

import io.HeaderPrinter;
import io.MenuPrinter;
import io.TablePrinter;

import java.util.ArrayList;
import java.util.Scanner;

public class Department {
    public int Id;
    public String Name;
    public String Location;
    public ArrayList<Employee> EmployeeList;

    /**
     * A Company Department
     *
     * @param id       The identity key
     * @param name     The name of the Department
     * @param location The location of the Department
     */
    public Department(int id, String name, String location) {
        Id = id;
        Name = name;
        Location = location;
        EmployeeList = new ArrayList<>();
    }

    /**
     * A Company Department
     *
     * @param id       The identity key
     * @param name     The name of the Department
     * @param location The location of the Department
     */
    public Department(String id, String name, String location) {
        this(Integer.parseInt(id), name, location);
    }

    /**
     * A Company Department
     *
     * @param values the array of values with Id, Name, Location
     */
    public Department(ArrayList<String> values) {
        this(values.get(0), values.get(1), values.get(2));
    }

    /**
     * A Company Department
     *
     * @param id        The identity key
     * @param name      The name of the Department
     * @param location  The location of the Department
     * @param employees All employees
     */
    public Department(String id, String name, String location, ArrayList<Employee> employees) {
        this(id, name, location);
        EmployeeList = employees;
    }

    /**
     * Add an employee to the department, be careful with id and departmentId
     *
     * @param employee The employee to add
     */
    public void addEmployee(Employee employee) {
        if (EmployeeList == null) {
            EmployeeList = new ArrayList<>();
        }
        EmployeeList.add(employee);
    }

    /**
     * Prints the Department in a table
     *
     * @param printLength number of characters to go across the screen
     */
    public void print(int printLength) throws Exception {
        HeaderPrinter.print(Name, printLength);

        TablePrinter.printDivider(printLength);

        TablePrinter.printLine("Location", Location, printLength);
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
     * @param printWidth The width of the print area
     */
    public void modifyEntry(int printWidth) {
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
                    System.out.println("Name: " + Name);
                    System.out.print("New value:");
                    input.nextLine();
                    Name = input.nextLine();
                    break;
                case 2:
                    System.out.println("Location: " + Location);
                    System.out.print("New value:");
                    input.nextLine();
                    Location = input.nextLine();
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

    private ArrayList<String> getPropertyNames() {
        ArrayList<String> properties = new ArrayList<>();
        properties.add("Name");
        properties.add("Location");
        return properties;
    }

    private ArrayList<String> toArrayStrings() {
        ArrayList<String> itemString = new ArrayList<>();

        itemString.add(String.valueOf(Id));
        itemString.add(Name);
        itemString.add(Location);

        return itemString;
    }

    /**
     * Returns Departments as an array of strings
     *
     * @param departmentList The Departments to be converted
     * @return The array of department values
     */
    public static ArrayList<ArrayList<String>> toArrayStrings(ArrayList<Department> departmentList) {
        ArrayList<ArrayList<String>> values = new ArrayList<>();

        for (Department department : departmentList) {
            values.add(department.toArrayStrings());
        }

        return values;
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

        int id = Department.highestId(departmentList) + 1;

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

    @Override
    public String toString() {
        StringBuilder employeesString = new StringBuilder();
        for ( Employee employee : EmployeeList) {
            employeesString.append('{');
            employeesString.append(employee.toString());
            employeesString.append('}');
        }

        return "Department{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Location='" + Location + '\'' +
                ", EmployeeList=" + employeesString.toString() +
                '}';
    }
}
