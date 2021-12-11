package co.com.recuperacion_ciclo4_movil.mvp;

import java.util.List;

import co.com.recuperacion_ciclo4_movil.view.dto.TaskItem;

public interface MainMVP {
    interface Model{

        List<TaskItem> getTasks();

        void saveTask(TaskItem task);
    }

    interface Presenter{
        void loadTasks();

        void addNewTask();
    }

    interface View{

        void showTaskList(List<TaskItem> items);

        String getTaskDescription();

        void addTaskToList(TaskItem task);
    }
}
