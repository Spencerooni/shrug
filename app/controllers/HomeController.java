package controllers;

import Data.Company;
import Data.Main;
import play.mvc.*;

import play.twirl.api.Html;
import views.html.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    Collection<String> collection = new ArrayList<>();


    public Result index() {

        collection.add("a");
        collection.add("b");
        collection.add("c");
        collection.add("d");

        String queryData = new Main().userStory2();

        Html html = Html.apply("<h1>KDB</h1>" +
                "<p>"+ queryData + "</p>");

        return ok(main.render("KDB", html));
    }

    public Result login() {

        Html html = Html.apply("<h1>KDB</h1>"  +
                "<button onClick=\"window.location.href = '/hr';\">Act as HR</button> <br> " +
                "<button onClick=\"window.location.href = '/fin';\">Act as Financial</button> <br> " +
                "<button onClick=\"window.location.href = '/man';\">Act as Manager</button> <br> " +
                "<button onClick=\"window.location.href = '/tm';\">Act as Talent Manager</button> <br> " +
                "<button onClick=\"window.location.href = '/employeereport';\">Employees</button> <br> " +
                "<button onClick=\"window.location.href = '/bureport';\">BUs</button>");

        return ok(main.render("KDB", html));

    }

    public Result em() {

        String queryData = new Main().EmployeeReport();

        Html html = Html.apply("<h1>KDB</h1><p>Employee Report</p>" +  "<button onClick=\"window.location.href = '/';\">Back</button> <br> " +
                "<p>"+ queryData + "</p>");

        return ok(main.render("KDB", html));

    }

    public Result bu() {

        String queryData = new Main().BUReport();

        Html html = Html.apply("<h1>KDB</h1><p>BU Report</p>" + "<button onClick=\"window.location.href = '/';\">Back</button> <br> " +
                "<p>"+ queryData + "</p>");

        return ok(main.render("KDB", html));

    }

    public Result hr() {
        String queryData = new Main().getEmpBU();

        Html html = Html.apply("<h1>KDB</h1><p>Acting as HR</p>" +
                "<button onClick=\"window.location.href = '/';\">Back</button> <br> " +
                "<p>Add Employee:<br> <input placeholder = \"Name\"></input><input placeholder = \"Address\"></input><input placeholder = \"NIN\"></input><input placeholder = \"Bank Account\"></input><input placeholder = \"Starting Salary\"><br></input><button>Add</button></p>" +
                "<p>Add Sales:<br> <input placeholder = \"Name\"></input><input placeholder = \"Address\"></input><input placeholder = \"NIN\"></input><input placeholder = \"Bank Account\"></input><input placeholder = \"Starting Salary\"></input><input placeholder = \"Commission Rate\"></input><br><button>Add</button></p>" +
                "" + queryData);

        return ok(main.render("KDB", html));
    }

    public Result fin(){
        String queryData = new Main().getEmpSal();

        Html html = Html.apply("<h1>KDB</h1><p>Acting as Finance</p>" + "<button onClick=\"window.location.href = '/';\">Back</button> <br> " +
                "<p>"+ queryData + "</p>");

        return ok(main.render("KDB", html));
    }

    public Result man() {
        String queryData = new Main().getHighest();

        Html html = Html.apply("<h1>KDB</h1><p>Acting as Manager</p>" + "<button onClick=\"window.location.href = '/';\">Back</button> <br> " +
                "<p>"+ queryData + "</p><br><button>High Five</button>");

        return ok(main.render("KDB", html));
    }

    public Result tm() {
        String queryData = new Main().getProjects();

        Html html = Html.apply("<h1>KDB</h1><p>Acting as Talent Manager</p>" + "<button onClick=\"window.location.href = '/';\">Back</button> <br> " +
                "<p>"+ queryData + "</p>");

        return ok(main.render("KDB", html));
    }

}
