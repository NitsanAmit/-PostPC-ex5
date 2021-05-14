package exercise.android.reemh.todo_items;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TodoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TodoItemsHolder todoItemsStore = TodoItemsStore.getInstance();
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String todoItemsList = preferences.getString("todo_items_list", gson.toJson(new ArrayList<TodoItem>()));
        Type classType = new TypeToken<ArrayList<TodoItem>>() {}.getType();
        todoItemsStore.setItems(gson.fromJson(todoItemsList, classType));
    }

    public void saveItemsToStorageAsync() {
        AsyncTask.execute(this::saveItemsToStorage);
    }

    public void saveItemsToStorage() {
        List<TodoItem> items = TodoItemsStore.getInstance().getCurrentItems();
        if (items == null || items.isEmpty()) {
            return;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        preferences.edit().putString("todo_items_list", gson.toJson(items)).apply();
    }

}
