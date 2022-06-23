<!DOCTYPE html>

<html>
    <head>
        <title>CONFIRMAR</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <LINK REL=StyleSheet HREF="Estilo.css" TYPE="text/css" MEDIA=screen>
    </head>
    <body>
	<header>
           <h1>CONFIRMAR</h1>         
        </header>

 <?php 
 
	
 
	if(isset($_POST)){
				$p1=$_POST["p1"];
				$p2=$_POST["p2"];
				$p3=$_POST["p3"];
				$p4=$_POST["p4"];
				$p5=$_POST["p5"];
				$b1=$_POST["b1"];
				$b2=$_POST["b2"];
				$b3=$_POST["b3"];
				$l1=$_POST["l1"];
				$l2=$_POST["l2"];
		}else
		{
			echo "FALLO AL PASAR DATOS";
		}
				
   $conexion = new  mysqli("localhost", "root", "root", "hundelaflota"); 
   if($conexion->connect_errno){
	   echo "Falló la conexión" . $conexion->connect_errno;
   }
   $conexion->set_charset("utf8");
   
	
		$sql1="delete from portaaviones"; 
		$sql2="delete from buque"; 
		$sql3="delete from lanchauno"; 
		$sql4="delete from lanchados"; 
		$sql5="delete from postablas"; 
		$sql6="delete from disparos";

		$conexion->query($sql1);
		$conexion->query($sql2);
		$conexion->query($sql3);
		$conexion->query($sql4);
		$conexion->query($sql5);
		$conexion->query($sql6);
		
		$insertar1="insert into portaaviones values (0, $p1, $p2, $p3, $p4, $p5)";
		$insertar2="insert into buque values (0, $b1, $b2, $b3)";
		$insertar3="insert into lanchauno values (0, $l1)";
		$insertar4="insert into lanchados values (0, $l2)";
		$conexion->query($insertar1);
		$conexion->query($insertar2);
		$conexion->query($insertar3);
		$conexion->query($insertar4);
			

		
   $sql="select * from tabla";  

   $resultados=$conexion->query($sql);
   if($conexion->errno){
	   die($conexion->error);
   }

	  while($fila=$resultados->fetch_assoc()){	
		$hor = $fila['horizontal'];
		$ver = $fila['vertical'];
	  }
	  
	  	  $tab = $hor * $ver;
		  
		$mp1 = "";
		$mp2 = "";
		$mp3 = "";
		$mp4 = "";
		
		  if ($tab >= 25 && $tab <=35) 
		  {
			$mp1="insert into portaaviones values (1, 0, 1, 2, 3, 4)";
			$mp2="insert into buque values (1, 20, 30, 40)";
			$mp3="insert into lanchauno values (1, 23)";
			$mp4="insert into lanchados values (1, 43)";
		  } else if ($tab >= 36 && $tab <=45) 
		  {
			$mp1="insert into portaaviones values (1, 0, 10, 20, 30, 40)";
			$mp2="insert into buque values (1, 12, 13, 14)";
			$mp3="insert into lanchauno values (1, 42)";
			$mp4="insert into lanchados values (1, 44)";
		  } else if ($tab >= 46 && $tab <=55) 
		  {
			$mp1="insert into portaaviones values (1, 1, 11, 21, 31, 41)";
			$mp2="insert into buque values (1, 3, 4, 5)";
			$mp3="insert into lanchauno values (1, 24)";
			$mp4="insert into lanchados values (1, 44)";
		  } else if ($tab >= 56 && $tab <=65) 
		  {
			$mp1="insert into portaaviones values (1, 15, 25, 35, 45, 55)";
			$mp2="insert into buque values (1, 11, 21, 31)";
			$mp3="insert into lanchauno values (1, 3)";
			$mp4="insert into lanchados values (1, 51)";
		  } else if ($tab >= 66 && $tab <=75) 
		  {
			$mp1="insert into portaaviones values (1, 61, 62, 63, 64, 65)";
			$mp2="insert into buque values (1, 5, 15, 25)";
			$mp3="insert into lanchauno values (1, 11)";
			$mp4="insert into lanchados values (1, 32)";
		  } else if ($tab >= 76 && $tab <=85) 
		  {
			$mp1="insert into portaaviones values (1, 31, 32, 33, 34, 35)";
			$mp2="insert into buque values (1, 53, 63, 73)";
			$mp3="insert into lanchauno values (1, 1)";
			$mp4="insert into lanchados values (1, 7)";
		  }
		  else if ($tab >= 86 && $tab <=100) 
		  {
			$mp1="insert into portaaviones values (1, 26, 36, 46, 56, 66)";
			$mp2="insert into buque values (1, 20, 21, 22)";
			$mp3="insert into lanchauno values (1, 71)";
			$mp4="insert into lanchados values (1, 54)";
		  }
		  
		  

		$conexion->query($mp1);
		$conexion->query($mp2);
		$conexion->query($mp3);
		$conexion->query($mp4);
		  
	  
	  echo "<h2> El campo de batalla es de $tab cuadros, el portaaviones está en $p1, $p2, $p3, $p4 y $p5, el buque en $b1, $b2 y $b3, las lanchas en $l1 y $l2 </h2>";
	  
	  		$posiciones = array("$p1", "$p2", "$p3", "$p4", "$p5", "$b1", "$b2", "$b3", "$l1", "$l2");

	  echo "<table>";
		$n=0;
		$s=0;
		for ($n1=0; $n1<$ver; $n1++)
		{

			$s=1;
		echo "<tr>";

		for ($s=0; $s<$hor; $s++)
		{			
			$encontrado=false;
			$num = $n."".$s;
				$posTablas="insert into postablas values ($num)";
				$conexion->query($posTablas);
			foreach($posiciones as $elemento){
							
			if ($elemento==$num)
            $encontrado=true;
			}
			if ($encontrado){
			echo "<td class='pintado'>", $n, $s, "</td>";
			}
			else
			{echo "<td>", $n, $s, "</td>";}
			}

		

			$n=$n+1;
		echo "</tr>";
		}
		echo "</table>";
		
		$fallos = 0;
		
		foreach($posiciones as $elemento){
			if ($elemento>$num || $elemento<0)
            {
				echo "<h2> Posición $elemento mal puesta, vuelva atrás y colóquelo bien </h2>";
				$fallos++;
			}
			}
			
		
		if ( ( ($p1 + 1) == $p2 && ($p1 + 2) == $p3 && ($p1 + 3) == $p4 && ($p1 + 4) == $p5) || 
		(($p1 - 1) == $p2 && ($p1 - 2) == $p3 &&  ($p1 - 3) == $p4 && ($p1 - 4) == $p5))
		{
		echo "<h2> Portaaviones está en modo horizontal </h2>";
		
		foreach($posiciones as $elemento){
			if ($elemento==($p1+10) || $elemento==($p2+10) || $elemento==($p3+10) || $elemento==($p4+10) || $elemento==($p5+10) ||
				$elemento==($p1-10) || $elemento==($p2-10) || $elemento==($p3-10) || $elemento==($p4-10) || $elemento==($p5-10) ||
				$elemento==($p1-1) 	|| $elemento==($p1+10-1) || $elemento==($p1-10-1)  || $elemento==($p5+1)  || $elemento==($p5+10+1) ||
				$elemento==($p5-10+1))
            {
				echo "<h2> En la posición $elemento no hay el espacio requerido entre barcos </h2>";
				$fallos++;
			} 
			
			}

		
		} else 
		if ( ( ($p1 + 10) == $p2 && ($p1 + 20) == $p3 && ($p1 + 30) == $p4 && ($p1 + 40) == $p5) || 
		(($p1 + 10) == $p2 && ($p1 + 20) == $p3 &&  ($p1 - 30) == $p4 && ($p1 - 40) == $p5))
		{
		echo "<h2> Portaaviones está en modo vertical </h2>";
		
		foreach($posiciones as $elemento){
			if ($elemento==($p1+1) || $elemento==($p2+1) || $elemento==($p3+1) || $elemento==($p4+1) || $elemento==($p5+1) ||
				$elemento==($p1-1) || $elemento==($p2-1) || $elemento==($p3-1) || $elemento==($p4-1) || $elemento==($p5-1) ||
				$elemento==($p1-10) 	|| $elemento==($p1-10-1) || $elemento==($p1-10+1)  || $elemento==($p5+10)  || $elemento==($p5+10+1) ||
				$elemento==($p5+10-1))
            {
				echo "<h2> En la posición $elemento el portaaviones tiene un problema de espacio </h2>";
								$fallos++;

			}
			}
		
		}
		else {
					echo "<h2> Portaaviones está mal puesto </h2>";
									$fallos++;

		}
		
		if ( ( ($b1 + 1) == $b2 && ($b1 + 2) == $b3) || 
		(($b1 - 1) == $b2 && ($b1 - 2) == $b3 ))
		{
		echo "<h2> Buque está en modo horizontal </h2>";
		
		foreach($posiciones as $elemento){
			if ($elemento==($b1+10) || $elemento==($b2+10) || $elemento==($b3+10) ||
				$elemento==($b1-10) || $elemento==($b2-10) || $elemento==($b3-10) ||
				$elemento==($b1-1) 	|| $elemento==($b1+10-1) || $elemento==($b1-10-1)  || $elemento==($b3+1)  || $elemento==($b3+10+1) ||
				$elemento==($b3-10+1))
            {
				echo "<h2> En la posición $elemento no hay el espacio requerido entre barcos </h2>";
								$fallos++;

			}
			}
		} else 
		if ( ( ($b1 + 10) == $b2 && ($b1 + 20) == $b3) || 
		(($b1 - 10) == $b2 && ($b1 - 20) == $b3))
		{
		echo "<h2> Buque está en modo vertical </h2>";
		
		foreach($posiciones as $elemento){
			if ($elemento==($b1+1) || $elemento==($b2+1) || $elemento==($b3+1) ||
				$elemento==($b1-1) || $elemento==($b2-1) || $elemento==($b3-1) ||
				$elemento==($b1-10) 	|| $elemento==($b1-10-1) || $elemento==($b1-10+1)  || $elemento==($b3+10)  || $elemento==($b3+10+1) ||
				$elemento==($b3+10-1))
            {
				echo "<h2> En la posición $elemento el buque tiene un problema de espacio </h2>";
								$fallos++;

			}
			}
		}
		else {
					echo "<h2> Buque está mal puesto </h2>";
									$fallos++;

		}

			echo "<h2> Verificación de la lancha 1 </h2>";

		foreach($posiciones as $elemento){
			if ($elemento==($l1+1) || $elemento==($l1-1) || $elemento==($l1-10) || $elemento==($l1-10-1) || 
				$elemento==($l1-10+1)  || $elemento==($l1+10)  || $elemento==($l1+10+1) ||
				$elemento==($l1+10-1))
            {
				echo "<h2> En la posición $elemento la lancha 1 tiene un problema de espacio </h2>";
								$fallos++;

			}
			}
			
			echo "<h2> Verificación de la lancha 2 </h2>";

		foreach($posiciones as $elemento){
			if ($elemento==($l2+1) || $elemento==($l2-1) || $elemento==($l2-10) || $elemento==($l2-10-1) || 
				$elemento==($l2-10+1)  || $elemento==($l2+10)  || $elemento==($l2+10+1) ||
				$elemento==($l2+10-1))
            {
				echo "<h2> En la posición $elemento la lancha 1 tiene un problema de espacio </h2>";
								$fallos++;

			}
			}
	  
		echo "<br>";
		
		
		if ($fallos>0)
		{
			echo "<h2> CANTIDAD DE ERRORES: $fallos </h2>";		
		} 
		
		if($fallos>0){
					echo '<div class="padre">';
					echo '<div class="hijo">';
					echo '<a href="Index.php" class="boton">DATOS MAL INSERTADOS, VOLVER A INDEX</a>';
					echo '</div>';
					echo '</div>';

		} else {
			echo '<div class="padre">';
					echo '<div class="hijo">';
					echo '<a href="Jugar.php" class="boton">DATOS CORRECTOS, EMPEZAR A JUGAR</a>';
					echo '</div>';
					echo '</div>';

		}
	  
	 $conexion->close();	 
	 ?>
	 
   </body>
</html>