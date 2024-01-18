package view;

import model.Equipments;

import java.util.List;

public class ConsoleEquipmentView implements EquipmentView{
    @Override
    public void displayEquipmentDetails(Equipments equipments){
        System.out.println("Equipment Details: " + equipments);
    }

    @Override
    public void displayEquipmentsList(List<Equipments> equipmentsList){
        System.out.println("Equipments: ");
        for (Equipments equipments : equipmentsList){
            System.out.println(equipments);
        }
    }
}
