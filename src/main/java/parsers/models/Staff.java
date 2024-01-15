package parsers.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class Staff {
    @JsonProperty("employee")
    @XmlElement(name= "employee")
    private List<Employee> staff;

    public List<Employee> getStaff() {
        return staff;
    }

    public void setStaff(List<Employee> staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "{" +
                "staff=" + staff +
                '}';
    }
}
