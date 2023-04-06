package com.example.multiplayer_tic_tac_toe;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class Requests {
    public static String register(String login, String password) {
        try {
            String urlS = "https://your_site/java/register_java.php?login=" + login + "&&password=" + password;
            URL url = new URL(urlS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String login(String login, String password) {
        try {
            String urlS = "https://your_site/java/login_java.php?login=" + login + "&&password=" + password;
            URL url = new URL(urlS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String choseRoom(String playerType, int roomId, String nickName) {
        try {
            String urlS = "https://your_site/java/room_java.php?room=" + roomId + "&&player_type=" + playerType + "&&nick=" + nickName;
            URL url = new URL(urlS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[][] checkRoom() {
        String data = null;
        try {
            URL url = new URL("https://your_site/java/room_check_java.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            data = content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String[] roomsAc=data.split(";");
        String[][] roomsAccess=new String[4][2];
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                String[] temp=roomsAc[i].split(" ");
                roomsAccess[i][j]=temp[j+1];
            }
        }
        return roomsAccess;
    }

    public static boolean roomExit(int roomId,String playerType){
        try {
            String urlS = "https://your_site/java/room_exit_java.php?roomid=" + roomId + "&&playertype=" + playerType;
            URL url = new URL(urlS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.getResponseCode();
            con.disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String[] interfaceGet(int roomId,String player){
        try {
            String urlS = "https://your_site/java/interface_get_java.php?roomid=" + roomId;
            URL url = new URL(urlS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            String inter=content.toString();
            return inter.split(";");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean tourSet(int roomId,String tour,String[][] board){
        String boardS = "";
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                boardS+=board[i][j]+",";
            }
            boardS+=":";
        }
        try {
            String urlS = "https://your_site/java/tour_set_java.php?roomid=" + roomId + "&&tour=" + tour+"&&board="+boardS;
            URL url = new URL(urlS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.getResponseCode();
            con.disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void scoreSet(int roomId,String player){
        String urlS;
        try {
            if(player==null){
                urlS = "https://your_site/java/point_set_java.php?roomid=" + roomId;
            }else{
                urlS = "https://your_site/java/point_set_java.php?roomid=" + roomId + "&&player=" + player;
            }
            URL url = new URL(urlS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.getResponseCode();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

