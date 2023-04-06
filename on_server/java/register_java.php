<?php
if (isset($_GET['login']) && isset($_GET['password'])) {
    @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
    $repeated = mysqli_query($co, "SELECT * FROM users WHERE login='$_GET[login]'");
    if (mysqli_num_rows($repeated) == 0) {
        $passwordHash = password_hash($_GET['password'], PASSWORD_DEFAULT);
        mysqli_query($co, "INSERT INTO users (login,password,role) VALUES ('$_GET[login]','$passwordHash','player');");
        echo "success";
    } else {
        echo "fail";
    }
}
