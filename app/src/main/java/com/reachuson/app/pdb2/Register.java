package com.reachuson.app.pdb2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thrivecom.ringcaptcha.RingcaptchaApplication;
import com.thrivecom.ringcaptcha.RingcaptchaApplicationHandler;
import com.thrivecom.ringcaptcha.RingcaptchaVerification;


public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText username,password;
    private ProgressBar Spinner;

    //private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mdb;
    private FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner = (ProgressBar)findViewById(R.id.progressBar4);
        Spinner.setVisibility(View.GONE);

        mdb = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();


    }


    public void register(View view){
        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText4);
        final String email = username.getText().toString();
        final String pass = password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email should not be empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Password should not be empty", Toast.LENGTH_SHORT).show();
        }
        if(pass.length()<6){
            Toast.makeText(this, "Password should be atleast 6 characters", Toast.LENGTH_SHORT).show();
        }

        Spinner.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){Toast.makeText(Register.this, "Account Creation Failed!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Spinner.setVisibility(View.GONE);
                            Toast.makeText(Register.this, "User Created!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Register.this,Verify.class);
                            startActivity(i);


                        }
                    }
                });

    }
    public void logi(View vi){
        Intent inte = new Intent(Register.this,Login.class);
        startActivity(inte);
    }

}
