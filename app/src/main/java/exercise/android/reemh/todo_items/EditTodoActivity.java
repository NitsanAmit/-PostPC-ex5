package exercise.android.reemh.todo_items;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Date;

public class EditTodoActivity extends BaseActivity {

    private static final String STATUS_COMPLETED = "Completed";
    private static final String STATUS_IN_PROGRESS = "In Progress";
    private TodoItem item;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String itemKey = extras.getString("todo_key");
        item = TodoItemsStore.getInstance().getItemById(itemKey);
        if (item == null) {
            return;
        }
        descriptionEditText = findViewById(R.id.edit_txt_description);

        ((TextView) findViewById(R.id.txt_creation_time)).setText(item.getCreationTimeString());
        ((TextView) findViewById(R.id.txt_last_modified)).setText(item.getLastModifiedString());
        ((CheckBox) findViewById(R.id.checkbox_status)).setChecked(item.getCompleted());
        ((TextView) findViewById(R.id.txt_status)).setText(item.getCompleted() ? STATUS_COMPLETED : STATUS_IN_PROGRESS);
        ((CheckBox) findViewById(R.id.checkbox_status)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            ((TextView) findViewById(R.id.txt_status)).setText(isChecked ? STATUS_COMPLETED : STATUS_IN_PROGRESS);
            item.setCompleted(isChecked);
            item.setLastModified(new Date().getTime());
            ((TextView) findViewById(R.id.txt_last_modified)).setText(item.getLastModifiedString());
        });
        descriptionEditText.setText(item.getDescription());
    }

    @Override
    protected void onPause() {
        String descriptionDraft = descriptionEditText.getText().toString();
        if (!item.getDescription().equals(descriptionDraft)) {
            item.setDescription(descriptionDraft);
            item.setLastModified(new Date().getTime());
        }
        super.onPause();
    }

}