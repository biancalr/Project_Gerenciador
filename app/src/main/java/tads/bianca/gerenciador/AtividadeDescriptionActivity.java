package tads.bianca.gerenciador;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tads.bianca.gerenciador.Model.Atividade;

public class AtividadeDescriptionActivity extends AppCompatActivity {

    private static final String TAG = "AtividadeDescriptionActivity";
    private Atividade atividade;

    public AtividadeDescriptionActivity(Atividade atividade){
        this.atividade = atividade;
    }

    public AtividadeDescriptionActivity(){
        this.atividade = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_description);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        Button buttonOpenRoute = (Button) findViewById(R.id.button_open_route);
        buttonOpenRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RouteActivity.class);
                startActivity(intent);
            }
        });

    }

}
