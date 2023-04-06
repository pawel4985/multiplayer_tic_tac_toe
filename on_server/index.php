<?php 
ini_set('session.gc_maxlifetime', 7200);

session_start();

if (isset($_SESSION['expire']) && (time() - $_SESSION['expire'] > 7200)) {
    session_unset();     
    session_destroy();   
}
$_SESSION['expire'] = time(); 

if(isset($_SESSION['login'])){
    header("Location: chose_table.php");
}
?>
<!DOCTYPE html>
<html lang="pl">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="styles/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Multiplayer - tic-tac-toe</title>
</head>

<body>
    <div class="form">
        <h1>Zaloguj się</h1>
        <form action="" method="POST">
            <input type="text" name="login" placeholder="Login">
            <input type="password" name="password" placeholder="Hasło">
            <input type="submit" value="ZALOGUJ">
        </form>
        <a href="register.php">Nie masz jeszcze konta? Załóż je teraz!</a>
        <p class="error">
            <?php
            if (isset($_POST['login']) && isset($_POST['password'])) {
                @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
                $password = mysqli_query($co, "SELECT password FROM users WHERE login='$_POST[login]'");
                if (mysqli_num_rows($password) != 0) {
                    if (password_verify($_POST['password'], mysqli_fetch_row($password)[0])) {
                        echo ("Zalogowano!");
                        $_SESSION['login']=$_POST['login'];
                        header("Location: chose_table.php");
                    } else {
                        echo "Błędne hasło!";
                    }
                } else {
                    echo "Brak konta o podanej nazwie!";
                }
            }
            ?>
        </p>
    </div>
</body>

</html>