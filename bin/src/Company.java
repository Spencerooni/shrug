
import java.util.List;

public class Company {
    private List<Employee> employees;
    private List<BU> BUs;
    private List<EmployeeProject> employeeProject;
    private List<JobTitle> jobTitles;
    private List<Project> projects;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<BU> getBUs() {
        return BUs;
    }

    public void setBUs(List<BU> BUs) {
        this.BUs = BUs;
    }

    public List<EmployeeProject> getEmployeeProject() {
        return employeeProject;
    }

    public void setEmployeeProject(List<EmployeeProject> employeeProject) {
        this.employeeProject = employeeProject;
    }

    public List<JobTitle> getJobTitles() {
        return jobTitles;
    }

    public void setJobTitles(List<JobTitle> jobTitles) {
        this.jobTitles = jobTitles;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}