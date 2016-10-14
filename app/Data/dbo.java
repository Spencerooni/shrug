package Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbo {

    public Company getCompany() {
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
            PreparedStatement prepProject = conn.prepareStatement("Select * from Project order by Project_id");
            PreparedStatement prepBU = conn.prepareStatement("Select * from BU order by BU_id");
            PreparedStatement prepJobTitles = conn.prepareStatement("Select * from JobTitles order by Employee_type_id");
            PreparedStatement prepEmployeeProject = conn.prepareStatement("Select * from EmployeeProject order by Employee_number");

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
                employee.setCommissionRate(employeeQuery.getInt("Commission_rate"));
                employee.setTotalSales(employeeQuery.getInt("Total_sales"));
                employees.add(employee);
            }

            while(BUQuery.next()) {
                BU bu = new BU();
                bu.setID(BUQuery.getInt("BU_id"));
                bu.setName(BUQuery.getString("BU_name"));
                BUs.add(bu);
            }

            while(projectQuery.next()){
                Project project = new Project();

                int BUID = projectQuery.getInt("BU_id");
                String projectName = projectQuery.getString("Project_name");

                BUs.stream().filter(i -> i.getID() == BUID).forEach(i -> i.addProject(projectName));

                project.setID(projectQuery.getInt("Project_id"));
                project.setName(projectQuery.getString("Project_name"));
                project.setBUID(projectQuery.getInt("BU_id"));
                projects.add(project);
            }



            while(jobTitleQuery.next()) {
                //JobTitle jobTitle = new JobTitle();
                int EmployeeTypeID = jobTitleQuery.getInt("Employee_type_id");
                String JobTitle = jobTitleQuery.getString("Job_title");

                employees.stream().filter(i -> i.getEmployeeTypeID() == EmployeeTypeID).forEach(i -> i.setJobTitle(JobTitle));

                //jobTitles.add(jobTitle);
            }

            while(employeeProjectQuery.next()) {
                EmployeeProject employeeProject = new EmployeeProject();

                int ProjectID = employeeProjectQuery.getInt("Project_id");
                int EmployeeNumber = employeeProjectQuery.getInt("Employee_number");
                String projectName = projects.stream().filter(i -> i.getID() == ProjectID).limit(1).findFirst().get().getName();

                employeeProject.setProjectID(employeeProjectQuery.getInt("Project_id"));
                employeeProject.setEmployeeNumber(employeeProjectQuery.getInt("Employee_number"));
                employees.stream().filter(i -> i.getNumber() == EmployeeNumber).forEach(i -> i.addProject(projectName));
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

        return company;
    }
}
