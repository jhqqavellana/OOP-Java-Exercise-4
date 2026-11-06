import exceptions.EmployeeAlreadyExistsException;
import exceptions.EmployeeNotFoundException;
import exceptions.InvalidAttendanceException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages employee registration and attendance records.
 */
public class AttendanceManager {

  private final List<Employee> employees;
  private final List<AttendanceRecord> attendanceRecords;

  /**
   * Constructs an AttendanceManager instance initializing employee and attendance lists.
   */
  public AttendanceManager() {
    this.employees = new ArrayList<>();
    this.attendanceRecords = new ArrayList<>();
  }

  /**
   * Registers a new employee into the system.
   *
   * @param employee the Employee object to register
   * @throws EmployeeAlreadyExistsException if an employee with the same ID already exists
   */
  public void registerEmployee(Employee employee) throws EmployeeAlreadyExistsException {
    for (Employee emp : employees) {
      if (emp.getEmployeeId().equals(employee.getEmployeeId())) {
        throw new EmployeeAlreadyExistsException(
            "Employee with ID " + employee.getEmployeeId() + " already exists.");
      }
    }
    employees.add(employee);
  }

  /**
   * Finds an employee by their ID.
   *
   * @param employeeId the unique identifier of the employee
   * @return the matching Employee instance
   * @throws EmployeeNotFoundException if no employee is found with the given ID
   */
  public Employee findEmployeeById(String employeeId) throws EmployeeNotFoundException {
    for (Employee emp : employees) {
      if (emp.getEmployeeId().equals(employeeId)) {
        return emp;
      }
    }
    throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
  }

  /**
   * Records a time-in timestamp for an employee.
   *
   * @param employeeId the unique identifier of the employee
   * @throws EmployeeNotFoundException if the employee ID does not exist
   * @throws InvalidAttendanceException if the employee already has an active time-in
   */
  public void timeIn(String employeeId)
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);

    for (AttendanceRecord record : attendanceRecords) {
      if (record.getEmployeeId().equals(employeeId) && record.getTimeOut() == null) {
        throw new InvalidAttendanceException(
            "Employee " + employeeId + " already has an active time-in record.");
      }
    }
    attendanceRecords.add(new AttendanceRecord(employeeId, LocalDateTime.now()));
  }

  /**
   * Records a time-out timestamp for an employee's active record.
   *
   * @param employeeId the unique identifier of the employee
   * @throws EmployeeNotFoundException if the employee ID does not exist
   * @throws InvalidAttendanceException if no active time-in record is found
   */
  public void timeOut(String employeeId)
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);

    AttendanceRecord activeRecord = null;
    for (AttendanceRecord record : attendanceRecords) {
      if (record.getEmployeeId().equals(employeeId) && record.getTimeOut() == null) {
        activeRecord = record;
        break;
      }
    }

    if (activeRecord == null) {
      throw new InvalidAttendanceException(
          "Employee " + employeeId + " does not have an active time-in record to time-out.");
    }

    activeRecord.setTimeOut(LocalDateTime.now());
  }

  /**
   * Retrieves all completed attendance records for a given employee.
   *
   * @param employeeId the unique identifier of the employee
   * @return a List of completed AttendanceRecord objects
   * @throws EmployeeNotFoundException if the employee ID does not exist
   */
  public List<AttendanceRecord> getCompletedAttendanceRecords(String employeeId)
      throws EmployeeNotFoundException {
    findEmployeeById(employeeId);

    return attendanceRecords.stream()
        .filter(record -> record.getEmployeeId().equals(employeeId))
        .filter(record -> record.getTimeOut() != null)
        .collect(Collectors.toList());
  }
}