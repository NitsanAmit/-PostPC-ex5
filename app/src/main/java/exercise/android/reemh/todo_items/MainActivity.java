package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

  public TodoItemsHolder holder = null;
  private EditText editText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (holder == null) {
      if (savedInstanceState == null || savedInstanceState.get("items_holder") == null) {
        holder = new TodoItemsHolderImpl();
      } else {
        this.holder = (TodoItemsHolder) savedInstanceState.getSerializable("items_holder");
      }
    }
    RecyclerView recyclerView = findViewById(R.id.recyclerTodoItemsList);
    TodoItemsRecyclerAdapter adapter = new TodoItemsRecyclerAdapter(holder);
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
      holder.addNewInProgressItem(editText.getText().toString());
      editText.setText("");
      adapter.notifyDataSetChanged();
    });
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("items_holder", holder);
    outState.putString("user_input", editText.getText().toString());
  }

}
