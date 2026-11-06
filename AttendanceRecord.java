import java.time.LocalDateTime;

/**
 * Represents an employee's attendance record with time-in and time-out details.
 */
public class AttendanceRecord {
  private String employeeId;
  private LocalDateTime timeIn;
  private LocalDateTime timeOut;

  /**
   * Constructs an AttendanceRecord with an employee ID and initial time-in.
   *
   * @param employeeId the unique identifier of the employee
   * @param timeIn the timestamp when the employee timed in
   */
  public AttendanceRecord(String employeeId, LocalDateTime timeIn) {
    this.employeeId = employeeId;
    this.timeIn = timeIn;
    this.timeOut = null;
  }

  /**
   * Gets the employee ID associated with this record.
   *
   * @return the employee ID
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * Gets the time-in timestamp.
   *
   * @return the LocalDateTime of time-in
   */
  public LocalDateTime getTimeIn() {
    return timeIn;
  }

  /**
   * Gets the time-out timestamp.
   *
   * @return the LocalDateTime of time-out, or null if active
   */
  public LocalDateTime getTimeOut() {
    return timeOut;
  }

  /**
   * Sets the time-out timestamp.
   *
   * @param timeOut the LocalDateTime of time-out
   */
  public void setTimeOut(LocalDateTime timeOut) {
    this.timeOut = timeOut;
  }

}
