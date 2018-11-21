package tads.bianca.gerenciador;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tads.bianca.gerenciador.Control.FirebaseAuthListener;
import tads.bianca.gerenciador.Model.User;

public class SignUpActivity extends AppCompatActivity {

    private EditText edName;
    private EditText edEmail;
    private EditText edPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuthListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        this.mAuth = FirebaseAuth.getInstance();
        this.authListener = new FirebaseAuthListener(this);
        edName = findViewById(R.id.edit_name);
        edEmail = findViewById(R.id.edit_email);
        edPassword = findViewById(R.id.edit_password);
        Button buttonSignUp = (Button) findViewById(R.id.button_sign_up);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSignUpClick(view);
            }
        });

    }

    public void buttonSignUpClick(View view) {

        final String name = edName.getText().toString();
        final String email = edEmail.getText().toString();
        final String password = edPassword.getText().toString();
        if (email != null && password != null && !email.isEmpty() && !password.isEmpty()){
            final FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            String msg = task.isSuccessful() ? "SIGN UP OK!":
                                                               "SIGN UP ERROR!";
                            Toast.makeText(SignUpActivity.this, msg,
                                    Toast.LENGTH_SHORT).show();
                            if (task.isSuccessful()) {
                                User tempUser = new User(name, email);
                                DatabaseReference drUsers = FirebaseDatabase.

                                        getInstance().getReference("users");
                                drUsers.child(mAuth.getCurrentUser().getUid()).
                                        setValue(tempUser);
                            }
                        }
                    });
        }else {
            Toast.makeText(SignUpActivity.this, "Blank Spaces are not allowed",
                    Toast.LENGTH_SHORT).show();
        }

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
