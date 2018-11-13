package tads.bianca.gerenciador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import tads.bianca.gerenciador.Model.Atividade;
import tads.bianca.gerenciador.Model.Localization;

class AtividadeArrayAdapter extends RecyclerView.Adapter<AtividadeArrayAdapter.AtividadeHolder> {

    private static final String TAG = "AtividadeArrayAdapter";

    private final List<Atividade> tasks;
    Context context;
    RequestQueue queue;

    public AtividadeArrayAdapter(List<Atividade> tasks, RequestQueue queue, Context context) {
        this.tasks = tasks;
        this.context = context;
        this.queue = queue;
    }

    @Override
    public AtividadeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_listitem, parent, false);
        return new AtividadeHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(AtividadeHolder holder, final int position) {
        holder.bindTask(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        if (tasks == null) {
            return 0;
        }
        return tasks.size();
    }


    public class AtividadeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView taskName;
        private final TextView taskDate;
        private final TextView taskWeather;

        private Atividade task;

        Context context;

        public AtividadeHolder(View itemView) {
            super(itemView);
            this.taskName = (TextView) itemView.findViewById(R.id.task_name);
            this.taskDate = (TextView) itemView.findViewById(R.id.task_date);
            this.taskWeather = (TextView) itemView.findViewById(R.id.task_weather);
            itemView.setOnClickListener(this);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: called");
            Toast.makeText(v.getContext(), "Task selected: " +
                    task.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, AtividadeDescriptionActivity.class);
            context.startActivity(intent);
        }

        public void bindTask(Atividade task) {
            Log.d(TAG, "bindTask: called");
            this.task = task;
            taskName.setText(task.getName());
            taskDate.setText(task.getDate());
            if (task.getLocalization().getWeather() != null) {
                taskWeather.setText(task.getLocalization().getWeather());
            } else {
                final Localization localization = task.getLocalization();
                final TextView weather = taskWeather;
                weather.setText("Loading weather...");
                loadInBackground(weather, localization);
                task.setLocalization(localization);
            }

        }

        private void loadInBackground(final TextView weatherView, final Localization localization) {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http");
            builder.authority("api.openweathermap.org");
            builder.appendPath("data/2.5/weather");
            builder.appendQueryParameter("q", localization.getName());
            builder.appendQueryParameter("mode", "json");
            builder.appendQueryParameter("units", "metric");
            builder.appendQueryParameter("cnt", "" + 1);
            builder.appendQueryParameter("APPID", "afbcd56f2b7e8a0f0ef6b56624f30e76");
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                    builder.build().toString(), null,
                    new Response.Listener<JSONObject>() {
                        @Override

                        public void onResponse(JSONObject response) {
                            String weatherStr = getWeather(response);
                            if (weatherStr != null) {
                                localization.setWeather(weatherStr);
                                weatherView.setText(weatherStr);

                            } else {
                                weatherView.setText("Erro!");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    weatherView.setText("Erro!");
                }
            });
            queue.add(request);

        }

        private String getWeather(JSONObject forecastJson) {
            final String OWM_WEATHER = "weather";
            final String OWM_TEMPERATURE = "temp";
            final String OWM_MAIN = "main";
            final String OWM_DESCRIPTION = "description";
            try {
                JSONObject weatherObject = forecastJson.getJSONArray(OWM_WEATHER).
                        getJSONObject(0);
                JSONObject mainObject = forecastJson.getJSONObject(OWM_MAIN);
                String description = weatherObject.getString(OWM_DESCRIPTION);
                double temp = mainObject.getDouble(OWM_TEMPERATURE);
                return "weather now: " + description + " - " + temp;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

}
