package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;


public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText name, punchSpeed, punchPower, kickSpeed, kickPower;
    private Button btnSave;
    private TextView txtGetData;
    private Button btnGetData;
    private String allKickBoxers;
    private Button btnTransition;

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
        btnGetData = findViewById(R.id.btnGetData);

        txtGetData = findViewById(R.id.txtGetData);

        btnTransition = findViewById(R.id.btnNextActivity);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("OGFvuYhiZS", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){
                            txtGetData.setText(object.get("name") + " - " + "Punch Power: " + object.get("punchPower"));
                        }
                        else
                        {
                            Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });

        btnSave.setOnClickListener(SignUp.this);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if (objects.size() > 0){
                                for(ParseObject parseObject : objects) {

                                    allKickBoxers = allKickBoxers + parseObject.get("name") + "\n";
                                }
                                Toast.makeText(SignUp.this, allKickBoxers, Toast.LENGTH_LONG).show();


                            }
                        }else{
                            Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
                startActivity(intent);
            }
        });
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