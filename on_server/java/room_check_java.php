<?php
@$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
$check = mysqli_query($co, "SELECT * FROM rooms");
while ($row = mysqli_fetch_array($check)) {
    echo $row[0] . " " . $row[1] . " " . $row[2] . ";";
}
