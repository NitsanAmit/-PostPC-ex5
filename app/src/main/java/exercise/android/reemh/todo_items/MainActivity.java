package exercise.android.reemh.todo_items;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Objects;

public class MainActivity extends BaseActivity {

  private EditText editText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recyclerTodoItemsList);
    TodoItemsRecyclerAdapter adapter = new TodoItemsRecyclerAdapter(TodoItemsStore.getInstance(), this::startEditTodoActivity);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    recyclerView.setAdapter(adapter);

    FloatingActionButton fab = findViewById(R.id.buttonCreateTodoItem);
    editText = findViewById(R.id.editTextInsertTask);
    if (savedInstanceState != null && savedInstanceState.get("user_input") != null) {
      editText.setText(savedInstanceState.getString("user_input"));
    }
    editText.addTextChangedListener(new TextWatcher() {
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }
      public void afterTextChanged(Editable s) {
        fab.setClickable(!TextUtils.isEmpty(editText.getText()));
      }
    });

    fab.setOnClickListener(view -> {
      if (TextUtils.isEmpty(editText.getText())) return;
      TodoItemsStore.getInstance().addNewInProgressItem(editText.getText().toString());
      editText.setText("");
      adapter.notifyDataSetChanged();
    });
  }

  private void startEditTodoActivity(TodoItem item) {
    Intent intent = new Intent(MainActivity.this, EditTodoActivity.class);
    intent.putExtra("todo_key", item.getId());
    startActivity(intent);
  }

  @Override
  protected void onRestart() {
    TodoItemsStore.getInstance().sortItems();
    RecyclerView recyclerView = findViewById(R.id.recyclerTodoItemsList);
    Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    super.onRestart();
  }

}
