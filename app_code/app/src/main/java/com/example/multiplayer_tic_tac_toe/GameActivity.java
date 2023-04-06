package com.example.multiplayer_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private String nickname;
    private int roomId;
    private String playerType;

    private boolean tour;

    private String[][] board;

    private String[] nickNames;
    private String state;

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent in=getIntent();
        this.nickname=in.getStringExtra("nickname");
        this.roomId=Integer.parseInt(in.getStringExtra("roomId"));
        this.playerType=in.getStringExtra("playerType");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        interfaceGet();
                    }
                });
            }
        };
        long delay = 0;
        long period = 1500; // 1.5 sekundy
        this.timer.scheduleAtFixedRate(task, delay, period);
    }
    @Override
    public void onBackPressed(){
        if(Requests.roomExit(this.roomId,this.playerType)){
            this.timer.cancel();
            finish();
            super.onBackPressed();
        }else{
            Toast.makeText(this, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onStop() {
        Requests.roomExit(this.roomId,this.playerType);
        this.timer.cancel();
        finish();
        super.onStop();
    }
    public void interfaceGet() {
        TextView state=findViewById(R.id.state);
        if(Requests.interfaceGet(this.roomId,this.playerType)!=null){
            String[] data=Requests.interfaceGet(this.roomId,this.playerType);

            //state get
            this.state=data[4];
            if(Objects.equals(this.state, "stop")){
                state.setText("Oczekiwanie na gracza");
            }else if(Objects.equals(this.state, "d")){
                Requests.scoreSet(this.roomId,null);
            } else if (Objects.equals(this.playerType, this.state) && (!Objects.equals(this.state, "play"))) {
                Requests.scoreSet(this.roomId,this.state);
            }

            //nicknames set
            this.nickNames=data[0].split(" ");
            TextView nickX=findViewById(R.id.nick_x);
            TextView nickO=findViewById(R.id.nick_o);
            if(!Objects.equals(this.nickNames[0], "0")){
                nickX.setText(this.nickNames[0]);
            }else{
                nickX.setText("");
            }
            if(!Objects.equals(this.nickNames[1], "0")){
                nickO.setText(this.nickNames[1]);
            }else{
                nickO.setText("");
            }

            //tour set
            char tour=data[2].charAt(0);
            if(tour==this.playerType.charAt(0) && Objects.equals(this.state, "play")){
                state.setText("Twoja kolej!");
                this.tour=true;
            }else if(Objects.equals(this.state, "play")){
                state.setText("Kolej przeciwnika!");
                this.tour=false;
            }

            //score set
            TextView scoreX=findViewById(R.id.score_x);
            TextView scoreO=findViewById(R.id.score_o);
            String[] score=data[1].split(" ");
            scoreX.setText(score[0]);
            scoreO.setText(score[1]);

            //board get
            String[] br=data[3].split(":");
            this.board=new String[3][3];
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    String[] temp=br[i].split(",");
                    this.board[i][j]=temp[j];
                    int resourceId = this.getResources().getIdentifier("p"+i+"_"+j, "id", this.getPackageName());
                    ImageView im =findViewById(resourceId);
                    if(Objects.equals(this.board[i][j], "x")){
                        int img = this.getResources().getIdentifier("x", "drawable", this.getPackageName());
                        im.setImageResource(img);
                    }else if(Objects.equals(this.board[i][j], "o")){
                        int img = this.getResources().getIdentifier("o", "drawable", this.getPackageName());
                        im.setImageResource(img);
                    }else{
                        im.setImageResource(android.R.color.transparent);
                    }
                }
            }

        }else{
            state.setText("Brak połączenia z internetem");
        }

    }
    public void play(View v){
        int id=v.getId();
        ImageView im = findViewById(id);
        TextView state=findViewById(R.id.state);
        if(this.nickNames[0].equals("0") && this.nickNames[1].equals("0")){
            Requests.roomExit(this.roomId,this.playerType);
            this.timer.cancel();
            finish();
        }
        if(this.tour==true && Objects.equals(this.state, "play")){
            String[] index=String.valueOf(getResources().getResourceEntryName(id)).replace("p","").split("_");
            int i= Integer.parseInt(index[0]);
            int j= Integer.parseInt(index[1]);
            if(Objects.equals(this.board[i][j], "0")){
                String[][] temp = this.board;
                temp[i][j]=this.playerType;
                this.tour=false;
                state.setText("Kolej przeciwnika!");
                int img = this.getResources().getIdentifier(this.playerType, "drawable", this.getPackageName());
                im.setImageResource(img);
                if(Objects.equals(this.playerType, "x")) Requests.tourSet(this.roomId,"o",temp);
                if(Objects.equals(this.playerType, "o")) Requests.tourSet(this.roomId,"x",temp);
            }
        }
    }

}