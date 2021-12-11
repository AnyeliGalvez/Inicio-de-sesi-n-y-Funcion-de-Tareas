package co.com.recuperacion_ciclo4_movil.presenter;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.com.recuperacion_ciclo4_movil.model.MainInteractor;
import co.com.recuperacion_ciclo4_movil.mvp.MainMVP;
import co.com.recuperacion_ciclo4_movil.view.MainActivity;
import co.com.recuperacion_ciclo4_movil.view.dto.TaskItem;
import co.com.recuperacion_ciclo4_movil.view.dto.TaskState;

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
        Log.i(MainPresenter.class.getSimpleName(), "Add new task");
        String description = view.getTaskDescription();
        String date = SimpleDateFormat.getDateInstance().format(new Date());

        TaskItem task = new TaskItem(description, date);
        model.saveTask(task);
        view.addTaskToList(task);

    }

    @Override
    public void taskItemClicked(TaskItem item) {
        item.setState(TaskState.DONE);

        model.updateTask(item);
        view.updateTask(item);
    }
}
