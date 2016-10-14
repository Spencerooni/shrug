import java.util.ArrayList;

public class BU {

    private int ID;
    private String name;
    private ArrayList<String> projects = new ArrayList<>();

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getProjects() { return projects; }
    public void addProject(String project) { this.projects.add(project); }

}