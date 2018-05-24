<?php

$con = mysqli_connect("localhost", "dbadmin01", "W0rldView!!", "dev-space-db06");

mysqli_set_charset($con, 'utf8');

$userID = $_POST["user"];
$friendID = $_POST["friend"];



$statement = mysqli_prepare($con, "INSERT INTO friends (user, friend) VALUES (?, ?)");

mysqli_stmt_bind_param($statement, "ss", $userID, $friendID);
mysqli_stmt_execute($statement);


$verified =1;

//$scon = mysqli_connect("localhost", "dbadmin01", "W0rldView!!", "dev-space-db06");

$check_stmt ="UPDATE friends SET verified = '" . $verified . "' where friend = '". $userID ."' and user = '". $friendID ."'";
$verify = mysqli_query($con, $check_stmt);


if (mysqli_affected_rows($con)>0) { 
	
	$query = mysqli_query($con ,"UPDATE friends SET verified= '" . $verified. "' WHERE friend='". $friendID ."' AND user = '". $userID ."'");
	
	
	}











$response= "true";

echo $response;
//echo "hi";
//echo $userID;
//echo $friendID;

?>