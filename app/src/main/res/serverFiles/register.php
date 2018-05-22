<?php
//include 'config.php';

//echo 'response';
$con = mysqli_connect("localhost", "dbadmin01", "W0rldView!!", "dev-space-db06");

mysqli_set_charset($con, 'utf8');

$name = $_POST["name"];
$email = $_POST["email"];
$username = $_POST["username"];
$password = $_POST["password"];
//$response = array();
$statement = mysqli_prepare($con, "INSERT INTO user (name, username, email, password) VALUES (?, ?, ?, ?)");
//$response["success"] = false;

//$statement = mysqli_prepare($con, "INSERT INTO user (name, username, email, password) VALUES ($name, $username, $email, $password)";

mysqli_stmt_bind_param($statement, "ssss", $name, $username, $email, $password);
mysqli_stmt_execute($statement);


$response= "true";

//echo $response;
echo $response;

?>