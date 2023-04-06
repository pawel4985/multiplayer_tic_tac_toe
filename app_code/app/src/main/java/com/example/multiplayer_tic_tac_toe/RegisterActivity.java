package com.example.multiplayer_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView logA=findViewById(R.id.logA);
        logA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(in);
                finish();
            }
        });

        findViewById(R.id.regB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView lg = findViewById(R.id.logR);
                String login = String.valueOf(lg.getText());
                TextView ps = findViewById(R.id.pasR);
                String password = String.valueOf(ps.getText());
                TextView ps2 = findViewById(R.id.pasR2);
                String password2 = String.valueOf(ps2.getText());
                if(password.length()>4){
                    if(password.equals(password2)){
                        if(Objects.equals(Requests.register(login, password), "success")){
                            Toast.makeText(RegisterActivity.this, "Utworzono konto", Toast.LENGTH_SHORT).show();
                            Intent in=new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(in);
                            finish();
                        }else if(Objects.equals(Requests.register("antoni", "antoni"), "fail")){
                            Toast.makeText(RegisterActivity.this, "Istnieje już konto o podanej nazwie", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Podane hasła różnią się", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Wybrane hasło jest za krótkie", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}