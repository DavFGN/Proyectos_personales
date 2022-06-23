<!DOCTYPE html>

<html>
    <head>
        <title>COMBATE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <LINK REL=StyleSheet HREF="Estilo.css" TYPE="text/css" MEDIA=screen>
    </head>
    <body>
		<header>
           <h1>COMBATE INICIADO</h1>         
        </header>
	<table>
<tr>
    <td><table >
<tr><td>
<?php 

  $conexion = new  mysqli("localhost", "root", "root", "hundelaflota"); 
   if($conexion->connect_errno){
	   echo "Falló la conexión" . $conexion->connect_errno;
   }
   $conexion->set_charset("utf8");
   
   $sql="select * from tabla";  

   $resultados=$conexion->query($sql);
   if($conexion->errno){
	   die($conexion->error);
   }

	  while($fila=$resultados->fetch_assoc()){	
		$hor = $fila['horizontal'];
		$ver = $fila['vertical'];
	  }
	  
	  $sql1="select pos1, pos2, pos3, pos4, pos5 from portaaviones WHERE turno = 0";
	  $sql2="select pos1, pos2, pos3 from buque WHERE turno = 0";
	  $sql3="select pos1 from lanchauno WHERE turno = 0";
	  $sql4="select pos1 from lanchados where turno = 0";
	  
	  	$re1=$conexion->query($sql1);
		$re2=$conexion->query($sql2);
		$re3=$conexion->query($sql3);
		$re4=$conexion->query($sql4);
		
		 while($fila=$re1->fetch_assoc()){	
				$p1=$fila['pos1'];
				$p2=$fila['pos2'];
				$p3=$fila['pos3'];
				$p4=$fila['pos4'];
				$p5=$fila['pos5'];
	  }
	  while($fila=$re2->fetch_assoc()){	
				$b1=$fila['pos1'];
				$b2=$fila['pos2'];
				$b3=$fila['pos3'];

	  }
	  while($fila=$re3->fetch_assoc()){	
				$l1=$fila['pos1'];
	  }
	  while($fila=$re4->fetch_assoc()){	
				$l2=$fila['pos1'];

	  }
	  
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

?>

</td></tr></table></td>


    <td><table>
<tr><td>
 <?php 

  $conexion = new  mysqli("localhost", "root", "root", "hundelaflota"); 
   if($conexion->connect_errno){
	   echo "Falló la conexión" . $conexion->connect_errno;
   }
   $conexion->set_charset("utf8");
   
   $sql="select * from tabla";  

   $resultados=$conexion->query($sql);
   if($conexion->errno){
	   die($conexion->error);
   }

	  while($fila=$resultados->fetch_assoc()){	
		$hor = $fila['horizontal'];
		$ver = $fila['vertical'];
	  }
	  
	  $sql1="select pos1, pos2, pos3, pos4, pos5 from portaaviones WHERE turno = 1";
	  $sql2="select pos1, pos2, pos3 from buque WHERE turno = 1";
	  $sql3="select pos1 from lanchauno WHERE turno = 1";
	  $sql4="select pos1 from lanchados where turno = 1";
	  
	  	$re1=$conexion->query($sql1);
		$re2=$conexion->query($sql2);
		$re3=$conexion->query($sql3);
		$re4=$conexion->query($sql4);
		
		 while($fila=$re1->fetch_assoc()){	
				$p1=$fila['pos1'];
				$p2=$fila['pos2'];
				$p3=$fila['pos3'];
				$p4=$fila['pos4'];
				$p5=$fila['pos5'];
	  }
	  while($fila=$re2->fetch_assoc()){	
				$b1=$fila['pos1'];
				$b2=$fila['pos2'];
				$b3=$fila['pos3'];

	  }
	  while($fila=$re3->fetch_assoc()){	
				$l1=$fila['pos1'];
	  }
	  while($fila=$re4->fetch_assoc()){	
				$l2=$fila['pos1'];

	  }
	  
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
			$num = $n."".$s;
			echo "<td>", $n, $s, "</td>";
			}

		

			$n=$n+1;
		echo "</tr>";
		}
		echo "</table>";
		

?>

</td></tr></table></td>
</tr>
</table>	
        <p><h4>PONGA EL NÚMERO DE LA CASILLA A LA QUE VA A DISPARAR</h4></p>		
		<form action="Jugar2.php" method="POST">
        <p><h4>DISPARO:</h4> <input type="number" name="disparo1" value="0"></p>
		<?php
			$conexion = new  mysqli("localhost", "root", "root", "hundelaflota"); 
			if($conexion->connect_errno){
			   echo "Falló la conexión" . $conexion->connect_errno;
			}
			$conexion->set_charset("utf8");
	
		   if($conexion->errno){
			   die($conexion->error);
		   }
		   
		   $inse = "insert into disparos values (1, 200)";
		   		$conexion->query($inse);			
				$sql="select posiciones from postablas where posiciones not in (
						select posiciones from postablas, disparos where posiciones = disparo and 
						turno = 1) order by rand() limit 1";  
				$res=$conexion->query($sql);
				while($fila=$res->fetch_assoc()){		
					$numero = $fila['posiciones'];
				} ;	
		
				
		echo "<input type='hidden' name='maqdis' value='$numero' />";
		?>
		<input type="submit" value="SIGUIENTE" name="sigJugar" id="sigJ">
		</form>

        
   </body>
</html>