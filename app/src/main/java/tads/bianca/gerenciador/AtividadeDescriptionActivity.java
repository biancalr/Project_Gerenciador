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

public class AtividadeDescriptionActivity extends AppCompatActivity {

    private static final String TAG = "AtividadeDescriptionActivity";
    private Atividade atividade;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

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
        Log.e(TAG, "task: " + atividade.getName());
        inicializarComponentes();
        preencherComponentes(atividade);
        Button buttonOpenRoute = (Button) findViewById(R.id.button_open_route);
        buttonOpenRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RouteActivity.class);
                startActivity(intent);
            }
        });

    }

    private void searchTask(final String taskName) {
        Query query;
        query = databaseReference.child("atividades").child("name").equalTo(taskName);
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                atividade = dataSnapshot.getValue(Atividade.class);
                Log.e(TAG, "search task: called");
                preencherComponentes(atividade);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void preencherComponentes(Atividade atividade) {
        this.nome.setText(atividade.getName());
        this.data.setText(atividade.getDate());
        this.hora.setText(atividade.getHour());
//        this.localizacao.setText(atividade.getLocalization().getName());
        this.descricao.setText(atividade.getDescription());
    }

    private void inicializarComponentes(){
        this.nome = (TextView) findViewById(R.id.descrip_name);
        this.data = (TextView) findViewById(R.id.descrip_date);
        this.hora = (TextView) findViewById(R.id.descrip_hour);
//        this.localizacao = (TextView) findViewById(R.id.descrip_location);
        this.descricao = (TextView) findViewById(R.id.descrip_description);
    }



}
