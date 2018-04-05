<?php
    session_start();
    include("config.php");

    if(isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
        $error = $_GET['error'];

        $all_accounts_query = "select * from account";
        $customer_accounts_query = "select a.aid, a.branch, a.balance, a.openDate
            from account as a inner join owns as o on a.aid = o.aid
            where o.cid = '" . $_SESSION['cid'] . "';";

        $all_accounts = mysqli_query($conn, $all_accounts_query);
        $customer_accounts = mysqli_query($conn, $customer_accounts_query);

        echo "<html><body><div><h1 align='center'>Your Accounts</h1>";
        if(mysqli_num_rows($customer_accounts) != 0) {
            echo "<table class='table' align='center'>
                <tr>
                    <th>Account ID</th>
                    <th>Branch</th>
                    <th>Balance</th>
                    <th>Open Date</th>
                </tr>";

            while($tuple_customer_account = mysqli_fetch_array($customer_accounts, MYSQLI_ASSOC)) {
                if($tuple_customer_account['aid'] != '') {
                echo "<tr>
                        <td>" . $tuple_customer_account['aid'] . "</td>
                        <td>" . $tuple_customer_account['branch'] . "</td>
                        <td>" . $tuple_customer_account['balance'] . "</td>
                        <td>" . $tuple_customer_account['openDate'] . "</td>
                    </tr>";
                }               
            }
        }
        else
            echo "You do not have any accounts.";
        echo "</table></br></div>";
        // --------------------------------------------------------------------------
        echo "<div><h1 align='center'>All Accounts</h1>";
        echo "<table class='table' align='center'>
            <tr>
                <th>Account ID</th>
                <th>Branch</th>
                <th>Balance</th>
                <th>Open Date</th>
            </tr>";

        while($tuple_all_accounts = mysqli_fetch_array($all_accounts, MYSQLI_ASSOC)) {
            if($tuple_all_accounts['aid'] != '') {
            echo "<tr>
                    <td>" . $tuple_all_accounts['aid'] . "</td>
                    <td>" . $tuple_all_accounts['branch'] . "</td>
                    <td>" . $tuple_all_accounts['balance'] . "</td>
                    <td>" . $tuple_all_accounts['openDate'] . "</td>
                </tr>";
            }               
        }
        echo "</table></div>";

        

        echo "<br><br><div align='center'>
                <form action = 'transfer.php' method = 'post'>
                    <label>fromAccount: </label><input type = 'text' name = 'from'/><br/><br/>
                    <label>toAccount: </label><input type = 'text' name = 'to'/><br/><br/>
                    <label>transferAmount: </label><input type = 'text' name = 'amount'/><br/><br/>
                    <div style = 'font-size:32px; color:#cc0000; margin-top:10px'>
                        " . $error . "
                    </div><br>                    
                    <input type = 'submit' value = ' Submit '/><br/>
                </form>                                    
            </div>";

        echo "<br><br><div align='center'><a href='index.php'> Back </a></div>";
        echo "</body></html>";
    }
    else
        header("location: login.php");
?>