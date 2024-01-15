package model;

public class CerealsSector {
    private int ID;
    private String flavour;
    private int Product_id;
    private int Products_Supermarket_id;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
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
        return "CerealsSector{" +
                "ID=" + ID +
                ", flavour='" + flavour + '\'' +
                ", Product_id=" + Product_id +
                ", Products_Supermarket_id=" + Products_Supermarket_id +
                '}';
    }
}
