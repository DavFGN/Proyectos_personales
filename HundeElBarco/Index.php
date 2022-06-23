<!DOCTYPE html>

<html>
    <head>
        <title>Index</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <LINK REL=StyleSheet HREF="Estilo.css" TYPE="text/css" MEDIA=screen>
        <script type="text/javascript" src="javascript.js"></script>
    </head>
    <body>
		<header>
           <h1>CAMPO DE BATALLA</h1>         
        </header>
		<?php 
				
		 $conexion = new  mysqli("localhost", "root", "root", "hundelaflota");
   
		if($conexion->connect_errno){
	   echo "Falló la conexión" . $conexion->connect_errno;
		}
		$conexion->set_charset("utf8");
   
		$sql="delete from tabla"; 
		$sql1="delete from portaaviones"; 
		$sql2="delete from buque"; 
		$sql3="delete from lanchauno"; 
		$sql4="delete from lanchados"; 
		$sql5="delete from disparos";
		$sql6="delete from postablas";
		$conexion->query($sql);
		$conexion->query($sql1);
		$conexion->query($sql2);
		$conexion->query($sql3);
		$conexion->query($sql4);
		$conexion->query($sql5);
		$conexion->query($sql6);

		$conexion->close();
		?>
        <p><h4>INSERTE EL NÚMERO DE FILAS VERTICALES Y HORIZONTALES (MIN 5 X 5, MAX 10 X 10)</h4></p>

        <p><h4>Filas verticales:</h4> <input type="number" id="nVer" min="5" max="10" value="10"></p>
        <p><h4>Filas horizontales:</h4> <input type="number" id="nHor" min="5" max="10" value="10"></p>
        <input type="button" value="CONFIRMAR TAMAÑO" onclick="dosF(); this.disabled=true;">
		
		<form action="Barcos.php" method="POST">
		<br>
		<input type="hidden" name="vertic" id="nVerDefinitivo" value="0" />
		<input type="hidden" name="horiz" id="nHorDefinitivo"  value="0" />
		<input type="submit" value="SIGUIENTE" name="barcos" id="sig" disabled>
		</form>
        
        
   </body>
</html>