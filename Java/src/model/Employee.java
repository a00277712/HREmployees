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

    public String getFullName() {
        return FirstName + " " + LastName;
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
