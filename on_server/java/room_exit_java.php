<?php
if (isset($_GET['roomid']) && isset($_GET['playertype'])) {
    @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
    if ($_GET['playertype'] == "x") {
        mysqli_query($co, "UPDATE rooms set playerX=0 WHERE id=$_GET[roomid]");
    } else if ($_GET['playertype'] == "o") {
        mysqli_query($co, "UPDATE rooms set playerO=0 WHERE id=$_GET[roomid]");
    }
    mysqli_query($co, "UPDATE rooms SET board='0,0,0,:0,0,0,:0,0,0,:',tour='x',moves=0,xscore=0,oscore=0,xnext=0,onext=0");
}
