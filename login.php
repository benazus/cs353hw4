<?php
    session_start();
    include("config.php");
    
    if($_SERVER["REQUEST_METHOD"] == "POST") {
        // Collect credentials
        $username = strtolower($_POST['username']);
        $password = $_POST['password'];

        // customer name = username, customer id = password
        $query = "select name, cid from customer where name = '$username' and cid = '$password';";
        $result_table = mysqli_query($conn, $query);
        $count = mysqli_num_rows($result_table);

        if($count == 1) {
            // Access granted - exactly 1 matching tuple is found
            $_SESSION['loggedin'] = true;
            $_SESSION['name'] = $username;
            $_SESSION['cid'] = $password;
            header("location: index.php");
        }
        else {
            $error = "Invalid username or password.";
        }
    }

    // Table silecek script ve server side function
    // Assignment' ın son kısmı
?>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <div align = "center">
            <div align = "left">
                <div><b>Login</b></div>
                    
                <div>
                    <form action = "login.php" method = "post">
                        <label>User Name: </label><input type = "text" name = "username"/><br/><br/>
                        <label>Password: </label><input type = "password" name = "password"/><br/><br/>
                        <input type = "submit" value = " Submit "/><br/>
                    </form>              
                    <div style = "font-size:11px; color:#cc0000; margin-top:10px"><?php echo $error; ?></div>                       
                </div>                   
            </div>              
        </div>
    </body>
</html>
