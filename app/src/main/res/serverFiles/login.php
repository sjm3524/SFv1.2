<?php

include 'config.php';

$con = mysqli_connect($host, $user, $pass "dev-space-db06");
$username = $_POST["username"];
$password = $_POST["password"];
// $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
$statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = '" . $username . "' AND password = '" . $password . "' ");
mysqli_stmt_bind_param($statement, "ss", $username, $password);
$response = array();
$response["success"] = false;
while(mysqli_stmt_fetch($statement)){
    $response["success"] = true;
    $response["name"] = $name;
    $response["age"] = $age;
    $response["username"] = $username;
    $response["password"] = $password;
}
echo json_encode($response);
?>
