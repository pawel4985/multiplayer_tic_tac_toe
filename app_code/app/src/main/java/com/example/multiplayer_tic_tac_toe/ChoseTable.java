package com.example.multiplayer_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ChoseTable extends AppCompatActivity {
    private Timer timer = new Timer();
    private String nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_table);
        nickname=getIntent().getStringExtra("username");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        roomAcces();
                    }
                });
            }
        };
        long delay = 0;
        long period = 1000; // 1 sekunda
        this.timer.scheduleAtFixedRate(task, delay, period);
    }
    public void onResume(){
        this.timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        roomAcces();
                    }
                });
            }
        };
        long delay = 0;
        long period = 1000; // 1 sekunda
        this.timer.scheduleAtFixedRate(task, delay, period);
        super.onResume();
    }
    public void selectRoom(View v){
        roomAcces();
        String sc=String.valueOf(getResources().getResourceEntryName(v.getId()));
        String[] selected=sc.split("_");
        String playerType=selected[0];                      //x or o
        int roomId= Integer.parseInt(selected[1]);          //1,2,3,4
        String check=Requests.choseRoom(playerType, roomId, nickname);
        if(check!=null){
            if(check.equals("fail")){
                Toast.makeText(this, "To miejsce w pokoju jest zajęte! \n Wybierz inne.", Toast.LENGTH_SHORT).show();
            }else{
                Intent in = new Intent(ChoseTable.this,GameActivity.class);
                in.putExtra("nickname",nickname);
                in.putExtra("roomId",String.valueOf(roomId));
                in.putExtra("playerType",playerType);
                timer.cancel();
                startActivity(in);
            }
        }else{
            Toast.makeText(this, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show();
        }
    }
    public void roomAcces(){
        if(Requests.checkRoom()!=null){
            String[][] data = Requests.checkRoom();
            for(int i=0;i<4;i++){
                int pom=i+1;
                int players=0;
                if(!Objects.equals(data[i][0], "0")){
                    players += 1;
                    int resourceId1 = this.getResources().getIdentifier("x_"+pom, "id", this.getPackageName());
                    findViewById(resourceId1).setVisibility(View.INVISIBLE);
                }else{
                    int resourceId2 = this.getResources().getIdentifier("x_"+pom, "id", this.getPackageName());
                    findViewById(resourceId2).setVisibility(View.VISIBLE);
                }
                if(!Objects.equals(data[i][1], "0")){
                    players += 1;
                    int resourceId3 = this.getResources().getIdentifier("o_"+pom, "id", this.getPackageName());
                    findViewById(resourceId3).setVisibility(View.INVISIBLE);
                }else{
                    int resourceId4 = this.getResources().getIdentifier("o_"+pom, "id", this.getPackageName());
                    findViewById(resourceId4).setVisibility(View.VISIBLE);
                }
                int numPlayersId = this.getResources().getIdentifier("numP"+pom, "id", this.getPackageName());
                TextView numPlayers=findViewById(numPlayersId);
                numPlayers.setText(players+"/2");
            }
        }else{
            Toast.makeText(this, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show();
        }

    }
}