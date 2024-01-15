package model;

public class FruitsSector {
    private int ID;
    private String type;
    private int Product_id;
    private int Products_Supermarket_id;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int product_id) {
        Product_id = product_id;
    }

    public int getProducts_Supermarket_id() {
        return Products_Supermarket_id;
    }

    public void setProducts_Supermarket_id(int products_Supermarket_id) {
        Products_Supermarket_id = products_Supermarket_id;
    }

    @Override
    public String toString() {
        return "FruitsSector{" +
                "ID=" + ID +
                ", type='" + type + '\'' +
                ", Product_id=" + Product_id +
                ", Products_Supermarket_id=" + Products_Supermarket_id +
                '}';
    }
}