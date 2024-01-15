package parsers.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import parsers.DateXMLAdapter;

import java.util.Date;


@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    // @XmlElement(name = "firstName")
    private String firstName;
    // @XmlElement(name = "lastName")
    private String lastName;
    // @XmlElement(name = "age")
    private int age;

    // @XmlJavaTypeAdapter(DateXMLAdapter.class)
    private Date contratationDate;

    public Employee(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getContratationDate() {
        return contratationDate;
    }

    public void setContratationDate(Date contratationDate) {
        this.contratationDate = contratationDate;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "firstName ='" + firstName + '\'' +
                ", lastName ='" + lastName + '\'' +
                ", age =" + age +
                ", contratationDate =" + contratationDate +
                '}';
    }
}
