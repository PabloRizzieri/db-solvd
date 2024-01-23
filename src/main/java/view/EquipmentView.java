package view;

import model.Equipments;

import java.util.List;

public interface EquipmentView {
    void displayEquipmentDetails(Equipments equipments);

    void displayEquipmentsList(List<Equipments> equipmentsList);
}
