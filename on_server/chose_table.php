<?php 
ini_set('session.gc_maxlifetime', 7200);

session_start();

if (isset($_SESSION['expire']) && (time() - $_SESSION['expire'] > 7200)) {
    session_unset();     
    session_destroy();   
}
$_SESSION['expire'] = time(); 

if(!isset($_SESSION['login'])){
    header("Location: index.php");
}
if(isset($_SESSION['player_type'])&&isset($_SESSION['roomid'])){
    @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
    if ($_SESSION['player_type'] == "x") {
        mysqli_query($co, "UPDATE rooms set playerX=0 WHERE id=$_SESSION[roomid]");
    } else if ($_SESSION['player_type'] == "o") {
        mysqli_query($co, "UPDATE rooms set playerO=0 WHERE id=$_SESSION[roomid]");
    }
    mysqli_query($co, "UPDATE rooms SET board='0,0,0,:0,0,0,:0,0,0,:',tour='x',moves=0,xscore=0,oscore=0,xnext=0,onext=0");
    unset($_SESSION['player_type']);
    unset($_SESSION['roomid']);
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
    <div class="tables">
        <?php
        @$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");
        $rooms = mysqli_query($co, "SELECT * FROM rooms");
        while ($row = mysqli_fetch_array($rooms)) {
            echo "<form class='table' method='POST' id=$row[0]>";
            echo "<input type='hidden' name='roomid' value=$row[0]>";
            echo "<div class='str'>";
            echo "<p >ROOM $row[0]</p>";
            if ($row[1] != "0" && $row[2] != "0") {
                echo "<p class='players'>2/2</p>";
            } else if ($row[1] == "0" && $row[2] == "0") {
                echo "<p class='players'>0/2</p>";
            } else {
                echo "<p class='players'>1/2</p>";
            }
            echo "</div>";
            echo "<div class='type'>";
            if($row[1]!=0 && $row[2]==0){
                echo "<div></div>";
                echo "<div class='o' onClick='selectRoom(this.id)' id=o_"."$row[0]></div>";
            }else if($row[1]==0 && $row[2]!=0){
                echo "<div class='x' onClick='selectRoom(this.id)' id=x_"."$row[0]></div>";
                echo "<div></div>";
            }else if($row[1]==0 && $row[2]==0){
                echo "<div class='x' onClick='selectRoom(this.id)' id=x_"."$row[0]></div>";
                echo "<div class='o' onClick='selectRoom(this.id)' id=o_"."$row[0]></div>";
            }else if($row[1]!=0 && $row[2]!=0){
                echo "<div></div>";
                echo "<div></div>";
            }
            echo "</div>";
            echo "</form>";
        }


        if (isset($_GET['roomid']) && isset($_GET['player_type']) && isset($_SESSION['login'])) {
            if ($_GET['player_type'] == "x") {
                $check = mysqli_query($co, "SELECT playerX FROM rooms WHERE id=$_GET[roomid]");
                if (mysqli_num_rows($check) > 0) {
                    if (mysqli_fetch_array($check)[0] == "0") {
                        mysqli_query($co, "UPDATE rooms SET playerX = '$_SESSION[login]' WHERE id=$_GET[roomid]");
                        $_SESSION['player_type']=$_GET['player_type'];
                        $_SESSION['roomid']=$_GET['roomid'];
                        header("Location: game.php");
                    } else {
                        echo "<p class='error'>To miejsce jest zajęte</p>";
                    }
                } else {
                    echo "<p class='error'>To miejsce jest zajęte</p>";
                }
            } else if ($_GET['player_type'] == "o") {
                $check = mysqli_query($co, "SELECT playerO FROM rooms WHERE id=$_GET[roomid]");
                if (mysqli_num_rows($check) > 0) {
                    if (mysqli_fetch_array($check)[0] == "0") {
                        mysqli_query($co, "UPDATE rooms SET playerO = '$_SESSION[login]' WHERE id=$_GET[roomid]");
                        $_SESSION['player_type']=$_GET['player_type'];
                        $_SESSION['roomid']=$_GET['roomid'];
                        header("Location: game.php");
                    } else {
                        echo "<p class='error'>To miejsce jest zajęte</p>";
                    }
                } else {
                    echo "<p class='error'>To miejsce jest zajęte</p>";
                }
            }
        }

        ?>
    </div>
    <script>
        function selectRoom(selected){
            let data = selected.split("_");
            let form = document.getElementById(data[1]);
            window.location="http://localhost/tic_tac_toe/chose_table.php?player_type="+data[0]+"&&roomid="+data[1];
            form.submit();
            window.location="http://localhost/tic_tac_toe/game.php";
        }
    </script>
</body>

</html>