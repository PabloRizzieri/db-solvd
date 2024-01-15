package model;

public class Equipments {
    private int ID;
    private String equipmentName;
    private int Supermarket_id;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getSupermarket_id() {
        return Supermarket_id;
    }

    public void setSupermarket_id(int supermarket_id) {
        Supermarket_id = supermarket_id;
    }


    @Override
    public String toString() {
        return "Equipments{" +
                "id=" + ID +
                ", equipmentName='" + equipmentName + '\'' +
                ", Supermarket_id=" + Supermarket_id +
                '}';
    }
}
