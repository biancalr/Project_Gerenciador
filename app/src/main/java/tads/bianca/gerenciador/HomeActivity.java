package tads.bianca.gerenciador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tads.bianca.gerenciador.Model.Atividade;

public class HomeActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private FirebaseAuthListener authListener;
    private Atividade[] tasks = {
            new Atividade("Task 1", "Place", "Short Task", "13/12/2018", "13:20"),
            new Atividade("Task 2", "Other Place", "Short Task 2", "19/04/2018", "19:15")};
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = (RecyclerView)findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new AtividadeArrayAdapter(tasks, getApplicationContext()));
        this.mAuth = FirebaseAuth.getInstance();
        this.authListener = new FirebaseAuthListener(this);

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
    }
    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authListener);
    }

}
