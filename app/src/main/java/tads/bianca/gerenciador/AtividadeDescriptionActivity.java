package tads.bianca.gerenciador;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tads.bianca.gerenciador.Model.Atividade;
import tads.bianca.gerenciador.Model.Localization;

public class AtividadeDescriptionActivity extends AppCompatActivity {

    private static final String TAG = "AtividadeDescriptionActivity";
    private Atividade atividade;

    private TextView nome;
    private TextView localizacao;
    private TextView data;
    private TextView hora;
    private TextView descricao;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_description);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        atividade = (Atividade) getIntent().getSerializableExtra("task");
        atividade.setLocalization(new Localization((String) getIntent().getSerializableExtra("localization")));
        Log.e(TAG, "task: " + atividade.getName());
        initializeComponents();
        fillComponents(atividade);
        Button buttonOpenRoute = (Button) findViewById(R.id.button_open_route);
        buttonOpenRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void fillComponents(Atividade atividade) {
        this.nome.setText("Name: " + atividade.getName());
        this.data.setText("Date: " + atividade.getDate());
        this.hora.setText("Hour: " + atividade.getHour());
        this.localizacao.setText("Localization: " + atividade.getLocalization().getName());
        this.descricao.setText("Description: " + atividade.getDescription());
    }

    private void initializeComponents(){
        this.nome = (TextView) findViewById(R.id.descrip_name);
        this.data = (TextView) findViewById(R.id.descrip_date);
        this.hora = (TextView) findViewById(R.id.descrip_hour);
        this.localizacao = (TextView) findViewById(R.id.descrip_location);
        this.descricao = (TextView) findViewById(R.id.descrip_description);
    }



}
