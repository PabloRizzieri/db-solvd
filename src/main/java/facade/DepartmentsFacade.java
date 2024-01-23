package facade;

import model.Departments;

public class DepartmentsFacade {
    private final Departments departments;

    public DepartmentsFacade(Departments departments) {
        this.departments = departments;
    }

    // Facade methods for common operations
    public String getDepartmentInfo() {
        return "Department Information: " + departments.toString();
    }

    public void assignTask(String task) {
        departments.setDepartmentTask(task);
        System.out.println("Task assigned to department: " + task);
    }

}
