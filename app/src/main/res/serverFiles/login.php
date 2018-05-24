<?php

//include 'config.php';

$con = mysqli_connect("localhost", "dbadmin01", "W0rldView!!", "dev-space-db06");

mysqli_set_charset($con, 'utf8');

$username = $_POST["username"];
$password = $_POST["password"];
$statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
//$statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = '" . $username . "' AND password = '" . $password . "' ");
mysqli_stmt_bind_param($statement, "ss", $username, $password);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $userID, $name, $username, $email, $password);

$response = array();
$response["success"] = false;
while(mysqli_stmt_fetch($statement)){
    $response["success"] = true;
    $response["name"] = $name;
    $response["email"] = $age;
    $response["username"] = $username;
    $response["password"] = $password;
	$response["user_ID"] = $userID;
}
echo json_encode($response);
?>
