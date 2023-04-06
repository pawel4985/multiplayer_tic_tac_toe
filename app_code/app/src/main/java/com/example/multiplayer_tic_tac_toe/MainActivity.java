package com.example.multiplayer_tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        TextView reg=findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(in);
            }
        });

        Button logB=findViewById(R.id.logB);
        logB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView log=findViewById(R.id.log);
                String login = String.valueOf(log.getText());
                TextView pas=findViewById(R.id.pas);
                String password = String.valueOf(pas.getText());
                if(Objects.equals(Requests.login(login, password), "success")){
                    Toast.makeText(MainActivity.this, "Zalogowano", Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(MainActivity.this,ChoseTable.class);
                    in.putExtra("username",login);
                    startActivity(in);
                    finish();
                } else if (Objects.equals(Requests.login(login, password), "fail")) {
                    Toast.makeText(MainActivity.this, "Niepoprawne hasło", Toast.LENGTH_SHORT).show();
                }else if(Objects.equals(Requests.login(login, password), "noaccount")){
                    Toast.makeText(MainActivity.this, "Nie ma użytkownika o podanej nazwie", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}