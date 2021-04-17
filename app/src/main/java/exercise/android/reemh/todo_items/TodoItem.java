package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class TodoItem implements Serializable {

    private final String id;
    private final long creationTime;
    private String todoText;
    private Boolean isCompleted;

    public TodoItem(String todoText, Boolean isCompleted) {
        this.id = UUID.randomUUID().toString();
        this.creationTime = new Date().getTime();
        this.todoText = todoText;
        this.isCompleted = isCompleted;
    }

    public String getTodoText() {
        return todoText;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public String getId() {
        return id;
    }

    public long getCreationTime() {
        return creationTime;
    }

}
