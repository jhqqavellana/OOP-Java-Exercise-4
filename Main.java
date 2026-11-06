import exceptions.EmployeeAlreadyExistsException;
import exceptions.EmployeeNotFoundException;
import exceptions.InvalidAttendanceException;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Demonstration class executing system requirements and test cases.
 */
public class Main {

  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  /**
   * Application entry point.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    AttendanceManager manager = new AttendanceManager();

    System.out.println("\n----- Test Case 1: Register Employees -----");
    try {
      manager.registerEmployee(new Employee("E001", "Riri Williams", "IT"));
      manager.registerEmployee(new Employee("E002", "Nick Fury", "HR"));
      System.out.println("Employees successfully registered.");
    } catch (EmployeeAlreadyExistsException e) {
      System.err.println(e.getMessage());
    }

    System.out.println("\n\n----- Test Case 2: Prevent Duplicate Registration -----");
    try {
      manager.registerEmployee(new Employee("E001", "Thor Son of Odin", "Electrical"));
    } catch (EmployeeAlreadyExistsException e) {
      System.out.println("Exception: " + e.getMessage());
    }

    System.out.println("\n\n----- Test Case 3: Find Existing Employee -----");
    try {
      Employee emp = manager.findEmployeeById("E001");
      System.out.println(
          "Found Employee: "
              + "ID: "
              + emp.getEmployeeId()
              + " | Name: "
              + emp.getName()
              + " | Department: "
              + emp.getDepartment());
    } catch (EmployeeNotFoundException e) {
      System.err.println(e.getMessage());
    }

    System.out.println("\n\n----- Test Case 4: Handle Unregistered Employee -----");
    try {
      manager.findEmployeeById("E999");
    } catch (EmployeeNotFoundException e) {
      System.out.println("Exception: " + e.getMessage());
    }

    System.out.println("\n\n----- Test Case 5: Record Time-In -----");
    try {
      manager.timeIn("E001");
      System.out.println("Time-in recorded successfully for E001.");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    System.out.println("\n\n----- Test Case 6: Prevent Multiple Active Attendance Records -----");
    try {
      manager.timeIn("E001");
    } catch (InvalidAttendanceException e) {
      System.out.println("Exception: " + e.getMessage());
    } catch (EmployeeNotFoundException e) {
      System.err.println(e.getMessage());
    }

    System.out.println("\n\n----- Test Case 7: Prevent Time-Out Without Time-In -----");
    try {
      manager.timeOut("E002");
    } catch (InvalidAttendanceException e) {
      System.out.println("Exception: " + e.getMessage());
    } catch (EmployeeNotFoundException e) {
      System.err.println(e.getMessage());
    }

    System.out.println("\n\n----- Test Case 8: Record Time-Out -----");
    try {
      Thread.sleep(1000);
      manager.timeOut("E001");
      System.out.println("Time-out recorded successfully for E001.");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    System.out.println("\n\n----- Test Case 9: Allow New Attendance Record After Completion -----");
    try {
      manager.timeIn("E001");
      System.out.println("Second time-in recorded successfully for E001.");
      Thread.sleep(1000);
      manager.timeOut("E001");
      System.out.println("Second time-out recorded successfully for E001.");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    System.out.println(
        "\n\n----- Test Case 10: Filter & Display Completed Attendance Records -----");
    try {
      List<AttendanceRecord> completed = manager.getCompletedAttendanceRecords("E001");
      System.out.println("Completed Records for E001:");
      for (AttendanceRecord record : completed) {
        String formattedIn = record.getTimeIn().format(DATE_TIME_FORMATTER);
        String formattedOut = record.getTimeOut().format(DATE_TIME_FORMATTER);
        System.out.println(
            "Employee ID: "
                + record.getEmployeeId()
                + " | Time-In: "
                + formattedIn
                + " | Time-Out: "
                + formattedOut);
      }
    } catch (EmployeeNotFoundException e) {
      System.err.println(e.getMessage());
    }
  }
}