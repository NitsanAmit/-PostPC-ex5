package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.List;


public interface TodoItemsHolder extends Serializable {

    /**
     * Get a copy of the current items list
     */
    List<TodoItem> getCurrentItems();

    /**
     * Creates a new TodoItem and adds it to the list, with the @param description and status=IN-PROGRESS
     * Subsequent calls to [getCurrentItems()] should have this new TodoItem in the list
     */
    void addNewInProgressItem(String description);

    /**
     * sets the progress of the item at @param position as @param isCompleted
     */
    void setItemProgress(int position, boolean isCompleted);

    /**
     * delete the @param item
     */
    void deleteItem(TodoItem item);

    void setItems(List<TodoItem> items);

    TodoItem getItemById(String itemId);

    void sortItems();
}
