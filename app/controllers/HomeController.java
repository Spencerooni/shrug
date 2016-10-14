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

        String queryData = new Main().getThing();

        Html html = Html.apply("<h1>KDB</h1>" +
                "<p> yeoooo"+ queryData + "</p>");

        return ok(main.render("KDB", html));
    }

    public Result login() {

        Html html = Html.apply("<h1>KDB</h1>" +
                "<button onClick=\"window.location.href = '/index';\">Login</button>");

        return ok(main.render("KDB", html));



    }

}
