import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 2024L;

    private int employeeID;
    private String name;

    // Sensitive field
    private transient double baseSalary;

    // New field for versioning test
    private String department;

    public Employee(int employeeID,
                    String name,
                    double baseSalary,
                    String department) {

        this.employeeID = employeeID;
        this.name = name;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {

        return "Employee ID: " + employeeID +
                "\nName: " + name +
                "\nSalary: " + baseSalary +
                "\nDepartment: " + department;
    }
}