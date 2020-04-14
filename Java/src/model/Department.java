package model;

import java.util.ArrayList;

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
