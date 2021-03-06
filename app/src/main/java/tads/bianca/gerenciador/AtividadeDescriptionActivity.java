package tads.bianca.gerenciador;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import tads.bianca.gerenciador.Model.Atividade;
import tads.bianca.gerenciador.Model.Localization;

public class AtividadeDescriptionActivity extends AppCompatActivity {

    private static final String TAG = "AtividadeDescriptionActivity";
    private Atividade atividade;

    private TextView nome;
    private TextView address;
    private TextView localName;
    private TextView data;
    private TextView hora;
    private TextView descricao;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_description);
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.show();
        atividade = (Atividade) getIntent().getParcelableExtra("task");
        atividade.setLocalization((Localization) getIntent().getParcelableExtra("localization"));
        Log.e(TAG, "task: " + atividade.getName());
        initializeComponents();
        fillComponents(atividade);
        Button buttonOpenRoute = (Button) findViewById(R.id.button_open_route);
        buttonOpenRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                intent.putExtra("task", atividade);
                intent.putExtra("localization", atividade.getLocalization());
                startActivity(intent);
            }
        });

    }

    private void fillComponents(Atividade atividade) {
        this.nome.setText(atividade.getName());
        this.data.setText("Date: " + atividade.getDate());
        this.hora.setText("Hour: " + atividade.getHour());
        this.localName.setText("Name: " + atividade.getLocalization().getName());
        this.address.setText("Adress: " + atividade.getLocalization().getAddress());
        this.descricao.setText("Description: " + atividade.getDescription());
    }

    private void initializeComponents(){
        this.nome = (TextView) findViewById(R.id.descrip_name);
        this.data = (TextView) findViewById(R.id.descrip_date);
        this.hora = (TextView) findViewById(R.id.descrip_hour);
        this.localName = (TextView) findViewById(R.id.descrip_location_name);
        this.address = (TextView) findViewById(R.id.descrip_address);
        this.descricao = (TextView) findViewById(R.id.descrip_description);
    }



}
