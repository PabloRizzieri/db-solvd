package model;

public class Supervisors {
    private int ID;
    private String firstName;
    private String lastName;
    private int Employee_id;
    private int Department_id;
    private int Employee_Supermarket_id;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public void setEmployee_id(int employee_id) {
        Employee_id = employee_id;
    }

    public int getDepartment_id() {
        return Department_id;
    }

    public void setDepartment_id(int department_id) {
        Department_id = department_id;
    }

    public int getEmployee_Supermarket_id() {
        return Employee_Supermarket_id;
    }

    public void setEmployee_Supermarket_id(int employee_Supermarket_id) {
        Employee_Supermarket_id = employee_Supermarket_id;
    }

    @Override
    public String toString() {
        return "Supervisors{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Employee_id=" + Employee_id +
                ", Department_id=" + Department_id +
                ", Employee_Supermarket_id=" + Employee_Supermarket_id +
                '}';
    }
}
