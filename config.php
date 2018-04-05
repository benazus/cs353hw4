<?php
    define('server', 'dijkstra.ug.bcc.bilkent.edu.tr');
    define('username', 'berat.bicer');
    define('password', 'jfb4ia01');
    define('database', 'berat_bicer');    
    $conn = mysqli_connect(server, username, password, database);
    mysqli_select_db($conn, database);
?>