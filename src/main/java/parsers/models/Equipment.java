package parsers.models;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Equipment")
public class Equipment {
    // @XmlElement(name = "equipmentName")
    private String equipmentName;
    // @XmlElement(name = "equipmentType")

    private String equipmentType;

    public Equipment(){}


    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    @Override
    public String toString() {
        return "Equipment {" +
                "equipmentName ='" + equipmentName + '\'' +
                ", equipmentType ='" + equipmentType + '\'' +
                '}';
    }
}
