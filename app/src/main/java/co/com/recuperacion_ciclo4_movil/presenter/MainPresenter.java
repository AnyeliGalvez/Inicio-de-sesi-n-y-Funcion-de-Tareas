package co.com.recuperacion_ciclo4_movil.presenter;

import java.util.List;

import co.com.recuperacion_ciclo4_movil.model.MainInteractor;
import co.com.recuperacion_ciclo4_movil.mvp.MainMVP;
import co.com.recuperacion_ciclo4_movil.view.MainActivity;
import co.com.recuperacion_ciclo4_movil.view.dto.TaskItem;

public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View view;
    private MainMVP.Model model;

    public MainPresenter(MainMVP.View view){
        this.view = view;
        this.model = new MainInteractor();
    }

    @Override
    public void loadTasks() {
    List<TaskItem> items = model.getTasks();

    view.showTaskList(items);

    }

    @Override
    public void addNewTask() {

    }
}
