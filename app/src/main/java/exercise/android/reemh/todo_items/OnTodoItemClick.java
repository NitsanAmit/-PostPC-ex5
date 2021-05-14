package exercise.android.reemh.todo_items;

@FunctionalInterface
public interface OnTodoItemClick {
    void todoItemClicked(TodoItem item);
}
