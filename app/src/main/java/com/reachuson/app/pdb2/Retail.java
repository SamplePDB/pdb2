package com.reachuson.app.pdb2;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class Retail extends AppCompatActivity {



    private DatabaseReference databaseReference,ex2,ex3;
    private EditText searche;
    private ListView ans;
    private Button glass;
    private Context mcon;
    List<String> searches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail);
        ans = (ListView)findViewById(R.id.viewlistitem);
        searches = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Suppliers");
        glass = (Button)findViewById(R.id.button3);
        searche = (EditText)findViewById(R.id.editText3);
        glass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sea = searche.getText().toString();
                ex2 = FirebaseDatabase.getInstance().getReference("Suppliers/"+sea);
                ex3 = FirebaseDatabase.getInstance().getReference("Supplier2/"+sea);
                ex2.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        searches.clear();
                        for(DataSnapshot searcheshot : dataSnapshot.getChildren() ){
                            String res = searcheshot.getValue(String.class);

                            if(res != null) {
                                searches.add(res);

                            }
                            else {
                                Toast.makeText(mcon, ".|.", Toast.LENGTH_SHORT).show();
                            }

                        }

                        list_item adapter = new list_item(Retail.this,searches);
                        ans.setAdapter(adapter);

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ex3.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot searcheshot : dataSnapshot.getChildren() ){
                            String res = searcheshot.getValue(String.class);

                            if(res != null) {
                                searches.add(res);

                            }
                            else {
                                Toast.makeText(mcon, ".|.", Toast.LENGTH_SHORT).show();
                            }

                        }

                        list_item adapter = new list_item(Retail.this,searches);
                        ans.setAdapter(adapter);

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }



}
