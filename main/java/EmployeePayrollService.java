import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
    ;
    private static final String FILE_PATH = "C:\\Users\\nitish\\nitish.txt";

    public EmployeePayrollService() {
        employeePayrollList = new ArrayList<>();
    }

    public static void main(String[] args) {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        Scanner consoleInputReader = new Scanner(System.in);

        for (int i = 0; i < 2; i++) {
            employeePayrollService.readEmployeePayrollData(consoleInputReader);
            employeePayrollService.writeEmployeePayrollData();
            employeePayrollService.printData(IOService.FILE_IO);
        }

        System.out.println(employeePayrollService.countEntries(IOService.CONSOLE_IO));

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

    public void writeEmployeePayrollData() {
        checkFile();
        StringBuffer empBuffer = new StringBuffer();
        employeePayrollList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get(FILE_PATH), empBuffer.toString().getBytes());
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // method to create file if file doesn't exist
    private void checkFile() {
        File file = new File(FILE_PATH);
        try {

            if (!file.exists()) {

                file.createNewFile();
                System.out.println("Created a file at " + FILE_PATH);
            }
        } catch (IOException e1) {
            System.err.println("Problem encountered while creating a file");
        }
    }

    // method to print content of file
    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIoService().printData();

    }

    // method to count entries
    public long countEntries(IOService ioService) {
        long entries = 0;
        try {
            entries = Files.lines(new File(FILE_PATH).toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
}
