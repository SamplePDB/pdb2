package com.reachuson.app.pdb2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.reachuson.app.pdb2.Verify2;
import com.thrivecom.ringcaptcha.RingcaptchaAPIController;
import com.thrivecom.ringcaptcha.RingcaptchaService;
import com.thrivecom.ringcaptcha.lib.handlers.RingcaptchaHandler;
import com.thrivecom.ringcaptcha.lib.handlers.RingcaptchaSMSHandler;
import com.thrivecom.ringcaptcha.lib.models.RingcaptchaResponse;

public class Verify extends AppCompatActivity {
    private Button otp,verify;
    private EditText ph,ot;

    private FirebaseAuth mauth;
    private FirebaseDatabase mdb;
    private DatabaseReference myref;
    private String userID;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);


        otp = (Button)findViewById(R.id.button2);
        ph = (EditText)findViewById(R.id.editText);

        mauth = FirebaseAuth.getInstance();
        mdb = FirebaseDatabase.getInstance();
        myref = mdb.getReference();
    }
    public void send_otp(View view){
        String phno = "+91"+ph.getText().toString() ;
        if(phno.isEmpty()){
            Toast.makeText(this, "Please enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
        }
        else{
            AlertDialog.Builder alertb = new AlertDialog.Builder(this);
            alertb.setMessage("Is Your Phone Number Correct?\r\n"+phno);
            alertb.setPositiveButton("Yeah!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String phno = "+91" + ph.getText().toString();
                    FirebaseUser user2 = mauth.getCurrentUser();
                    userID = user2.getUid();
                    email = user2.getEmail();
                    myref.child(userID).child("num").setValue(phno);
                    RingcaptchaAPIController controller = new RingcaptchaAPIController("4ebyjo1okyna5u8ino8y");
                    controller.sendCaptchaCodeToNumber(getApplicationContext(), phno, RingcaptchaService.SMS, new RingcaptchaHandler() {

                        //Called when the response is successful
                        @Override
                        public void onSuccess(RingcaptchaResponse o) {

                            //Handle SMS reception automatically (only valid for verification)
                            RingcaptchaAPIController.setSMSHandler(new RingcaptchaSMSHandler() {

                                //Only called when SMS reception was detected
                                @Override
                                public boolean handleMessage(String s, String s1) {
                                    //Automatically verify PIN code
                                    return true;
                                }
                            });
                        }

                        //Called when the response is unsuccessful
                        @Override
                        public void onError(Exception e) {
                            //Display an error to the user
                        }
                    }, "440eaa00032114cb2933c5e74d8f3b9e46a4e7bc");
                    Intent inte = new Intent(Verify.this, Verify2.class);
                    startActivity(inte);

                }

            });
            alertb.setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog alert = alertb.create();
            alert.show();

        }

    }
}