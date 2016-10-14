import java.util.ArrayList;

/**
 * Created by liamf on 13/10/2016.
 */
public class Employee {

    private int number;
    private String name;
    private String address;
    private String nationalInsuranceNumber;
    private String bankAccountIBAN;
    private int startingSalary;

    private ArrayList<String> projects = new ArrayList<>();
    private String jobTitle;

    private int employeeTypeID;

    private int commissionRate;
    private int totalSales;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public String getBankAccountIBAN() {
        return bankAccountIBAN;
    }

    public void setBankAccountIBAN(String bankAccountIBAN) {
        this.bankAccountIBAN = bankAccountIBAN;
    }

    public int getEmployeeTypeID() {return employeeTypeID;}

    public void setEmployeeTypeID(int employeeTypeID) {this.employeeTypeID = employeeTypeID;}

    public int getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(int commissionRate) {
        this.commissionRate = commissionRate;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getStartingSalary() {
        return startingSalary;
    }

    public void setStartingSalary(int startingSalary) {
        this.startingSalary = startingSalary;
    }

    public ArrayList<String> getProjects() { return projects; }

    public void addProject(String project) { this.projects.add(project); }

    public String getJobTitle() { return jobTitle; }

    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
}