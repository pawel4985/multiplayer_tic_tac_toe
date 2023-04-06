<?php
if (isset($_GET['roomid'])&&isset($_GET['player'])) {
    @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
    $nick = mysqli_query($co, "SELECT playerX,playerO FROM rooms WHERE id=$_GET[roomid]");
    $result = mysqli_fetch_row($nick);
    echo $result[0] . " " . $result[1] . ";";
    $score = mysqli_query($co, "SELECT xscore,oscore FROM rooms WHERE id=$_GET[roomid]");
    $result1 = mysqli_fetch_row($score);
    echo $result1[0] . " " . $result1[1] . ";";
    $tour = mysqli_query($co, "SELECT tour FROM rooms WHERE id=$_GET[roomid]");
    echo mysqli_fetch_row($tour)[0] . ";";
    $board = mysqli_query($co, "SELECT board FROM rooms WHERE id=$_GET[roomid]");
    echo mysqli_fetch_row($board)[0] . ";";
    $state = mysqli_query($co, "SELECT state FROM rooms WHERE id=$_GET[roomid]");
    echo mysqli_fetch_row($state)[0] . ";";
    $time=time();
    if($_GET['player']=="x"){
      mysqli_query($co,"UPDATE rooms SET networkX=$time WHERE id=$_GET[roomid]");
    }else if($_GET['player']=="o"){
      mysqli_query($co,"UPDATE rooms SET networkO=$time WHERE id=$_GET[roomid]");
    }
  
}
