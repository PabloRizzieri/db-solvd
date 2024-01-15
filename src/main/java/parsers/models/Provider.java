package parsers.models;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Provider")
public class Provider {
    // @XmlElement(name = "providerName")
    private String providerName;
    // @XmlElement(name = "providerType")
    private String providerType;

    public Provider(){}


    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    @Override
    public String toString() {
        return "Provider {" +
                "providerName ='" + providerName + '\'' +
                ", providerType ='" + providerType + '\'' +
                '}';
    }

}
