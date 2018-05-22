<?php
include 'config.php';

$con = mysqli_connect($host, $user, $pass $db_name);

$name = $_POST["name"];
$email = $_POST["email"];
$username = $_POST["username"];
$password = $_POST["password"];

//$statement = mysqli_prepare($con, "INSERT INTO user (name, email, username, password) VALUES (?, ?, ?, ?)");

$statement = mysqli_prepare($con, "INSERT INTO user (name, username, email, password) VALUES ($name, $username, $email, $password)";

mysqli_stmt_bind_param($statement, "siss", $name, $email, $username, $password);
mysqli_stmt_execute($statement);

$response = array();
$response["success"] = true;

echo json_encode($response);

?>