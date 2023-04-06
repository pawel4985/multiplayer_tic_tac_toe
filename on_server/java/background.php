<?php
function boardGet($data)
{
    $data = explode(':', $data);
    $board = array(array());
    for ($i = 0; $i < 3; $i += 1) {
        $temp = explode(',', $data[$i]);
        for ($j = 0; $j < 3; $j += 1) {
            $board[$i][$j] = $temp[$j];
        }
    }
    return $board;
}
function checkWin($board, $player)
{
    for ($i = 0; $i < 3; $i++) {
        if ($board[$i][0] == $player && $board[$i][1] == $player && $board[$i][2] == $player) {
            return true;
        }
        if ($board[0][$i] == $player && $board[1][$i] == $player && $board[2][$i] == $player) {
            return true;
        }
    }

    if ($board[0][0] == $player && $board[1][1] == $player && $board[2][2] == $player) {
        return true;
    }
    if ($board[2][0] == $player && $board[1][1] == $player && $board[0][2] == $player) {
        return true;
    }

    return false;
}

function checkDraw($board)
{
    for ($i = 0; $i < 3; $i++) {
        for ($j = 0; $j < 3; $j++) {
            if ($board[$i][$j] == "0") {
                return false;
            }
        }
    }
    return true;
}




set_time_limit(0);

@$co = mysqli_connect("localhost", "root", "", "api") or die("Błąd połączenia z bazą danych!");


while (true) {
    for ($i = 1; $i < 5; $i += 1) {
        $dataR = mysqli_query($co, "SELECT board,playerX,playerO FROM rooms WHERE id=$i");
        $data = mysqli_fetch_array($dataR);
        if ($data[1] != "0" && $data[2] != "0") {
            mysqli_query($co, "UPDATE rooms SET state='play' WHERE id=$i");
            $board = boardGet($data[0]);
            if (checkWin($board, "x")) {
                mysqli_query($co, "UPDATE rooms SET state='x' WHERE id=$i");
            } else if (checkWin($board, "o")) {
                mysqli_query($co, "UPDATE rooms SET state='o' WHERE id=$i");
            } else if (checkDraw($board)) {
                mysqli_query($co, "UPDATE rooms SET state='d' WHERE id=$i");
            }
        } else {
            mysqli_query($co, "UPDATE rooms SET state='stop' WHERE id=$i");
        }
    }

    sleep(1);
}
