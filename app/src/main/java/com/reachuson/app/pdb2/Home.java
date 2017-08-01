package com.reachuson.app.pdb2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class Home extends AppCompatActivity {



    private DatabaseReference databaseReference,ex2;
    private FirebaseDatabase mdb;
    private FirebaseAuth mauth;
    private EditText searche,name,ph;
    private ListView ans;
    private String usrID;
    private Button glass,refer;
    String authkey = "167803Ai4BFvQd597f3709";
    String senderId = "102234";
    String message = "http://www.google.com";
    String route="default";
    private Context mcon;
    List<search> searches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail);
        ans = (ListView)findViewById(R.id.viewlistitem);
        searches = new ArrayList<search>();

        mdb = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser usr = mauth.getCurrentUser();
        usrID = usr.getUid();
        glass = (Button)findViewById(R.id.button3);
        refer = (Button)findViewById(R.id.refer);
        searche = (EditText)findViewById(R.id.editText3);
        glass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searches.clear();
                String sea = searche.getText().toString();
                ex2 = FirebaseDatabase.getInstance().getReference("Suppliers");
                Query ex1 = ex2.orderByChild("name").startAt(sea).endAt(sea+"\uf8ff");

                ex1.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        searches.clear();
                        for(DataSnapshot searcheshot : dataSnapshot.getChildren() ){
                            search res = searcheshot.getValue(search.class);

                            if(res != null) {
                                searches.add(res);

                            }
                            else {
                                Toast.makeText(mcon, "No Listing!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        list_item adapter = new list_item(Home.this,searches);
                        ans.setAdapter(adapter);

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });
    }
    public void referclk(View view){
        setContentView(R.layout.referral);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Want to Exit?")
                .setNegativeButton("I Do",null)
                .setPositiveButton("I Don't", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setContentView(R.layout.activity_retail);
                    }
                }).create().show();

    }
    public void searclk(View view){
        setContentView(R.layout.activity_retail);
    }

    public void refer(View View) throws IOException{
        name = (EditText)findViewById(R.id.editText6);
        ph = (EditText)findViewById(R.id.editText5);

        String Name = name.getText().toString();
        final String Ph = "91"+ph.getText().toString();

        user usr = new user();
        usr.setName(Name);
        usr.setnumber(Ph);
        mdb.getReference(usrID).child("Referral").setValue(usr);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    URLConnection myURLConnection=null;
                    URL myURL=null;
                    BufferedReader reader=null;
                    String encoded_message= URLEncoder.encode(message);
                    String mainUrl="https://control.msg91.com/api/sendhttp.php?";
                    StringBuilder sbPostData= new StringBuilder(mainUrl);
                    sbPostData.append("authkey="+authkey);
                    sbPostData.append("&mobiles="+Ph);
                    sbPostData.append("&message="+encoded_message);
                    sbPostData.append("&route="+route);
                    sbPostData.append("&sender="+senderId);
                    mainUrl = sbPostData.toString();

                    try
                    {
                        myURL = new URL(mainUrl);
                        myURLConnection = myURL.openConnection();
                        myURLConnection.getInputStream();
                        myURLConnection.connect();
                        reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));


                        String response;
                        while ((response = reader.readLine()) != null)
                            Log.d("RESPONSE", ""+response);

                            reader.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        Toast.makeText(this, "Referral Successfully Sent!", Toast.LENGTH_SHORT).show();


    }
}
