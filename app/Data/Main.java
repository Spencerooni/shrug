package Data;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //userStory2();
    }

    public String userStory2() {

        Company comp = getComp();

        return

        ("\nEmployees | Type | Projects\n") +

        comp.getEmployees().stream().map(i ->
                (i.getName()) + " | " + (i.getJobTitle()) + " | "
                        + (i.getProjects().stream().map(s -> (s + " ")).collect(Collectors.joining(" "))) + ("\n")).collect(Collectors.joining("<br>"))

        + ("<br>BU | Projects\n") +

        comp.getBUs().stream().map(ij -> (ij.getName() + " | ") + ij.getProjects().stream().map(s -> s.toString()).collect(Collectors.joining(" ")) + " " + ("\n")).collect(Collectors.joining("<br>"));

    }

    public Company getComp() {
        Company comp = new Company();
        try{
            dbo dbo = new dbo();
            comp = dbo.getCompany();
        } catch (Exception e) {}
        return comp;
    }

    public String BUReport() {
        Company comp = getComp();
        return ("<br>BU | Projects<br>") +

                comp.getBUs().stream().map(ij -> (ij.getName() + " | ") + ij.getProjects().stream().map(s -> s.toString()).collect(Collectors.joining(" ")) + " " + ("\n")).collect(Collectors.joining("<br>"));

    }

    public String EmployeeReport() {
        Company comp = getComp();
        return ("<br>Employees | Type | Projects<br>") +
                comp.getEmployees().stream().map(i ->
                        (i.getName()) + " | " + (i.getJobTitle()) + " | "
                                + (i.getProjects().stream().map(s -> (s + " ")).collect(Collectors.joining(" "))) + ("\n")).collect(Collectors.joining("<br>"));
    }

    public String getEmpBU() {
        Company comp = getComp();

        ArrayList<Employee> em = (ArrayList<Employee>)comp.getEmployees();
        ArrayList<BU> bu = (ArrayList<BU>)comp.getBUs();

        String result = "";

        for (BU b : bu ) {
            result += "<br> " + b.getName() + "<br>";
            for (String s : b.getProjects()) {
                result += s + " ";
                for (Employee e : em) {
                    if (e.getProjects().stream().filter(u -> u.equals(s)).count() > 0) {
                        result += e.getName() + " ";
                    }
                }
            }
        }
        return result;
    }

    public String getEmpSal() {
        Company comp = getComp();

        ArrayList<Employee> em = (ArrayList<Employee>)comp.getEmployees();
        String result = "Employee | Gross Pay <br>";

        for (Employee e : em) {
            result += e.getName();
            int pay = e.getStartingSalary() + (e.getCommissionRate()*e.getTotalSales());
            result += " | £" + pay + "<br>";
        }
        return  result;
    }

    public String getHighest() {
        Company comp = getComp();

        ArrayList<Employee> em = (ArrayList<Employee>)comp.getEmployees();
        String result = "Sales Employee with Highest Sales <br>";
        int sales = 0;
        String se = "";
        for(Employee e : em ) {
            se = (e.getTotalSales() > sales) ? e.getName() : se;
            sales = (e.getTotalSales() > sales) ? e.getTotalSales() : sales;
        }
        result += se + " with " + sales;
        return  result;
    }

    public String getProjects(){
        Company comp = getComp();
        ArrayList<Project> pr = (ArrayList<Project>)comp.getProjects();
        ArrayList<Employee> em = (ArrayList<Employee>)comp.getEmployees();

        String result = "Project: <select>";

        for (Project p : pr) {
            result += "<option>" + p.getName() + "</option>";
        }
        result += "</select>";

        result += " Employee: <select>";

        for (Employee e : em) {
            result += "<option>" + e.getName() + "</option>";
        }
        result += "</select> <button>add</button> <br><br>";


        result += "Project | Employee Count <br>";

        for (Project p : pr) {
            result += p.getName() + " | ";
            int count = 0;
            for (Employee e : em) {
                if (e.getProjects().contains(p.getName())) {
                    count++;
                }
            }
            result += count + "<br>";
        }

        result += "<br> Employees | Project Count<br>" + em.stream().map(i -> i.getName() + " " + i.getProjects().size()).collect(Collectors.joining("<br>"));

        return result;


    }
}