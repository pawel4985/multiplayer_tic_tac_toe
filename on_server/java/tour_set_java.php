<?php
if (isset($_GET['roomid']) && isset($_GET['tour']) && isset($_GET['board'])) {
    @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
    $mo = mysqli_query($co, "SELECT moves FROM rooms WHERE id=$_GET[roomid]");
    $moves = mysqli_fetch_row($mo)[0] + 1;
    mysqli_query($co, "UPDATE rooms SET tour='$_GET[tour]',board='$_GET[board]',moves=$moves WHERE id=$_GET[roomid]");
}
