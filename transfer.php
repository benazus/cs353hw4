<?php
    session_start();
    include("config.php");
    if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
        $from = $_POST['from'];
        $to = $_POST['to'];
        $amount = (is_numeric($_POST['amount']) ? (int)$_POST['amount'] : 0);

        $verify_from_query = "select aid from owns where cid = '" . $_SESSION['cid'] . "';";
        $verify_from_table = mysqli_query($conn, $verify_from_query);

        $valid_from = 0;
        while($tuple_verify_from = mysqli_fetch_array($verify_from_table, MYSQLI_ASSOC)) {
            if($tuple_verify_from['aid'] == $from) {
                $valid_from = 1;
                break;
            }
        }
        
        if($valid_from == 0)
            header("location: transaction.php?error=invalid_fromAccount");




        $verify_to_query = "select aid, balance from account;";
        $verify_to_table = mysqli_query($conn, $verify_to_query);
        $valid_to = 0;
        $amount_to = 0;
        while($tuple_to_verify = mysqli_fetch_array($verify_to_table, MYSQLI_ASSOC)) {
            if($tuple_to_verify['aid'] == $to) {
                $valid_to = 1;
                $amount_to = (int) $tuple_to_verify['balance'];
                break;
            }
        }
        
        if($valid_to == 0)
            header("location: transaction.php?error=invalid_toAccount");


        $verify_amount_query = "select balance from account where aid = '" . $from . "';";
        $verify_amount_table = mysqli_query($conn, $verify_amount_query);
        $valid_amount = 0;
        $amount_from = 0;
        while($tuple_amount_verify = mysqli_fetch_array($verify_amount_table, MYSQLI_ASSOC)) {
            $i = (int) $tuple_amount_verify['balance'];
            if($i > $amount && $amount > 0) {
                $valid_amount = 1;
                $amount_from = $i;  
                break;
            }
        }
        
        if($valid_amount == 0)
            header("location: transaction.php?error=invalid_transferAmount");
        


        $from_balance = $amount_from - $amount;
        $to_balance = $amount_to + $amount;

        if($from_balance > 0) {
            $update_from = "update account set balance = " . $from_balance . " where aid = '" . $from . "';";
            $update_to = "update account set balance = " . $to_balance . " where aid = '" . $to . "';";
            mysqli_query($conn, "SET FOREIGN_KEY_CHECKS=0;");
            mysqli_query($conn, $update_from);
            mysqli_query($conn, $update_to);
            mysqli_query($conn, "SET FOREIGN_KEY_CHECKS=1;");
            header("location: transaction.php?error=transaction_succesful");
        }
        else {
            header("location: transaction.php?error=invalid_balance");
        }
    }
    else
        header("location: login.php");
        // $tuple_amount_verify['balance'] > 
?>

        