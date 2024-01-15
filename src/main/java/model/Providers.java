package model;

public class Providers {

    private int ID;
    private String providerName;
    private String providerBranch;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderBranch() {
        return providerBranch;
    }

    public void setProviderBranch(String providerBranch) {
        this.providerBranch = providerBranch;
    }



    @Override
    public String toString() {
        return "Providers{" +
                "ID=" + ID +
                ", providerName='" + providerName + '\'' +
                ", providerBranch='" + providerBranch + '\'' +
                '}';
    }
}
