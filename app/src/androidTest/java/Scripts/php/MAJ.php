
<?php

$c = $_POST["ref"];
$n = $_POST["des"];
$p = $_POST["prix"];


$user = "root";
$password = "";
$host = "localhost";
$db_name = "stockupf22";


$con = mysqli_connect($host, $user, $password, $db_name);
$req = "update produit set dg='" . $n . "',prix='" . $p . "' where rf='" . $c . "';";

if (mysqli_query($con, $req))
  echo "Mise à jour effectuée";
else
  echo "problème de mise à jour";

?>