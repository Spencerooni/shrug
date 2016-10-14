import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by liamf on 13/10/2016.
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Name: ");
        scanner.next();

        try{
            dbo dbo = new dbo();
            Company comp = dbo.getCompany();

            int commissionRate = 0;
            int totalSales = 0;
            int employeeTypeID = 0;
            System.out.println("Enter the name of the employee to be added");
            String name = scanner.next();
            System.out.println("Enter the address");
            String address = scanner.next();
            System.out.println("Enter the National Insurance Number");
            String nationalInsuranceNumber = scanner.next();
            System.out.println("Enter the bank account IBAN");
            String IBAN = scanner.next();
            System.out.println("Is the new employee a sales employee?");
            String salesResponse = scanner.next().toLowerCase();
            if (salesResponse.equals("yes") || salesResponse.equals("y")) {
                employeeTypeID = 3;
                System.out.println("Enter the employee's sales total");
                totalSales = scanner.nextInt();
                System.out.println("Enter the employee's commission rate in %");
                commissionRate = scanner.nextInt();
            }
            else if (salesResponse.equals("no") || salesResponse.equals("n")){
                System.out.println("Enter the employee's job title ID");
                employeeTypeID = scanner.nextInt();
            }
            else {
                System.out.println("Please enter \"yes\" or \"no\"");
            }
            System.out.println("Enter the employee's starting salary");
            int startingSalary = scanner.nextInt();
            dbo.insertEmployee(name, address, nationalInsuranceNumber, IBAN, startingSalary, employeeTypeID, commissionRate, totalSales);

            comp = dbo.getCompany();

            for (Employee x : comp.getEmployees()) {
                System.out.println(x.getName());
            }

            System.out.println("Enter the name of the project to be added");
            String projectName = scanner.next();
            System.out.println("Enter the Business unit ID");
            int buid = scanner.nextInt();
            dbo.insertProject(projectName, buid);

            comp = dbo.getCompany();

            for (Project x : comp.getProjects()) {
                System.out.println(x.getName());
            }
        }
        catch (Exception e) {

        }

    }
}
