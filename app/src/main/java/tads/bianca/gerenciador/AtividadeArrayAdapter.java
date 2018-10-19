package tads.bianca.gerenciador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import tads.bianca.gerenciador.Model.Atividade;

class AtividadeArrayAdapter extends RecyclerView.Adapter<AtividadeArrayAdapter.AtividadeHolder> {

    private static final String TAG = "AtividadeArrayAdapter";

    private Atividade[] tasks;
    Context context;

    public AtividadeArrayAdapter(Atividade[] tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public AtividadeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_listitem, parent, false);
        return new AtividadeHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(AtividadeHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.bindTask(tasks[position]);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClicked: clicked on " + tasks[position]);
                Toast.makeText(context, "Task selected is: " +
                        tasks[position].getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AtividadeDescriptionActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tasks == null){
            return 0;
        }
        return tasks.length;
    }


    public class AtividadeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView taskName;
        private TextView taskDate;

        private Atividade task = new Atividade();

        LinearLayout linearLayout;

        public AtividadeHolder(View itemView) {
            super(itemView);
            this.taskName = (TextView) itemView.findViewById(R.id.task_name);
            this.taskDate = (TextView) itemView.findViewById(R.id.task_date);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.parent_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Task selected: " +
                    task.getName(), Toast.LENGTH_SHORT).show();
        }

        public void bindTask(Atividade task) {
            this.task = task;
            taskName.setText(task.getName());
            taskDate.setText(task.getDate());
        }

    }
}
