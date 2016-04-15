package com.tifaniwarnita.ciccatalyst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tifaniwarnita.ciccatalyst.controllers.PreferencesController;

import java.util.ArrayList;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        final EditText editTextToken = (EditText) findViewById(R.id.edit_text_token);
        Button buttonLogin = (Button) findViewById(R.id.button_login);
        TextView textViewLanjut = (TextView) findViewById(R.id.text_view_lewati);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = editTextToken.getText().toString();
                // TODO: STUB authToken(String token) return null atau ArrayList<String>: idx 1 id, idx 2 nama
                // Cek kesamaan dengan basis data, kalau sama ambil namanya, simpen di SharedPreferences
                /*
                ArrayList<String> auth = authToken(editTextToken);
                if (auth != null) {
                    /* ArrayList<String> auth = new ArrayList<>();
                    auth.add("13513055");
                    auth.add("Tifani Warnita");
                     * /
                    PreferencesController.setUser(getApplicationContext(), auth.get(0), auth.get(1));
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Token salah",
                            Toast.LENGTH_SHORT).show();
                }
                */

                if (editTextToken.getText().toString().equals("KUCING")) {
                    ArrayList<String> auth = new ArrayList<>();
                    auth.add("13513055");
                    auth.add("Tifani Warnita");
                    PreferencesController.setUser(getApplicationContext(), auth.get(0), auth.get(1));
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Token salah",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                startActivity(intent);
            }
        });
    }
}
