<?php
//include 'config.php';

//echo 'response';
$con = mysqli_connect("localhost", "dbadmin01", "W0rldView!!", "dev-space-db06");

mysqli_set_charset($con, 'utf8');

$userID = $_POST["user"];
$friendID = $_POST["friend"];

//echo $userID;
//echo $friendID;

//$response = array();
$statement = mysqli_prepare($con, "INSERT INTO friends (user, friend) VALUES (?, ?)");
//$response["success"] = false;

//$statement = mysqli_prepare($con, "INSERT INTO user (name, username, email, password) VALUES ($name, $username, $email, $password)";

mysqli_stmt_bind_param($statement, "ss", $userID, $friendID);
mysqli_stmt_execute($statement);

$check_verified = mysqli_prepare($con, "SELECT * FROM friends WHERE friend = $userID");



mysqli_stmt_execute($check_verified);

//mysqli_stmt_store_result($check_verified);
//mysqli_stmt_bind_result($check_verified, $row, $user, $friend, $verified);

$verified=1;

$sql = mysql_query("SELECT user FROM friends WHERE friend = '".$userID."'");
            if (mysql_num_rows($sql) != 0) {
				while(mysqli_stmt_fetch($statement)){
					if (
					
					echo json_encode($response);
				}
			}
$query = mysqli_prepare($con ,"UPDATE friends SET verified= ".$verified." WHERE friend='".$userID."' AND user = '".$userID."'");
mysqli_stmt_execute($query);





$response= "true";

echo $response;

?>