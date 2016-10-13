/**
 * Created by liamf on 13/10/2016.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbo {

    public void getCompany() {
        Connection conn;

        List<Employee> employees = new ArrayList<Employee>();
        List<Project> projects= new ArrayList<Project>();
        List<BU> BUs = new ArrayList<BU>();
        List<JobTitle> jobTitles = new ArrayList<JobTitle>();
        List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();

        Company company = new Company();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Company?useSSL=false", "root", "password");

            PreparedStatement prepEmployee = conn.prepareStatement("Select * from Employees order by Employee_number");
            PreparedStatement prepProject = conn.prepareStatement("Select * from Employees order by Employee_number");
            PreparedStatement prepBU = conn.prepareStatement("Select * from Employees order by Employee_number");
            PreparedStatement prepJobTitles = conn.prepareStatement("Select * from Employees order by Employee_number");
            PreparedStatement prepEmployeeProject = conn.prepareStatement("Select * from Employees order by Employee_number");

            ResultSet employeeQuery = prepEmployee.executeQuery();
            ResultSet projectQuery = prepProject.executeQuery();
            ResultSet BUQuery = prepBU.executeQuery();
            ResultSet jobTitleQuery = prepJobTitles.executeQuery();
            ResultSet employeeProjectQuery = prepEmployeeProject.executeQuery();

            while(employeeQuery.next()){
                Employee employee = new Employee();
                employee.setNumber(employeeQuery.getInt("Employee_number"));
                employee.setName(employeeQuery.getString("Employee_name"));
                employee.setAddress(employeeQuery.getString("Address"));
                employee.setNationalInsuranceNumber(employeeQuery.getString("National_insurance_number"));
                employee.setBankAccountIBAN(employeeQuery.getString("Bank_account_IBAN"));
                employee.setStartingSalary(employeeQuery.getInt("Starting_salary"));
                employee.setEmployeeTypeID(employeeQuery.getInt("Employee_type_id"));
                employee.setCommissionRate(employeeQuery.getInt("Comission_rate"));
                employee.setTotalSales(employeeQuery.getInt("Total_sales"));

                employees.add(employee);
            }

            while(projectQuery.next()){
                Project project = new Project();
                project.setID(projectQuery.getInt("Project_id"));
                project.setName(projectQuery.getString("Project_name"));
                project.setBUID(projectQuery.getInt("BU_id"));

                projects.add(project);
            }

            while(BUQuery.next()) {
                BU bu = new BU();
                bu.setID(BUQuery.getInt("BU_id"));
                bu.setName(BUQuery.getString("BU_name"));

                BUs.add(bu);
            }

            while(jobTitleQuery.next()) {
                JobTitle jobTitle = new JobTitle();
                jobTitle.setID(jobTitleQuery.getInt("Employee_type_id"));
                jobTitle.setJobTitle(jobTitleQuery.getString("Job_title"));

                jobTitles.add(jobTitle);
            }

            while(employeeProjectQuery.next()) {
                EmployeeProject employeeProject = new EmployeeProject();
                employeeProject.setProjectID(employeeProjectQuery.getInt("Project_id"));
                employeeProject.setEmployeeNumber(employeeProjectQuery.getInt("Employee_number"));
            }

            company.setBUs(BUs);
            company.setEmployeeProject(employeeProjects);
            company.setEmployees(employees);
            company.setJobTitles(jobTitles);
            company.setProjects(projects);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
