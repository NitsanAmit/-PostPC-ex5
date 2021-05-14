package exercise.android.reemh.todo_items;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        ((TodoApp) getApplication()).saveItemsToStorageAsync();
        super.onDestroy();
    }
}
