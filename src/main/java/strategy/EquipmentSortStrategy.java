package strategy;

import model.Equipments;

import java.util.List;

public interface EquipmentSortStrategy {
    List<Equipments> sort(List<Equipments> equipmentsList);
}
