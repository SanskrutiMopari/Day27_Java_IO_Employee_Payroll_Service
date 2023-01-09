import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollImpl {
    public static enum IOService {
        FILE_IO, CONSOLE_IO, DB_IO, REST_IO
    }

    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();;

    public EmployeePayrollImpl() {
        employeePayrollList = new ArrayList<>();
    }

    public static void main(String[] args) {
        EmployeePayrollImpl employeePayrollService = new EmployeePayrollImpl();
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData();

    }

    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee Name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    private void writeEmployeePayrollData() {
        System.out.println("\nWriting Employee Payroll to console\n" + employeePayrollList);
    }
}
