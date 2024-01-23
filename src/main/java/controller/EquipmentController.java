package controller;

import model.Equipments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import strategy.EquipmentSortStrategy;
import view.EquipmentView;

import java.util.List;

public class EquipmentController {
    private static final Logger LOGGER = LogManager.getLogger(EquipmentController.class);
    private EquipmentView equipmentView;
    private List<Equipments> equipmentsList;
    private EquipmentSortStrategy sortStrategy;

    public EquipmentController(EquipmentView equipmentView, List<Equipments> equipmentsList){
        this.equipmentView = equipmentView;
        this.equipmentsList = equipmentsList;
    }

    public void displayEquipmentDetails(int equipmentId){
        Equipments equipmentSelected = getEquipmentById(equipmentId);
        equipmentView.displayEquipmentDetails(equipmentSelected);
    }

    public void displayAllEquipments(){
        equipmentView.displayEquipmentsList(equipmentsList);
    }

    private Equipments getEquipmentById(int equipmentId){
        return equipmentsList.stream()
                .filter(equipments -> equipments.getID() == equipmentId)
                .findFirst()
                .orElse(null);
    }

    public void setSortStrategy(EquipmentSortStrategy sortStrategy){
        this.sortStrategy = sortStrategy;
    }

    public void displaySortEquipments(){
        if(sortStrategy != null){
            List<Equipments> sortedEquipments = sortStrategy.sort(equipmentsList);
            equipmentView.displayEquipmentsList(sortedEquipments);
        } else {
            // Handle error or provide a default message
            LOGGER.error("Sort Strategy not found");
        }
    }
}
