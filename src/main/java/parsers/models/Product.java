package parsers.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import parsers.DateXMLAdapter;

import java.util.Date;


@XmlRootElement(name = "Product")
public class Product {
    // @XmlElement(name = "productName")
    private String productName;
    // @XmlElement(name = "productPrice")
    private int productPrice;
    // @XmlElement(name = "productCategory")
    private String productCategory;
    // @XmlJavaTypeAdapter(DateXMLAdapter.class)
    private Date expirationDate;

    public Product(){}


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Product {" +
                "productName ='" + productName + '\'' +
                ", productPrice =" + productPrice +
                ", productCategory ='" + productCategory + '\'' +
                ", expirationDate =" + expirationDate +
                '}';
    }
}


