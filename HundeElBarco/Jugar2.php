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

		if(isset($_POST)){
				$disp1=$_POST["disparo1"];
				$disp2=$_POST["maqdis"];
		}else
		{
			echo "FALLO AL PASAR DATOS";
		}

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
	  $sql5="delete from disparos where turno = 1 and disparo = 200";
	  
	  $d0 = "insert into disparos values (0, $disp1)";
	  $d1 = "insert into disparos values (1, $disp2)";

		$conexion->query($d0);
		$conexion->query($d1);
		
	  	$re1=$conexion->query($sql1);
		$re2=$conexion->query($sql2);
		$re3=$conexion->query($sql3);
		$re4=$conexion->query($sql4);
		$conexion->query($sql5);
		
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
	
	$dispa = "select disparo from disparos where turno = 1";
	$dispar1=$conexion->query($dispa);
	
	$arrdis = array();
	
	while($fila=$dispar1->fetch_assoc()){	
				$tem=$fila['disparo'];
				array_push($arrdis, $tem);
	  }
	
		
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
			$tocado=false;
			$num = $n."".$s;
 
			foreach($posiciones as $elemento){
			if ($elemento==$num)
            $encontrado=true;
			}
			
			foreach($arrdis as $elemento){
			if ($elemento==$num)
            $tocado=true;
			}
			
			if($encontrado && $tocado){
			echo "<td class='cazado'>", $n, $s, "</td>";	
			}
			else
			if ($encontrado){
			echo "<td class='pintado'>", $n, $s, "</td>";
			}
			else
				
			if ($tocado){
			echo "<td class='agua'>", $n, $s, "</td>";
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
 
 if(isset($_POST)){
				$disp1=$_POST["disparo1"];
				$disp2=$_POST["maqdis"];
		}else
		{
			echo "FALLO AL PASAR DATOS";
		}

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
		
	$dispa = "select disparo from disparos where turno = 0";
	$dispar1=$conexion->query($dispa);
	
	$arrdis = array();
	
	while($fila=$dispar1->fetch_assoc()){	
				$tem=$fila['disparo'];
				array_push($arrdis, $tem);
	  }
	  
	  $comprobar = "";
		
		
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
			$tocado=false;
			$num = $n."".$s;
 
			foreach($posiciones as $elemento){
			if ($elemento==$num)
            $encontrado=true;
			}
			
			foreach($arrdis as $elemento){
			if ($elemento==$num)
            $tocado=true;
			}
			
			if($encontrado && $tocado){
			echo "<td class='cazado'>", $n, $s, "</td>";	
			if($num==$disp1){$comprobar="TOCADO";}
			}
			else
			if ($encontrado){
			echo "<td>", $n, $s, "</td>";
			}
			else
				
			if ($tocado){
			echo "<td class='agua'>", $n, $s, "</td>";
			if($num==$disp1){$comprobar="AGUA";}
			}	
				else
			{echo "<td>", $n, $s, "</td>";}
			}

		

			$n=$n+1;
		echo "</tr>";
		}
		echo "</table>";
		
			  $maxsql="SELECT max(posiciones) as pos from postablas";
	  
	  	$resmax=$conexion->query($maxsql);
		
		 while($fila=$resmax->fetch_assoc()){	
				$maxcifra=$fila['pos'];
	  }

		if($disp1>$maxcifra || $disp1<0){
			$comprobar="FUERA DE LOS LIMITES";
		}

		 echo "</td></tr></table></td></tr></table><br>
		 
		 <table><tr><td>";
 
		if($comprobar == "")
		{
			$comprobar = "FUERA DE LOS LIMITES";
		}
		
 echo "<p><h2>DISPARO: $comprobar</h2></p>";
 
 $fin = "select (select count(*) from disparos, portaaviones where disparos.turno = 1 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos1) + 
		(select count(*) from disparos, portaaviones where disparos.turno = 1 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos2) +
		(select count(*) from disparos, portaaviones where disparos.turno = 1 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos3) +
		(select count(*) from disparos, portaaviones where disparos.turno = 1 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos4) +
		(select count(*) from disparos, portaaviones where disparos.turno = 1 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos5) +
		(select count(*) from disparos, buque where disparos.turno = 1 and disparos.turno = buque.turno and disparo = buque.pos1) + 
		(select count(*) from disparos, buque where disparos.turno = 1 and disparos.turno = buque.turno and disparo = buque.pos2) + 
		(select count(*) from disparos, buque where disparos.turno = 1 and disparos.turno = buque.turno and disparo = buque.pos3) + 
		(select count(*) from disparos, lanchauno where disparos.turno = 1 and disparos.turno = lanchauno.turno and disparo = lanchauno.pos1) + 
		(select count(*) from disparos, lanchados where disparos.turno = 1 and disparos.turno = lanchados.turno and disparo = lanchados.pos1) 
		as disparo";
		
		$compfin=$conexion->query($fin);
		while($fila=$compfin->fetch_assoc()){	
				$cnt11=$fila['disparo'];
	  }
	  
	  if($cnt11>=10){
		echo"<h1>HAS PERDIDO</h1>";
			echo '<div class="padre">';
					echo '<div class="hijo">';
					echo '<a href="Index.php" class="boton">VOLVER AL INDEX</a>';
					echo '</div>';
					echo '</div>';
	  }
	  
	   $fin0 = "select (select count(*) from disparos, portaaviones where disparos.turno = 0 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos1) + 
		(select count(*) from disparos, portaaviones where disparos.turno = 0 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos2) +
		(select count(*) from disparos, portaaviones where disparos.turno = 0 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos3) +
		(select count(*) from disparos, portaaviones where disparos.turno = 0 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos4) +
		(select count(*) from disparos, portaaviones where disparos.turno = 0 and disparos.turno = portaaviones.turno and disparo = portaaviones.pos5) +
		(select count(*) from disparos, buque where disparos.turno = 0 and disparos.turno = buque.turno and disparo = buque.pos1) + 
		(select count(*) from disparos, buque where disparos.turno = 0 and disparos.turno = buque.turno and disparo = buque.pos2) + 
		(select count(*) from disparos, buque where disparos.turno = 0 and disparos.turno = buque.turno and disparo = buque.pos3) + 
		(select count(*) from disparos, lanchauno where disparos.turno = 0 and disparos.turno = lanchauno.turno and disparo = lanchauno.pos1) + 
		(select count(*) from disparos, lanchados where disparos.turno = 0 and disparos.turno = lanchados.turno and disparo = lanchados.pos1) 
		as disparo";
		
		$compfin0=$conexion->query($fin0);
		while($fila=$compfin0->fetch_assoc()){	
				$cnt10=$fila['disparo'];
	  }

	  if($cnt10>=10){
		echo"<h1>HAS GANADO</h1>";
			echo '<div class="padre">';
					echo '<div class="hijo">';
					echo '<a href="Index.php" class="boton">VOLVER AL INDEX</a>';
					echo '</div>';
					echo '</div>';
	  }
	  
	  

?> </td></tr></table></td>
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