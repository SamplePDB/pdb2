package com.reachuson.app.pdb2;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reachuson.app.pdb2.MainActivity;
import com.thrivecom.ringcaptcha.RingcaptchaAPIController;
import com.thrivecom.ringcaptcha.lib.handlers.RingcaptchaHandler;
import com.thrivecom.ringcaptcha.lib.models.RingcaptchaResponse;

public class Verify2 extends AppCompatActivity {
    private Button verify;
    private EditText ot;
    private TextView mTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify2);

        verify = (Button)findViewById(R.id.button);
        ot = (EditText)findViewById(R.id.editText2);
        mTextField = (TextView)findViewById(R.id.textView2);

        new CountDownTimer(40000,1000){
            public void onTick(long millisUntilFinished) {
                mTextField.setText("OTP will arrive within: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("Enter The OTP, Arrived!");
            }

        }.start();



    }
    public void verify(View view){
        RingcaptchaAPIController controller = new RingcaptchaAPIController("4ebyjo1okyna5u8ino8y");
        String otp = ot.getText().toString();
        if(otp.isEmpty()){
            Toast.makeText(this, "Enter the OTP Received", Toast.LENGTH_SHORT).show();
        }
        else{

            controller.verifyCaptchaWithCode(getApplicationContext(), otp, new RingcaptchaHandler() {

                //Called when the response is successful
                @Override
                public void onSuccess(RingcaptchaResponse ringcaptchaResponse) {
                    //Clear SMS handler to avoid multiple verification attempts
                    RingcaptchaAPIController.setSMSHandler(null);
                    Toast.makeText(Verify2.this, "!!!Verified!!!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Verify2.this,MainActivity.class);
                    startActivity(i);
                }

                //Called when the response is unsuccessful
                @Override
                public void onError(Exception e) {
                    //Display an error to the user
                }
            },"440eaa00032114cb2933c5e74d8f3b9e46a4e7bc");
        }
    }

}

