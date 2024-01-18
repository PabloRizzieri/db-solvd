package strategy;

import model.Equipments;

import java.util.Comparator;
import java.util.List;

public class SortByEquipmentID implements EquipmentSortStrategy{
    @Override
    public List<Equipments> sort(List<Equipments> equipmentsList){
        equipmentsList.sort(Comparator.comparingInt(Equipments::getID));
        return equipmentsList;
    }
}
