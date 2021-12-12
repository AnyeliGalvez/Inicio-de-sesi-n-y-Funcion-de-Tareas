package co.com.recuperacion_ciclo4_movil.presenter;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.com.recuperacion_ciclo4_movil.model.MainInteractor;
import co.com.recuperacion_ciclo4_movil.mvp.MainMVP;
import co.com.recuperacion_ciclo4_movil.view.dto.TaskItem;
import co.com.recuperacion_ciclo4_movil.view.dto.TaskState;

public class MainPresenter implements MainMVP.Presenter {

    private final MainMVP.View view;
    private final MainMVP.Model model;

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
    public void taskItemClicked(TaskItem task) {
        String message = task.getState() == TaskState.PENDING
                ? "TERMINADA"
                : "ELIMINAR";
        view.showConfirmDialog("¿Qué desea hacer?", task);
        //view.showDeleteDialog(message, task);


    }

    @Override
    public void updateTask(TaskItem task) {
        task.setState(task.getState() == TaskState.PENDING ? TaskState.DONE : TaskState.PENDING);

        model.updateTask(task);
        view.updateTask(task);


    }

    @Override
    public void deleteTask(TaskItem task) {
        model.deleteTask(task);
        view.deleteTask(task);
    }


}



