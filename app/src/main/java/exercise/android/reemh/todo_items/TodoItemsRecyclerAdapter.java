package exercise.android.reemh.todo_items;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
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
    OnTodoItemClick listener;
    private boolean onBind;

    public TodoItemsRecyclerAdapter(TodoItemsHolder itemsHolder, OnTodoItemClick listener) {
        this.itemsHolder = itemsHolder;
        this.listener = listener;
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
        if (!TextUtils.isEmpty(item.getDescription())) {
            holder.todoDescription.setText(item.getDescription());
            holder.todoDescription.setVisibility(View.VISIBLE);
        }else {
            holder.todoDescription.setVisibility(View.GONE);
        }
        holder.todoCheckbox.setChecked(item.getCompleted());
        if (item.getCompleted()) {
            holder.todoText.setPaintFlags(holder.todoText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.todoText.setPaintFlags(holder.todoText.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
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
        holder.itemView.setOnClickListener(view -> {
            if (!onBind) {
                this.listener.todoItemClicked(item);
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
    TextView todoDescription;
    CheckBox todoCheckbox;
    ImageButton todoDelete;
    View itemView;

    public TodoItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.todoText = itemView.findViewById(R.id.text_todo);
        this.todoDescription = itemView.findViewById(R.id.text_todo_description);
        this.todoCheckbox = itemView.findViewById(R.id.checkbox_todo);
        this.todoDelete = itemView.findViewById(R.id.btn_delete_todo);
        this.itemView = itemView;
    }
}