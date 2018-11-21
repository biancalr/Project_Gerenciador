package tads.bianca.gerenciador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tads.bianca.gerenciador.Model.Atividade;
import tads.bianca.gerenciador.Model.Localization;

public class HomeActivity extends AppCompatActivity{

    private static final String TAG = "HomeActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuthListener authListener;
    private List<Atividade> tasks;
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private DatabaseReference drAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.tasks = new ArrayList<>();
        this.queue = Volley.newRequestQueue(this);
        this.mAuth = FirebaseAuth.getInstance();
        this.authListener = new FirebaseAuthListener(this);
        FirebaseDatabase fbDB = FirebaseDatabase.getInstance();
        drAtividade = fbDB.getReference("atividades");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                openAdd();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            case R.id.action_select:
                openSelect();
                return true;
            case R.id.action_sign_out:
                openSignOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSignOut() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            mAuth.signOut();
        } else {
            Toast.makeText(HomeActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }

    private void openSelect() {
        Toast.makeText(this, "Não implementada.", Toast.LENGTH_SHORT).show();
    }

    private void openSettings() {
        Toast.makeText(this, "Não implementada.", Toast.LENGTH_SHORT).show();
    }

    private void openAdd() {
        Intent intent = new Intent(getApplicationContext(), CreateTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authListener);
        drAtividade.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tasks.clear();
                for (DataSnapshot atvSnapshot: dataSnapshot.getChildren()){
                    Atividade atividade = atvSnapshot.getValue(Atividade.class);
                    Localization localization = atividade.getLocalization();
                    System.out.println(localization.getName());
                    if (atividade != null){
                        tasks.add(atividade);
                    }
                }
                recyclerView = (RecyclerView)findViewById(R.id.list_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new AtividadeListAdapter(tasks, queue, getApplicationContext()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Erro: \n" + databaseError.getMessage());
                System.out.println(databaseError.getMessage());
            }
        });
    }
    @Override
    public void onStop() {
        super.onStop();
        queue.cancelAll(this);
        mAuth.removeAuthStateListener(authListener);
    }

}
