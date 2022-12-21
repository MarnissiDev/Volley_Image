<?php
    

         $r=$_POST["ref"];
	
   $user="root";
   $password="";
   $host="localhost";
   $db_name="stockupf22";
   
     
   $con=mysqli_connect($host,$user,$password,$db_name);
  
 $req="delete  from produit where rf='".$r."';";
   
   if(mysqli_query($con,$req))
	  echo "Produit supprimé ";
    else
      echo"problème de suppression";
	  
	  ?>