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
import android.widget.Toast;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tads.bianca.gerenciador.Fragments.DateFragment;
import tads.bianca.gerenciador.Fragments.TimeFragment;
import tads.bianca.gerenciador.Model.Atividade;
import tads.bianca.gerenciador.Model.Localization;

public class CreateTaskActivity extends AppCompatActivity {

    private static final String TAG = "CreateTaskActivity";

    private Atividade atividade;

    private DateFragment fragmentDate;
    private TimeFragment fragmentTime;

    private EditText name;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_task);
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
        fillFragments();
        Button buttonSetLocation = (Button) findViewById(R.id.button_set_location);
        buttonSetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateLocalizationActivity.class);
                initializeComponents();
                fillComponents();
                if (atividade != null){
                    intent.putExtra("atividade", atividade);
                    Log.d(TAG, "atividade: " + atividade.getName() + " sent");
                    startActivity(intent);
                }else {
                    Toast.makeText(CreateTaskActivity.this, "Blank spaces not allowed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private void fillFragments() {
        this.fragmentDate = new DateFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.date_create, fragmentDate, "DateFragment");
        transaction.commit();

        this.fragmentTime = new TimeFragment();
        FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
        transaction2.replace(R.id.hour_create, fragmentTime, "TimeFragment");
        transaction2.commit();
    }

    private void initializeComponents() {
        //Catch the data from the layout
        atividade = new Atividade();
        this.name = (EditText) findViewById(R.id.create_name);
        this.description = (EditText) findViewById(R.id.create_description);
    }

    private void fillComponents() {
        //set the data to atividade
        atividade.setName(name.getText().toString().trim());
        atividade.setDescription(description.getText().toString().trim());
        atividade.setDate(fragmentDate.getDate().trim());
        atividade.setHour(fragmentTime.getTime().trim());
    }

//    private void createAtividade() {
//        try {
//            Log.d(TAG, "addTask: called");
//            atividade = new Atividade();
//            initializeComponents();
//            fillComponents();
//            //Creating an id
//            atividade.setId(drAtividade.push().getKey());
//            //Set the atividade as a child
//            drAtividade.child("atividades").child(atividade.getId()).setValue(atividade);
//            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//            startActivity(intent);
//        }catch (Exception e) {
//            Log.d(TAG, "Erro: \n" + e.getMessage());
//            System.out.println("Erro: \n" + e.getMessage());
//        }
//
//    }


}
