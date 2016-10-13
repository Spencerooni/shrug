import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by liamf on 13/10/2016.
 */
public class Main {


    public static void main(String[] args) {

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Company?useSSL=false", "root", "password");
            PreparedStatement prepEmployee = conn.prepareStatement("Select * from Employees order by Employee_number");
            PreparedStatement prepEdddmployee = conn.prepareStatement("Select Employee_name, BU_name from Employees join EmployeeProject using (Employee_number) join Project using (Project_id) join BU using (BU_id) group by BU_name");
            
            Company comp = new Company();
            dbo dbo = new dbo();
            comp = dbo.getCompany();
            comp.getEmployees().stream().forEach(i -> System.out.println(i.getName()));

        } catch (Exception e) {}

    }
}
