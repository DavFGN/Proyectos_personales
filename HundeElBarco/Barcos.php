<!DOCTYPE html>

<html>
    <head>
        <title>BARCOS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <LINK REL=StyleSheet HREF="Estilo.css" TYPE="text/css" MEDIA=screen>
    </head>
    <body>
	<header>
           <h1>BARCOS</h1>         
        </header>
		<?php 				
		if(isset($_POST)){
				$vVer=$_POST["vertic"];
				$vHor=$_POST["horiz"];
		}else
		{
			echo "FALLO AL PASAR DATOS";
		}
		
		 $conexion = new  mysqli("localhost", "root", "root", "hundelaflota");
   
		if($conexion->connect_errno){
	   echo "Falló la conexión" . $conexion->connect_errno;
		}
		$conexion->set_charset("utf8");
   
		$sql="insert into tabla values ($vVer, $vHor) "; 
		$resultados=$conexion->query($sql);
		$conexion->close();		

				echo "<table>";
		$n=0;
		$s=0;
		for ($n1=0; $n1<$vVer; $n1++)
		{

			$s=1;
		echo "<tr>";

		for ($s=0; $s<$vHor; $s++)
		{			
			
		echo "<td>", $n, $s, "</td>";

			}

		

			$n=$n+1;
		echo "</tr>";
		}
		echo "</table>";
		
		?>
		<p><h4>INSERTE LAS POSICIONES DE DONDE ESTARÁN LOS BARCOS (EN FILA, VERTICAL U HORIZONTAL, SIN CHOCARSE) </h4></p>
		
			<form action="Confirmar.php" method="POST">
		  <p><h4>Portaaviones:</h4> <input type="number" value="0" name="p1"> <input type="number" name="p2" value="1"> <input type="number" name="p3" value="2">
		  <input type="number" name="p4" value="3"> <input type="number" name="p5" value="4"></p>
		  <p><h4>Buque:</h4> <input type="number" name="b1" value="20"> <input type="number" name="b2" value="30"> <input type="number" name="b3" value="40"></p>
		  <p><h4>Lancha 1:</h4> <input type="number" name="l1" value="23"></p>
		  <p><h4>Lancha 2:</h4> <input type="number" name="l2" value="43"></p>
		  		<input type="submit" value="SIGUIENTE" name="confirm">
			</form>

   </body>
</html>