package tads.bianca.gerenciador;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tads.bianca.gerenciador.Model.Atividade;
import tads.bianca.gerenciador.Model.Localization;

public class CreateTaskActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "CreateTaskActivity";
    private DatabaseReference drAtividade;
    private Atividade atividade;
    DateFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_task);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        this.mAuth = FirebaseAuth.getInstance();

        fragment = new DateFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.date_create, fragment, "DateFragment");
        transaction.commit();

        FirebaseDatabase fbDB = FirebaseDatabase.getInstance();
        drAtividade = fbDB.getReference("atividades");
        Button buttonCreateTaskClick = (Button) findViewById(R.id.button_create_task);
        buttonCreateTaskClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAtividade();
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private boolean createAtividade() {
        try {
            Log.d(TAG, "addTask: called");
            //Criar o objeto Atividade
            atividade = new Atividade();
            //Pegar os dados do layout
            EditText name = (EditText) findViewById(R.id.create_name);
            EditText description = (EditText) findViewById(R.id.create_description);
            EditText hour = (EditText) findViewById(R.id.create_hour);
            EditText loc = (EditText) findViewById(R.id.create_location);
            Localization l = new Localization(loc.getText().toString());
            //setando os dados recuperados em atividade
            atividade.setName(name.getText().toString().trim());
            atividade.setDescription(description.getText().toString().trim());
            atividade.setDate(fragment.getDate().trim());
            atividade.setHour(hour.getText().toString().trim());
            atividade.setLocalization(l);
            //criando um id
            String id = drAtividade.push().getKey();
            //setando a atividade como um child
            drAtividade.child(id).setValue(atividade);
            //intent para HomeActivity
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.d(TAG, "Erro: \n" + e.getMessage());
            System.out.println("Erro: \n" + e.getMessage());
            return false;
        }

    }


}
