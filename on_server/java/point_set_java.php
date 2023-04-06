<?php
if (isset($_GET['roomid']) && isset($_GET['player'])||isset($_GET['roomid'])) {
    @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
    $actScore = mysqli_query($co, "SELECT xscore,oscore FROM rooms WHERE id=$_GET[roomid]");
  if(isset($_GET['player'])){
        if ($_GET['player'] == "x") {
        $score = mysqli_fetch_row($actScore)[0] + 1;
        mysqli_query($co, "UPDATE rooms SET xscore=$score WHERE id=$_GET[roomid]");
    } else if ($_GET['player'] == "o") {
        $score = mysqli_fetch_row($actScore)[1] + 1;
        mysqli_query($co, "UPDATE rooms SET oscore=$score WHERE id=$_GET[roomid]");
    }
  }
  mysqli_query($co, "UPDATE rooms SET board='0,0,0,:0,0,0,:0,0,0,:' WHERE id=$_GET[roomid]");
}
