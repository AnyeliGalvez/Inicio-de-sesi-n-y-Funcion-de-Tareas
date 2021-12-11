package co.com.recuperacion_ciclo4_movil.mvp;

import java.util.List;

import co.com.recuperacion_ciclo4_movil.view.dto.TaskItem;

public interface MainMVP {
    interface Model{

        List<TaskItem> getTasks();

        void saveTask(TaskItem task);

        void updateTask(TaskItem item);
    }

    interface Presenter{
        void loadTasks();

        void addNewTask();

        void taskItemClicked(TaskItem item);
    }

    interface View{

        void showTaskList(List<TaskItem> items);

        String getTaskDescription();

        void addTaskToList(TaskItem task);

        void updateTask(TaskItem task);
    }
}
