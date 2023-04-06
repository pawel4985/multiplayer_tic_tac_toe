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
        <h1>Aby grać załóż darmowe konto</h1>
        <form action="" method="POST">
            <input type="text" name="login" placeholder="Login">
            <input type="password" name="password" placeholder="Hasło">
            <input type="password" name="password2" placeholder="Powtórz hasło">
            <input type="submit" value="ZAŁÓŻ KONTO">
        </form>
        <a href="index.php">Masz już konto? Zaloguj się!</a>
        <p class="error">
            <?php
            if (isset($_POST['login']) && isset($_POST['password'])) {
                if ($_POST['password'] == $_POST['password2']) {
                    @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
                    $repeated = mysqli_query($co, "SELECT * FROM users WHERE login='$_POST[login]'");
                    if (mysqli_num_rows($repeated) == 0) {
                        $passwordHash = password_hash($_POST['password'], PASSWORD_DEFAULT);
                        mysqli_query($co, "INSERT INTO users (login,password,role) VALUES ('$_POST[login]','$passwordHash','player');");
                        echo "Utworzono konto!";
                        header("Location: index.php");
                    } else {
                        echo "Istnieje już konto o podanej nazwie!";
                    }
                } else {
                    echo "Podane hasła różnią się od siebie!";
                }
            }
            ?>
        </p>
    </div>
</body>

</html>