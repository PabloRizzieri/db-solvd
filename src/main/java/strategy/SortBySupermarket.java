package strategy;

import model.Equipments;

import java.util.Comparator;
import java.util.List;

public class SortBySupermarket implements EquipmentSortStrategy{
    @Override
    public List<Equipments> sort(List<Equipments> equipmentsList){
        equipmentsList.sort(Comparator.comparingInt(Equipments::getSupermarket_id));
        return equipmentsList;
    }
}
