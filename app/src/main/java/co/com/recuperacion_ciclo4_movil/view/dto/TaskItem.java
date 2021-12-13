package co.com.recuperacion_ciclo4_movil.view.dto;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

public class TaskItem {
    private String description;
    private String date;
    private TaskState state;

    public TaskItem(String description, String date) {
        this.description = description;
        this.date = date;
        this.state = TaskState.PENDING;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskItem item = (TaskItem) o;
        return description.equals(item.description) && date.equals(item.date) && state == item.state;
    }



    @Override
    public int hashCode() {
        return Objects.hash(description, date, state);
    }
}