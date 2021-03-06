package com.reachuson.app.pdb2;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


public class Doc extends AppCompatActivity {
    private Button nxt;
    private EditText name,lcn,pin;
    private Spinner Spinner;

    private FirebaseAuth mauth;
    private FirebaseDatabase mdb;
    private DatabaseReference myref;
    private String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);

        mauth = FirebaseAuth.getInstance();
        mdb = FirebaseDatabase.getInstance();
        myref = mdb.getReference();
        FirebaseUser user = mauth.getCurrentUser();
        userID = user.getUid();

        nxt = (Button)findViewById(R.id.button);
        Spinner = (Spinner)findViewById(R.id.spinner);
        name = (EditText)findViewById(R.id.editText);
        lcn = (EditText)findViewById(R.id.editText3);
        pin = (EditText)findViewById(R.id.editText4);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String retail = lcn.getText().toString();
                String pincoce = pin.getText().toString();
                String State = Spinner.getSelectedItem().toString();
                Random rand = new Random();
                Integer code = rand.nextInt() * 23005 ;
                String Ref = code.toString();
                user muser = new user();
                muser.setlcn(retail);
                muser.setInstname(Name);
                muser.setpin(pincoce);
                muser.setstate(State);
                muser.setcode(Ref);

                if(mdb.getReference(userID).child("Info").setValue(muser).isSuccessful()){
                    Toast.makeText(Doc.this, "User Information Created!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Doc.this,Home.class);
                    startActivity(i);
                }
                else {

                    Intent i = new Intent(Doc.this,Home.class);
                    startActivity(i);
                    Toast.makeText(Doc.this, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}