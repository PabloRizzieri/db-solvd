package model;

public class Supermarkets {

    private int ID;
    private String name;


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



    @Override
    public String toString() {
        return "Supermarkets{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
