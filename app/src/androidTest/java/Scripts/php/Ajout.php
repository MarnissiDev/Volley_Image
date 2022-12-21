<?php



$c = $_POST["ref"];
$n = $_POST["des"];
$p = $_POST["prix"];


$user = "root";
$password = "";
$host = "localhost";
$db_name = "stockupf22";


$con = mysqli_connect($host, $user, $password, $db_name);
$req = "insert into produit(rf,dg,prix) values('" . $c . "','" . $n . "','" . $p . "');";

if (mysqli_query($con, $req))
  echo "Insertion r�ussie";
else
  echo "erreur d'insertion!!!";
