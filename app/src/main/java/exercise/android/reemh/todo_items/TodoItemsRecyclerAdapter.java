package exercise.android.reemh.todo_items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemsRecyclerAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    TodoItemsHolder itemsHolder;
    private boolean onBind;

    public TodoItemsRecyclerAdapter(TodoItemsHolder itemsHolder) {
        this.itemsHolder = itemsHolder;
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false);
        return new TodoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        onBind = true;
        TodoItem item = itemsHolder.getCurrentItems().get(position);
        holder.todoText.setText(item.getTodoText());
        holder.todoCheckbox.setChecked(item.getCompleted());
        holder.todoCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!onBind) {
                itemsHolder.setItemProgress(position, isChecked);
                notifyDataSetChanged();
            }
        });
        holder.todoDelete.setOnClickListener(view -> {
            if (!onBind) {
                itemsHolder.deleteItem(item);
                notifyDataSetChanged();
            }
        });
        onBind = false;
    }

    @Override
    public int getItemCount() {
        return itemsHolder.getCurrentItems().size();
    }

}

class TodoItemViewHolder extends RecyclerView.ViewHolder {

    TextView todoText;
    CheckBox todoCheckbox;
    ImageButton todoDelete;

    public TodoItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.todoText = itemView.findViewById(R.id.text_todo);
        this.todoCheckbox = itemView.findViewById(R.id.checkbox_todo);
        this.todoDelete = itemView.findViewById(R.id.btn_delete_todo);
    }
}