<?php
    session_start();
    include("config.php");

    if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
        $delete = $_GET['delete'];
        $customer_id = $_SESSION['cid'];
        $query_aid = "select aid from owns where cid = '$customer_id'";
        $result_table = mysqli_query($conn, $query_aid);
        echo "<html><div align='center'>";
        if(mysqli_num_rows($result_table) != 0) {
            echo "<table class='table' align='center'>
                <tr>
                    <th>Account ID</th>
                    <th>Branch</th>
                    <th>Balance</th>
                    <th>Open Date</th>
                    <th>Close Account?</th>
                </tr>";

            while($tuple_owns = mysqli_fetch_array($result_table, MYSQLI_ASSOC)) {
                $query_account_info = "select * from account where aid = '" . $tuple_owns['aid'] . "';";
                $account = mysqli_query($conn, $query_account_info);
                $tuple_account = mysqli_fetch_array($account);
                if($tuple_account['aid'] != '') {
                echo "<tr>
                        <td>" . $tuple_account['aid'] . "</td>
                        <td>" . $tuple_account['branch'] . "</td>
                        <td>" . $tuple_account['balance'] . "</td>
                        <td>" . $tuple_account['openDate'] . "</td>
                        <td class='no-bullet'>
                            <a href='delete.php?aid=" . $tuple_account['aid'] . "'>Close Account</a>
                        </td>
                    </tr>";
                }               
            }
        }
        echo "</table></div>";

        echo "<div align='center'><p style = 'font-size:32px; color:#cc0000; margin-top:10px'>" . $delete . "</p></div>";

        echo "<div align='center'><a href='transaction.php'> Money Transfer </a></div>";

        echo "<br><div align='center'><a href='logout.php'> Log Out </a></div>";
        echo "</html>";
    }
    else 
        header("location: login.php");
?>