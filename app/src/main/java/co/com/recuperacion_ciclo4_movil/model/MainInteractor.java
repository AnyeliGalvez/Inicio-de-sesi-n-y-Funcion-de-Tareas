package co.com.recuperacion_ciclo4_movil.model;

import java.util.ArrayList;
import java.util.List;

import co.com.recuperacion_ciclo4_movil.mvp.MainMVP;
import co.com.recuperacion_ciclo4_movil.view.dto.TaskItem;

public class MainInteractor implements MainMVP.Model {

    private List<TaskItem> tempItems;

    public MainInteractor() {
        tempItems = new ArrayList<>();
        tempItems.add(new TaskItem("Pagar servicios públicos", "Dec 11, 2021" ));
    }

    @Override
    public List<TaskItem> getTasks() {
        return new ArrayList<>(tempItems);
    }

    @Override
    public void saveTask(TaskItem task) {
        tempItems.add(task);
    }

    @Override
    public void updateTask(TaskItem item) {

    }
}
