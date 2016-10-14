package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by liamf on 13/10/2016.
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Company?useSSL=false", "root", "password");
            PreparedStatement prepEmployee = conn.prepareStatement("Select * from Employees order by Employee_number");
            //PreparedStatement prepEdddmployee = conn.prepareStatement("Select Employee_name, BU_name from Employees join EmployeeProject using (Employee_number) join Project using (Project_id) join BU using (BU_id) group by BU_name");

            Company comp = new Company();
            database dbo = new database();
            comp = dbo.getCompany();

            System.out.println("\nEmployees | Type | Projects\n");

            comp.getEmployees().stream().forEach(i -> {
                System.out.print(i.getName() + " " + i.getJobTitle() + ", ");
                i.getProjects().stream().forEach(s -> System.out.print(s + ", "));
                System.out.print("\n");
            });

            System.out.println("\nBU | Projects\n");

            comp.getBUs().stream().forEach(i -> {
                System.out.print(i.getName() + ", ");
                i.getProjects().stream().forEach(s -> System.out.print(s + ", "));
                System.out.print("\n");
            });

        } catch (Exception e) {
            System.out.println(e.getCause());
        }

    }

    public String getThing() {

        String queryData = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Company?useSSL=false", "root", "password");
            PreparedStatement prepEmployee = conn.prepareStatement("Select * from Employees order by Employee_number");
            //PreparedStatement prepEdddmployee = conn.prepareStatement("Select Employee_name, BU_name from Employees join EmployeeProject using (Employee_number) join Project using (Project_id) join BU using (BU_id) group by BU_name");

            Company comp = new Company();
            database dbo = new database();
            comp = dbo.getCompany();

            queryData = ("\nEmployees | Type | Projects\n");

            queryData += comp.getEmployees().stream()
                    .map(i -> i.getName() + " " + i.getJobTitle() + ", " + i.getProjects().stream().collect(Collectors.joining(", ")))
                    .collect(Collectors.joining("\n"));

//            System.out.println("\nBU | Projects\n");
//
//            comp.getBUs().stream().forEach(i -> {
//                System.out.print(i.getName() + ", ");
//                i.getProjects().stream().forEach(s -> System.out.print(s + ", "));
//                System.out.print("\n");
//            });



        } catch (Exception e) {
        }

        return queryData;
    }

}
