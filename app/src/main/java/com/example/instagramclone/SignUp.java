package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;


public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText name, punchSpeed, punchPower, kickSpeed, kickPower;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.edtName);
        punchSpeed = findViewById(R.id.edtPuchSpeed);
        punchPower = findViewById(R.id.edtPunchPower);
        kickSpeed = findViewById(R.id.edtKickSpeed);
        kickPower = findViewById(R.id.edtKickPower);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(SignUp.this);

    }





    @Override
    public void onClick(View view) {
        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", name.getText().toString());
            kickBoxer.put("punchSpeed", Integer.parseInt(punchSpeed.getText().toString()));
            kickBoxer.put("punchPower", Integer.parseInt(punchPower.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(kickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(kickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(SignUp.this, "kickBoxer Saved to the server", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e){
            Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}