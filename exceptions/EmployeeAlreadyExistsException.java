package exceptions;

/**
 * Exception thrown when registering an employee whose ID is already registered.
 */
public class EmployeeAlreadyExistsException extends Exception {
  public EmployeeAlreadyExistsException(String message) {
    super(message);
  }

}
