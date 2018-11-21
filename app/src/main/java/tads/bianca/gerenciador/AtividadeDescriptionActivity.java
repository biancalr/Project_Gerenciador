package tads.bianca.gerenciador;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
                Intent intent = new Intent(getApplicationContext(), RouteActivity.class);
                startActivity(intent);
            }
        });

    }

    private void fillComponents(Atividade atividade) {
        this.nome.setText("Nome: " + atividade.getName());
        this.data.setText("Data: " + atividade.getDate());
        this.hora.setText("Hora: " + atividade.getHour());
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
