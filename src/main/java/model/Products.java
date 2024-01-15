package model;

public class Products {
    private int ID;
    private String productName;
    private String category;
    private double price;
    private int Provider_id;
    private int Supermarket_id;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProvider_id() {
        return Provider_id;
    }

    public void setProvider_id(int provider_id) {
        Provider_id = provider_id;
    }

    public int getSupermarket_id() {
        return Supermarket_id;
    }

    public void setSupermarket_id(int supermarket_id) {
        Supermarket_id = supermarket_id;
    }

    @Override
    public String toString() {
        return "Products{" +
                "ID=" + ID +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", Provider_id=" + Provider_id +
                ", Supermarket_id=" + Supermarket_id +
                '}';
    }
}
