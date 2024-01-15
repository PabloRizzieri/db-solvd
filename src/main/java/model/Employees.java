package model;

import java.util.Date;

public class Employees {

    private int ID;
    private int age;
    private String firstName;
    private String lastName;
    private int Supermarket_id;
    private int Department_id;




    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public int getSupermarket_id() {
        return Supermarket_id;
    }

    public void setSupermarket_id(int supermarket_id) {
        Supermarket_id = supermarket_id;
    }

    public int getDepartment_id() {
        return Department_id;
    }

    public void setDepartment_id(int department_id) {
        Department_id = department_id;
    }



    @Override
    public String toString() {
        return "Employees{" +
                "id=" + ID +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Supermarket_id=" + Supermarket_id +
                ", Department_id=" + Department_id +
                '}';
    }
}
