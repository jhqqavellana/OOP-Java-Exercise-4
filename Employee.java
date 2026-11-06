/**
 * Represents an employee in the system.
 */
public class Employee {
  private String employeeId;
  private String name;
  private String department;

  /**
   * Constructs a new Employee with specified details.
   *
   * @param employeeId the unique identifier of the employee
   * @param name the full name of the employee
   * @param department the department assigned to the employee
   */
  public Employee(String employeeId, String name, String department) {
    this.employeeId = employeeId;
    this.name = name;
    this.department = department;
  }

  /**
   * Gets the unique employee ID.
   *
   * @return the employee ID
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * Gets the full name of the employee.
   *
   * @return the employee name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the department of the employee.
   *
   * @return the department name
   */
  public String getDepartment() {
    return department;
  }
  
}
