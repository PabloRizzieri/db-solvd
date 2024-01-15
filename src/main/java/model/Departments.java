package model;

public class Departments {

    private int ID;
    private String departmentTask;
    private String departmentName;
    private int Supermarket_id;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDepartmentTask() {
        return departmentTask;
    }

    public void setDepartmentTask(String departmentTask) {
        this.departmentTask = departmentTask;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getSupermarket_id() {
        return Supermarket_id;
    }

    public void setSupermarket_id(int supermarket_id) {
        Supermarket_id = supermarket_id;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "ID=" + ID +
                ", departmentTask='" + departmentTask + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", Supermarket_id=" + Supermarket_id +
                '}';
    }

}
