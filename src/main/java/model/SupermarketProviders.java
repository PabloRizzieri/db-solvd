package model;

public class SupermarketProviders {
    private int Supermarket_id;
    private int Provider_id;

    public int getSupermarket_id() {
        return Supermarket_id;
    }

    public void setSupermarket_id(int supermarket_id) {
        Supermarket_id = supermarket_id;
    }

    public int getProvider_id() {
        return Provider_id;
    }

    public void setProvider_id(int provider_id) {
        Provider_id = provider_id;
    }

    @Override
    public String toString() {
        return "SupermarketProviders {" +
                "Supermarket_id:" + Supermarket_id +
                ", Provider_id:" + Provider_id +
                '}';
    }
}
