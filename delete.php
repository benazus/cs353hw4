<?php
    session_start();
    include("config.php");
    if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
        $account_id = $_GET['aid'];

        $delete_from_account = "delete from account where aid = '" . $account_id . "';";
        $delete_from_owns = "delete from owns where aid = '" . $account_id . "';";
        mysqli_query($conn, "SET FOREIGN_KEY_CHECKS=0;");
        mysqli_query($conn, $delete_from_account);
        mysqli_query($conn, $delete_from_owns);
        mysqli_query($conn, "SET FOREIGN_KEY_CHECKS=1;");
        header("location: index.php?delete=success");
    }
    else
        header("location: login.php");
?>