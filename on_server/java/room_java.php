<?php
if (isset($_GET['room']) && isset($_GET['player_type']) && isset($_GET['nick'])) {
    @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
    if ($_GET['player_type'] == "x") {
        $check = mysqli_query($co, "SELECT playerX FROM rooms WHERE id=$_GET[room]");
        if (mysqli_num_rows($check) > 0) {
            if (mysqli_fetch_array($check)[0] == "0") {
                mysqli_query($co, "UPDATE rooms SET playerX = '$_GET[nick]' WHERE id=$_GET[room]");
            } else {
                echo "fail";
            }
        } else {
            echo "fail";
        }
    } else if ($_GET['player_type'] == "o") {
        $check = mysqli_query($co, "SELECT playerO FROM rooms WHERE id=$_GET[room]");
        if (mysqli_num_rows($check) > 0) {
            if (mysqli_fetch_array($check)[0] == "0") {
                mysqli_query($co, "UPDATE rooms SET playerO = '$_GET[nick]' WHERE id=$_GET[room]");
            } else {
                echo "fail";
            }
        } else {
            echo "fail";
        }
    }
}
