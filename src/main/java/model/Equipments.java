package model;

public class Equipments {
    private int ID;
    private String equipmentName;
    private int Supermarket_id;

    private Equipments() {}

    public int getID() {
        return ID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getSupermarket_id() {
        return Supermarket_id;
    }

    @Override
    public String toString() {
        return "Equipments{" +
                "id=" + ID +
                ", equipmentName='" + equipmentName + '\'' +
                ", Supermarket_id=" + Supermarket_id +
                '}';
    }

    public static class Builder {
        private Equipments equipments;

        public Builder() {
            this.equipments = new Equipments();
        }

        public Builder withID(int ID) {
            this.equipments.ID = ID;
            return this;
        }

        public Builder withEquipmentName(String equipmentName) {
            this.equipments.equipmentName = equipmentName;
            return this;
        }

        public Builder withSupermarketId(int Supermarket_id) {
            this.equipments.Supermarket_id = Supermarket_id;
            return this;
        }

        public Equipments build() {
            return this.equipments;
        }
    }
}
