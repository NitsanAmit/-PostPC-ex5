package exercise.android.reemh.todo_items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoItemsStore implements TodoItemsHolder {

  private static TodoItemsStore instance;
  private List<TodoItem> items;

  private TodoItemsStore() { }

  public static TodoItemsStore getInstance() {
    if (instance == null){
      instance = new TodoItemsStore();
    }
    return instance;
  }

  @Override
  public List<TodoItem> getCurrentItems() { return new ArrayList<>(items); }

  @Override
  public void addNewInProgressItem(String description) {
    this.items.add(new TodoItem(description, false));
    sortItems();
  }

  @Override
  public void setItemProgress(int position, boolean isCompleted) throws IndexOutOfBoundsException {
    if (position > items.size()) throw new IndexOutOfBoundsException();
    if (items.get(position).getCompleted() == isCompleted) return;
    items.get(position).setCompleted(isCompleted);
    sortItems();
  }

  @Override
  public void deleteItem(TodoItem item) {
    items.remove(item);
  }

  @Override
  public void setItems(List<TodoItem> items) {
    this.items = items;
  }

  @Override
  public TodoItem getItemById(String itemId) {
    for (TodoItem item : items) {
      if (item.getId().equals(itemId)) {
        return item;
      }
    }
    return null;
  }

  @Override
  public void sortItems() {
    Collections.sort(this.items, ((o1, o2) -> {
      if (o1.getCompleted() == o2.getCompleted()) {
        return Long.compare(o1.getCreationTime(), o2.getCreationTime());
      } else {
        return o1.getCompleted() ? 1 : -1;
      }
    }));

  }
}
