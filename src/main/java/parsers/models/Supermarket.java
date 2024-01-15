package parsers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import parsers.DateJacksonAdapter;
import parsers.DateXMLAdapter;
import java.util.Date;
import java.util.List;


@XmlRootElement(name = "Supermarket")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"id", "staff", "product", "equipment", "provider", "supermarketName", "openingDate"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Supermarket {

    @XmlAttribute(name = "id")
    @JsonProperty
    private int id;

    @JsonProperty("staff")
    private List<Staff> employees;
    @XmlElement(name = "product")
    @JsonProperty
    private Product product;
    @XmlElement(name = "equipment")
    @JsonProperty
    private Equipment equipment;
    @XmlElement(name = "provider")
    @JsonProperty
    private Provider provider;
    @XmlElement(name = "supermarketName")
    @JsonProperty
    private String supermarketName;

    @XmlJavaTypeAdapter(DateXMLAdapter.class)
    @JsonDeserialize(using = DateJacksonAdapter.class)
    private Date openingDate;

    public Supermarket(){}



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getSupermarketName() {
        return supermarketName;
    }

    public void setSupermarketName(String supermarketName) {
        this.supermarketName = supermarketName;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Staff> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Staff> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Supermarket{" +
                "id=" + id +
                ", employees=" + employees +
                ", product=" + product +
                ", equipment=" + equipment +
                ", provider=" + provider +
                ", supermarketName='" + supermarketName + '\'' +
                ", openingDate=" + openingDate +
                '}';
    }
}
