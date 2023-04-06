<?php
if (isset($_GET['login']) && isset($_GET['password'])) {
    @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
    $password = mysqli_query($co, "SELECT password FROM users WHERE login='$_GET[login]'");
    if (mysqli_num_rows($password) != 0) {
        if (password_verify($_GET['password'], mysqli_fetch_row($password)[0])) {
            echo ("success");
        } else {
            echo "fail";
        }
    } else {
        echo "noaccount";
    }
}
