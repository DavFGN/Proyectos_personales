<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>ej1</title>
</head>
<body>

<?php
session_start();
$dia=date("d-m-Y");
if(isset($_REQUEST['generar'])){
	unset($_REQUEST['generar']);
	$alumnos=array_keys($_REQUEST);
	$faltas=array_values($_REQUEST);
	$archivo="Faltas_$dia"."_$_SESSION[grupo].csv";
	$csv=fopen($archivo,"w");
	fputs($csv,'"Alumno","Falta"'."\n");
	for($i=0;$i<count($alumnos);$i++){
		$cad='"'.$alumnos[$i].'","'.$faltas[$i].'"'."\n";
		fputs($csv,$cad);}
	fclose($csv);
	echo 'Archivo generado.<br>';
	echo '<a href="'.$archivo.'" download="'.$archivo.'">Descargar</a>';
}
else if(isset($_REQUEST['grupo'])){
	$_SESSION['grupo']=$_REQUEST['grupo'];
	$conex=mysqli_connect("localhost","root","","ex4");
	$alumnos=mysqli_query($conex,"select nombre,apellidos from alumnos where grupo='$_SESSION[grupo]'");
	echo "Día: $dia<br>Grupo: $_SESSION[grupo]";
	echo '<form method="post">';
	while($reg=mysqli_fetch_array($alumnos)){
		$nombreCompleto="$reg[nombre] $reg[apellidos]";
		echo '<input type="radio" name="'."$nombreCompleto".'" value="J"> J ';
		echo '<input type="radio" name="'."$nombreCompleto".'" value="I"> I ';
		echo "- $nombreCompleto<br>";}
	echo '<input type="submit" value="Enviar" name="generar"></form>';
}
else{
	echo 'Seleccione grupo<br>
		<form method="post">
		<select name="grupo">
		<option value="1SMR">1SMR</option>
		<option value="1DAM">1DAM</option>
		<option value="1DAW">1DAW</option>
		<option value="2DAM">2DAM</option>
		<option value="2DAW">2DAW</option>
		</select>
		<input type="submit" value="Siguiente"></form>';}
?>

</body>