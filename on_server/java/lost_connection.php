<?php
  set_time_limit(0);

$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");

while (true) {
    for ($i = 1; $i < 5; $i += 1) {
        $act_time = time();
        $get_time = mysqli_query($co, "SELECT networkX, networkO FROM rooms WHERE id = $i");
        $data = mysqli_fetch_row($get_time);
        $timeX = $data[0];
        $timeO = $data[1];
        if ($act_time - $timeX > 15 && $timeX != 0) {
            mysqli_query($co, "UPDATE rooms SET playerX = 0 WHERE id = $i");
        }
        if ($act_time - $timeO > 15 && $timeO != 0) {
            mysqli_query($co, "UPDATE rooms SET playerO = 0 WHERE id = $i");
        }
    }
    sleep(10);
}

