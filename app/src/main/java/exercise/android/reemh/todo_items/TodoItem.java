package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TodoItem implements Serializable {

    private static final SimpleDateFormat hoursFormat = new SimpleDateFormat("hh:mm");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
    private final String id;
    private final long creationTime;
    private long lastModified;
    private String todoText;
    private String description;
    private Boolean isCompleted;

    public TodoItem(String todoText, Boolean isCompleted) {
        this.id = UUID.randomUUID().toString();
        this.creationTime = new Date().getTime();
        this.lastModified = creationTime;
        this.todoText = todoText;
        this.isCompleted = isCompleted;
        this.description = "";
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

    public String getLastModifiedString() {
        if (TimeHelpers.isLessThanAnHourAgo(lastModified)) {
            long minutes = TimeUnit.MILLISECONDS.toMinutes(new Date().getTime() - lastModified);
            return minutes + " minutes ago";
        }

        if (TimeHelpers.isToday(lastModified)) {
            return "Today at " + hoursFormat.format(lastModified);
        }

        return dateFormat.format(lastModified) + " at " + hoursFormat.format(lastModified);
    }


    public String getCreationTimeString() {
        return dateFormat.format(lastModified) + " at " + hoursFormat.format(lastModified);
    }


    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
