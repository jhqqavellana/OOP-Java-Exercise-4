package exceptions;

/**
 * Exception thrown when an employee ID does not exist in the system.
 */
public class EmployeeNotFoundException extends Exception {
  public EmployeeNotFoundException(String message) {
    super(message);
  }

}
